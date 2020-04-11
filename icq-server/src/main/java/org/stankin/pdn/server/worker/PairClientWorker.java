package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet2PairRequest;
import org.stankin.pdn.client.packet.Packet2PairResponse;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;

public class PairClientWorker extends AbstractClientWorker {

    private ClientListWorker clientListWorker;
    private Client client;

    PairClientWorker(ClientHandler handler, Channel channel, ClientListWorker clientListWorker) {
        super(handler, channel);
        this.clientListWorker = clientListWorker;
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(Packet packet) {
        Packet2PairRequest inPacket = (Packet2PairRequest) packet;

        Client pair = ServerContext.getInstance().getActiveClient(inPacket.getUserToPair());
        if (pair != null && !this.client.getConnectionList().containsKey(pair.getName())) {
            sendReply(new Packet2PairResponse().withSocketAddress(pair.getAddress()).withUsername(pair.getName()),
                    this.client);

            //Отправляем адрес собеседника второму участнику диалога
            sendReply(new Packet2PairResponse().withSocketAddress(this.client.getAddress())
                    .withUsername(this.client.getName()), pair);

            client.getConnectionList().put(client.getName(), client.getAddress());
        } else {
            sendReply(new Packet2PairResponse().withReason("Client is not online"), this.client);
        }

        //Попробовать создать объект - канал. Выдавать его на двух пользователей и все сообщения транслировать в него
    }

    private void sendReply(Packet packet, Client to) {
        channel.write(packet, to.getAddress());
    }


}

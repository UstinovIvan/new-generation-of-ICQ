package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet3PairRequest;
import org.stankin.pdn.client.packet.Packet3PairResponse;
import org.stankin.pdn.client.packet.Packet2UsersListRequest;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;

public class PairClientWorker extends AbstractClientWorker {

    private ClientListWorker clientListWorker;
    private Client client;

    PairClientWorker(ClientHandler handler, Channel channel, Client client, ClientListWorker clientListWorker) {
        super(handler, channel);
        this.client = client;
        this.clientListWorker = clientListWorker;
    }

    @Override
    public void disconnectedFromChannel() {
        ServerContext.getInstance().removeClient(client);
        channel.close();
    }

    @Override
    public void acceptPacket(Packet packet) {
        if (packet instanceof Packet2UsersListRequest) {
            clientListWorker.acceptPacket(packet);
            return;
        }

        Packet3PairRequest inPacket = (Packet3PairRequest) packet;

        Client pair = ServerContext.getInstance().getActiveClient(inPacket.getUserToPair());
        if (pair != null && !this.client.getConnectionList().containsKey(pair.getName())) {
            sendPacket(new Packet3PairResponse().withSocketAddress(pair.getAddress()).withUsername(pair.getName()));

            //Отправляем адрес собеседника второму участнику диалога
            pair.getHandler().getClientWorker().sendPacket(new Packet3PairResponse().withSocketAddress(this.client.getAddress())
                    .withUsername(this.client.getName()));

            client.getConnectionList().put(pair.getName(), pair.getAddress());
        } else {
            sendPacket(new Packet3PairResponse().withReason("Client is not online"));
        }

        //Попробовать создать объект - канал. Выдавать его на двух пользователей и все сообщения транслировать в него
    }


}

package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;
import org.stankin.pdn.server.packet.Packet;
import org.stankin.pdn.server.packet.Server2ClientList;

import java.util.ArrayList;
import java.util.List;

public class ClientListWorker extends AbstractClientWorker {

    private Client client;

    public ClientListWorker(ClientHandler handler, Channel channel, Client client) {
        super(handler, channel);
        this.client = client;
    }

    @Override
    public void disconnectedFromChannel() {
        channel.close();
    }

    @Override
    public void acceptPacket(Packet packet) {
        Server2ClientList clientListPacket = (Server2ClientList) packet;

        List<String> usersList = new ArrayList<>();
        ServerContext.getInstance().getClientList().forEach(client -> {
            usersList.add(client.getName());
        });
        usersList.remove(client.getName());
        clientListPacket.setUsersList(usersList);

        channel.write(clientListPacket);
    }
}

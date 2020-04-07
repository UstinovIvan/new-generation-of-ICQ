package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet2UsersListResponse;

import java.util.ArrayList;
import java.util.List;

public class ClientListWorker extends AbstractClientWorker {

    private Client client;

    ClientListWorker(ClientHandler handler, Channel channel, Client client) {
        super(handler, channel);
        this.client = client;
    }

    @Override
    public void disconnectedFromChannel() {
        channel.close();
    }

    @Override
    public void acceptPacket(Packet packet) {
        Packet2UsersListResponse clientListPacket = new Packet2UsersListResponse();

        List<String> usersList = new ArrayList<>(ServerContext.getInstance().getClientNames());

        usersList.remove(client.getName());
        clientListPacket.setUsersList(usersList);

        channel.write(clientListPacket);
    }
}

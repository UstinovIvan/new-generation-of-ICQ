package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.packet.Packet;
import org.stankin.pdn.client.packet.Packet250UserDisconnected;
import org.stankin.pdn.server.context.ServerContext;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;

public abstract class AbstractClientWorker implements ClientWorker{

    ClientHandler handler;
    Channel channel;
    Client client;

    AbstractClientWorker(ClientHandler handler, Channel channel, Client client) {
        this.handler = handler;
        this.channel = channel;
        this.client = client;
    }

    @Override
    public void disconnectedFromChannel() {
        if (client != null) {
            if (client.getConnectionList().size() != 0) {
                client.getConnectionList().values()
                        .forEach(clientWorker -> clientWorker.sendPacket(new Packet250UserDisconnected()
                                .withUsername(this.client.getName())));
            }
            ServerContext.getInstance().removeClient(this.client);
        }
        channel.close();
    }

    @Override
    public void sendPacket(Packet packet) {
        this.channel.write(packet);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

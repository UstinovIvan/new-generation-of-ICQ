package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.client.packet.Packet;
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

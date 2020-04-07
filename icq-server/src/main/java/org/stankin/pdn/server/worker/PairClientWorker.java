package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.stankin.pdn.server.handler.ClientHandler;
import org.stankin.pdn.server.model.Client;
import org.stankin.pdn.client.packet.Packet;

public class PairClientWorker extends AbstractClientWorker {

    private Client client;

    PairClientWorker(ClientHandler handler, Channel channel, Client client) {
        super(handler, channel);
        this.client = client;
    }

    @Override
    public void disconnectedFromChannel() {

    }

    @Override
    public void acceptPacket(Packet packet) {

    }


}

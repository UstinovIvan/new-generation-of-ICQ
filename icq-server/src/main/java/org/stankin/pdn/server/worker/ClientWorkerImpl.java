package org.stankin.pdn.server.worker;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.stankin.pdn.server.packet.Packet;

public class ClientWorkerImpl implements ClientWorker {

    private SimpleChannelUpstreamHandler handler;
    private Channel channel;

    public ClientWorkerImpl(SimpleChannelUpstreamHandler handler, Channel channel) {
        this.handler = handler;
        this.channel = channel;
    }

    @Override
    public void disconnectedFromChannel() {
        channel.close();

    }

    @Override
    public void acceptPacket(Packet packet) {

    }
}

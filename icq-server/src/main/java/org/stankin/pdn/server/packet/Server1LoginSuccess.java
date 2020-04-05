package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Server1LoginSuccess extends Packet {

    private final int ID = 11;

    @Override
    public void get(ChannelBuffer buffer) {

    }

    @Override
    public void send(ChannelBuffer buffer) {

    }

    @Override
    public int getID() {
        return this.ID;
    }
}

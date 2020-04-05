package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Server3UsersList extends Packet {

    private final int ID = 30;

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

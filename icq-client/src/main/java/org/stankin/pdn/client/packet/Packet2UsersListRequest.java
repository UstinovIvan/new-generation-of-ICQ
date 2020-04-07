package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet2UsersListRequest extends Packet {

    private final int ID = 20;

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

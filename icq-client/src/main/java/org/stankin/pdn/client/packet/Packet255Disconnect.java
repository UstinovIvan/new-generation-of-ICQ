package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet255Disconnect extends Packet {

    private final int ID = 255;

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void get(ChannelBuffer buffer) {

    }

    @Override
    public void send(ChannelBuffer buffer) {

    }
}
package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet3PublicKey extends Packet {

    private final int ID = 3;

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

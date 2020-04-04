package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class DisconnectPacket extends Packet {

    private final int ID = 5;

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

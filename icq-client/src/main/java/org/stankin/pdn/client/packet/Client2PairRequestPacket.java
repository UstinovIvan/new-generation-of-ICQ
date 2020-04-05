package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Client2PairRequestPacket extends ClientPacket {

    private final int ID = 2;

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

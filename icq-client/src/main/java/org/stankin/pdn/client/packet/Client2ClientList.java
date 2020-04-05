package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.util.List;

public class Client2ClientList extends ClientPacket {

    private final int ID = 20;

    private List<String> clientList;

    @Override
    public void get(ChannelBuffer buffer) {

    }

    @Override
    public void send(ChannelBuffer buffer) {

    }

    @Override
    public int getID() {
        return 0;
    }
}

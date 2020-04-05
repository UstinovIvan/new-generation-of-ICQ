package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.util.ArrayList;
import java.util.List;

public class Client2ClientList extends ClientPacket {

    private final int ID = 20;

    private List<String> clientList;

    @Override
    public void get(ChannelBuffer buffer) {
        int usersCount = buffer.readShort();
        clientList = new ArrayList<>(usersCount);
        StringBuilder builder;
        while (usersCount != 0) {
            int length = buffer.readShort();
            builder = new StringBuilder();
            for (int i = 0; i < length; i ++) {
                builder.append(buffer.readChar());
            }
            clientList.add(builder.toString());
            usersCount--;
        }
    }

    @Override
    public void send(ChannelBuffer buffer) {

    }

    @Override
    public int getID() {
        return this.ID;
    }

    public List<String> getClientList() {
        return clientList;
    }
}

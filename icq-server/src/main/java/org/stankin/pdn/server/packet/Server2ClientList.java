package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.util.List;

public class Server2ClientList extends Packet {

    private final int ID = 20;

    private List<String> usersList;

    @Override
    public void get(ChannelBuffer buffer) {

    }

    @Override
    public void send(ChannelBuffer buffer) {
        buffer.writeShort(usersList.size());
        usersList.forEach(user -> {
            buffer.writeShort(user.length());
            for (int i = 0; i < user.length(); i++) {
                buffer.writeChar(user.charAt(i));
            }
        });
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public List<String> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<String> usersList) {
        this.usersList = usersList;
    }
}

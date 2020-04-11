package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

import java.util.ArrayList;
import java.util.List;

public class Packet2UsersListResponse extends Packet {

    private final int ID = 21;

    private List<String> usersList;

    @Override
    public void get(ChannelBuffer buffer) {
        int usersCount = buffer.readShort();
        usersList = new ArrayList<>(usersCount);
        StringBuilder builder;
        while (usersCount != 0) {
            int length = buffer.readShort();
            builder = new StringBuilder();
            for (int i = 0; i < length; i ++) {
                builder.append(buffer.readChar());
            }
            usersList.add(builder.toString());
            usersCount--;
        }
        System.out.println(usersList);
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

    public Packet2UsersListResponse withUsersList(List<String> usersList) {
        this.usersList = usersList;
        return this;
    }
}

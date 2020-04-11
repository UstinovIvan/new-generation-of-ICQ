package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet2PairRequest extends Packet {

    private final int ID = 30;

    private String userToPair;

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();
        StringBuilder builder = new StringBuilder();
        while (length != 0) {
            builder.append(buffer.readChar());
            length--;
        }
        userToPair = builder.toString();
    }

    @Override
    public void send(ChannelBuffer buffer) {
        int length = userToPair.length();
        buffer.writeShort(length);
        for (int i = 0; i < length; i++) {
            buffer.writeChar(userToPair.charAt(i));
        }
    }

    public String getUserToPair() {
        return userToPair;
    }

    public void setUserToPair(String userToPair) {
        this.userToPair = userToPair;
    }

    public Packet2PairRequest withUserToPair(String userToPair) {
        this.userToPair = userToPair;
        return this;
    }
}

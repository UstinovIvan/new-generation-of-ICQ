package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet1LoginSuccess extends Packet {

    private final int ID = 11;

    private String uid;

    @Override
    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();

        StringBuilder stringBuilder = new StringBuilder();
        while (length != 0) {
            stringBuilder.append(buffer.readChar());
            length--;
        }
        uid = stringBuilder.toString();
        System.out.println("Get uid = " + uid);
    }

    @Override
    public void send(ChannelBuffer buffer) {
        int length = uid.length();
        buffer.writeShort(length);

        for (int i = 0; i < length; i++) {
            buffer.writeChar(uid.charAt(i));
        }

        System.out.println("Send uid = " + uid);
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Packet1LoginSuccess withUid(String uid) {
        this.uid = uid;
        return this;
    }
}

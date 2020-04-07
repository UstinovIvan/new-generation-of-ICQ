package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Server1LoginSuccess extends Packet {

    private final int ID = 11;

    private String uid;

    @Override
    public void get(ChannelBuffer buffer) {

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

    public Packet withUid(String uid) {
        this.uid = uid;
        return this;
    }
}

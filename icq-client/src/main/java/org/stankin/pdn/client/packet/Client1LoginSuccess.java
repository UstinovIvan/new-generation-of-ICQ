package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Client1LoginSuccess extends ClientPacket {

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
}

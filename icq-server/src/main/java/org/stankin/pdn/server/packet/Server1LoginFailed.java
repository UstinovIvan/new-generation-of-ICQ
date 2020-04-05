package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Server1LoginFailed extends Packet {

    private final int ID = 12;

    private String reason;

    public Server1LoginFailed(String reason) {
        this.reason = reason;
    }

    @Override
    public void get(ChannelBuffer buffer) {

    }

    @Override
    public void send(ChannelBuffer buffer) {
        int reasonLength = reason.length();

        buffer.writeShort(reasonLength);
        for (int i = 0; i < reasonLength; i++) {
            buffer.writeChar(reason.charAt(i));
        }
    }

    @Override
    public int getID() {
        return this.ID;
    }
}

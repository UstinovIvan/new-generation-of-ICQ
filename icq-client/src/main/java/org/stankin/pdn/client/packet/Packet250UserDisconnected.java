package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Пакет, отправляемый сервером всем парам клиента, который отключился от сервера
 */
public class Packet250UserDisconnected extends Packet {

    private final int ID = 250;

    private String username;

    @Override
    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();
        StringBuilder builder = new StringBuilder();
        while (length != 0) {
            builder.append(buffer.readChar());
            length--;
        }
        username = builder.toString();
    }

    @Override
    public void send(ChannelBuffer buffer) {
        int length = username.length();
        buffer.writeShort(length);
        for (int i = 0; i < length; i++) {
            buffer.writeChar(username.charAt(i));
        }
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Packet250UserDisconnected withUsername(String username) {
        this.username = username;
        return this;
    }
}

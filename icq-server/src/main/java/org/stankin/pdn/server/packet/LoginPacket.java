package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class LoginPacket extends Packet {

    private final int ID = 1;
    private String login;

    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(buffer.readChar());
        }

        login = builder.toString();
    }

    public void send(ChannelBuffer buffer) {
        // Тело отправки пустое, т.к. сервер не посылает этот пакет
    }


    @Override
    public int getID() {
        return this.ID;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

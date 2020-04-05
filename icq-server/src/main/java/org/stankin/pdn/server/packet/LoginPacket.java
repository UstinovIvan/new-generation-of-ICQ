package org.stankin.pdn.server.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class LoginPacket extends Packet {

    private final int ID = 1;
    private String login;
    private String password;

    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(buffer.readChar());
        }
        login = builder.toString();

        builder = new StringBuilder();
        length = buffer.readShort();
        for (int i = 0; i < length; i++) {
            builder.append(buffer.readChar());
        }

        password = builder.toString();
    }

    public void send(ChannelBuffer buffer) { //TODO: Заполнить отправку клиентом. Пакеты зеркальные для клиента и сервера
        // Тело отправки пустое, т.к. сервер не посылает этот пакет
    }


    @Override
    public int getID() {
        return this.ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

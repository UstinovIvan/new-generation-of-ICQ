package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Client1LoginPacket extends ClientPacket {

    private final int ID = 1;
    private String login;
    private String password;

    public Client1LoginPacket(String login, String password) {
        this.login = login;
        this.password = password;
    }

    Client1LoginPacket() {
    }

    public void get(ChannelBuffer buffer) {

    }

    public void send(ChannelBuffer buffer) { //TODO: Заполнить отправку клиентом. Пакеты зеркальные для клиента и сервера
        int loginLength = login.length();
        int passwordLength = password.length();

        buffer.writeShort(loginLength);
        for (int i = 0; i < loginLength; i++) {
            buffer.writeChar(login.charAt(i));
        }

        buffer.writeShort(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            buffer.writeChar(password.charAt(i));
        }
    }


    @Override
    public int getID() {
        return this.ID;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

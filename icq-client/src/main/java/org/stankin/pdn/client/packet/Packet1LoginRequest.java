package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Пакет, отправляемый на сервер с запросом авторизации
 */
public class Packet1LoginRequest extends Packet {

    private final int ID = 1;
    private String login;
    private String password;

    public Packet1LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    Packet1LoginRequest() {
    }

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

    public void send(ChannelBuffer buffer) {
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Packet1LoginRequest withLogin(String login) {
        this.login = login;
        return this;
    }

    public Packet1LoginRequest withPassword(String password) {
        this.password = password;
        return this;
    }


}

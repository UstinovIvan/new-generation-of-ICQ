package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Пакет, возвращаемый сервером при ошибке авторизации
 */
public class Packet1LoginFailed extends Packet {

    private final int ID = 12;

    private String reason;

    @Override
    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(buffer.readChar());
        }

        reason = builder.toString();
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Packet1LoginFailed withReason(String reason) {
        this.reason = reason;
        return this;
    }
}

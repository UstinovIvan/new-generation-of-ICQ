package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public abstract class TransmittablePacket extends Packet implements Transmittable {

    private String from;
    private String to;

    @Override
    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();
        to = readBuffer(length, buffer);

        length = buffer.readShort();
        from = readBuffer(length, buffer);
    }

    @Override
    public void send(ChannelBuffer buffer) {
        writeBuffer(to, buffer);

        if (from != null) {
            writeBuffer(from, buffer);
        } else {
            buffer.writeShort(0);
        }
    }

    String readBuffer(int length, ChannelBuffer buffer) {
        StringBuilder builder = new StringBuilder();

        while (length != 0) {
            builder.append(buffer.readChar());
            length--;
        }
        return builder.toString();
    }

    void writeBuffer(String string, ChannelBuffer buffer) {
        int stringLength = string.length();
        buffer.writeShort(stringLength);
        for (int i = 0; i < stringLength; i++) {
            buffer.writeChar(string.charAt(i));
        }
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }
}

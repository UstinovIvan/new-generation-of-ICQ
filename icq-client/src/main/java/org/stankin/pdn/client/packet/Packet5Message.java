package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet5Message extends TransmittablePacket {

    private final int ID = 5;

    private String message;

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void get(ChannelBuffer buffer) {
        int length = buffer.readShort();
        message = readBuffer(length, buffer);

        length = buffer.readShort();
        to = readBuffer(length, buffer);

        length = buffer.readShort();
        from = readBuffer(length, buffer);
    }

    @Override
    public void send(ChannelBuffer buffer) {
        writeBuffer(message, buffer);
        writeBuffer(to, buffer);

        if (from != null) {
            writeBuffer(from, buffer);
        } else {
            buffer.writeShort(0);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Packet5Message withMessage(String message) {
        this.message = message;
        return this;
    }
}

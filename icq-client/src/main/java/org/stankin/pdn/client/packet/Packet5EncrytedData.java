package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;
import org.stankin.pdn.crypto.api.model.EncrytedData;

public abstract class Packet5EncrytedData extends TransmittablePacket {

    private EncrytedData message;

    @Override
    public void get(ChannelBuffer buffer) {
        super.get(buffer);

        message = new EncrytedData();

        int length = buffer.readInt();
        byte[] arr = new byte[length];
        buffer.readBytes(arr);
        message.setData(arr);

        length = buffer.readInt();
        arr = new byte[length];
        buffer.readBytes(arr);
        message.setKey(arr);

        length = buffer.readInt();
        arr = new byte[length];
        buffer.readBytes(arr);
        message.setSignature(arr);

        if (buffer.readByte() == 1) {
            length = buffer.readShort();
            message.setName(readBuffer(length, buffer));
        }
    }

    @Override
    public void send(ChannelBuffer buffer) {
        super.send(buffer);
        buffer.writeInt(message.getData().length);
        buffer.writeBytes(message.getData());

        buffer.writeInt(message.getKey().length);
        buffer.writeBytes(message.getKey());

        buffer.writeInt(message.getSignature().length);
        buffer.writeBytes(message.getSignature());

        if (message.getName() != null) {
            buffer.writeByte(1);
            writeBuffer(message.getName(), buffer);
        } else {
            buffer.writeByte(0);
        }
    }

    public EncrytedData getMessage() {
        return message;
    }

    public void setMessage(EncrytedData message) {
        this.message = message;
    }


}

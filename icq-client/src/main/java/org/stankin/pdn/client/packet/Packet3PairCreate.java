package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public class Packet3PairCreate extends TransmittablePacket{

    private final int ID = 31;

    private String publicKey;
    private short needAnswer;

    @Override
    public void get(ChannelBuffer buffer) {

        needAnswer = buffer.readShort();

        int keyLength = buffer.readShort();
        this.publicKey = readBuffer(keyLength, buffer);

        int toLength = buffer.readShort();
        this.to = readBuffer(toLength, buffer);

        int fromLength = buffer.readShort();
        from = readBuffer(fromLength, buffer);
    }

    @Override
    public void send(ChannelBuffer buffer) {

        buffer.writeShort(needAnswer);

        writeBuffer(publicKey, buffer);
        writeBuffer(to, buffer);

        if (from != null) {
            writeBuffer(from, buffer);
        } else {
            buffer.writeShort(0);
        }
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Packet3PairCreate withPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public short getNeedAnswer() {
        return needAnswer;
    }

    public void setNeedAnswer(short needAnswer) {
        this.needAnswer = needAnswer;
    }

    public Packet3PairCreate withNeedAnswer(short needAnswer) {
        this.needAnswer = needAnswer;
        return this;
    }

    public Packet3PairCreate withTo(String to) {
        this.to = to;
        return this;
    }

    public Packet3PairCreate withFrom(String from) {
        this.from = from;
        return this;
    }
}

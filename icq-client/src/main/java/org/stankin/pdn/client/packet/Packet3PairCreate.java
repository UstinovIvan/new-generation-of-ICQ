package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;
import org.stankin.pdn.client.worker.PairWorker;

/**
 * Запрос, отправляемый другому пользователю, для создания пары
 */
public class Packet3PairCreate extends TransmittablePacket {

    private final int ID = 31;

    /**
     * Личный публичный ключ
     */
    private String publicKey;

    /**
     * Требуется ответ на этот пакет
     *
     * @see PairWorker#acceptPacket
     */
    private short needAnswer;

    @Override
    public void get(ChannelBuffer buffer) {
        super.get(buffer);

        needAnswer = buffer.readShort();

        int keyLength = buffer.readShort();
        this.publicKey = readBuffer(keyLength, buffer);
    }

    @Override
    public void send(ChannelBuffer buffer) {
        super.send(buffer);

        buffer.writeShort(needAnswer);
        writeBuffer(publicKey, buffer);
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
        this.setTo(to);
        return this;
    }

    public Packet3PairCreate withFrom(String from) {
        this.setFrom(from);
        return this;
    }
}

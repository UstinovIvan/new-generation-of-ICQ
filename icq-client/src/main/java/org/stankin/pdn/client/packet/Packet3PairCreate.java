package org.stankin.pdn.client.packet;

import org.jboss.netty.buffer.ChannelBuffer;
import org.stankin.pdn.client.worker.PairWorker;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Запрос, отправляемый другому пользователю, для создания пары
 */
public class Packet3PairCreate extends TransmittablePacket {

    private final int ID = 31;

    /**
     * Личный публичный ключ
     */
    private PublicKey publicKey;

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
        byte[] byteKey = new byte[keyLength];
        buffer.readBytes(byteKey);
        try {
            this.publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(byteKey));
        } catch (Exception e) {
            System.out.println("Ошибка получения ключа: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void send(ChannelBuffer buffer) {
        super.send(buffer);

        buffer.writeShort(needAnswer);
        buffer.writeShort(publicKey.getEncoded().length);
        buffer.writeBytes(publicKey.getEncoded());
    }

    @Override
    public int getID() {
        return this.ID;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public Packet3PairCreate withPublicKey(PublicKey publicKey) {
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

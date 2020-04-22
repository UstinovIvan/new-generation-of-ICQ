package org.stankin.pdn.crypto.api.handler.cipher.rsa;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.stankin.pdn.crypto.exception.InitHandlerException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class RsaHandler {

    private KeyPair keyPair;
    private Cipher cipher;

    private RsaHandler() {
    }

    public static RsaHandler init() throws InitHandlerException {
        Security.addProvider(new BouncyCastleProvider());
        RsaHandler handler = new RsaHandler();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            handler.keyPair = keyPairGenerator.generateKeyPair();
            handler.cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

            return handler;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println("Невозможно инициализировать RSA обработчик: " + e.getMessage());
            throw new InitHandlerException(e.getMessage());
        }
    }

    public byte[] encodeData(byte[] data, PublicKey publicKey) {

        try {
            cipher.init(Cipher.PUBLIC_KEY, publicKey);

            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("Ошибка RSA шифрования " + e.getMessage());
        }
        return null;
    }

    public byte[] decodeData(byte[] data) {

        try {
            cipher.init(Cipher.PRIVATE_KEY, keyPair.getPrivate());

            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("Ошибка RSA дешифрования " + e.getMessage());
        }
        return null;
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

    public Signature sign(Signature signature) throws Exception {
        signature.initSign(keyPair.getPrivate());

        return signature;
    }

}

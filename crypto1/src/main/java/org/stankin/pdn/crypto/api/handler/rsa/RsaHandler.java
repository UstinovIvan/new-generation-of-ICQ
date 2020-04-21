package org.stankin.pdn.crypto.api.handler.rsa;

import org.stankin.pdn.crypto.exception.InitHandlerException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class RsaHandler {

    private KeyPair keyPair;
    private Cipher cipher;

    private RsaHandler() {
    }

    public static RsaHandler init() throws InitHandlerException {
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

    public byte[] encodeData(byte[] data) {

        return null;
    }

    public byte[] decodeData(byte[] data, PublicKey publicKey) {

        return null;
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

}

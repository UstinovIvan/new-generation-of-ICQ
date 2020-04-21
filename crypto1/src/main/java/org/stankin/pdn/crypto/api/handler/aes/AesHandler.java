package org.stankin.pdn.crypto.api.handler.aes;

import org.stankin.pdn.crypto.exception.InitHandlerException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AesHandler {

    private KeyGenerator keyGenerator;
    private Cipher cipher;

    private AesHandler() {
    }

    public static AesHandler init() throws InitHandlerException {
        AesHandler handler = new AesHandler();

        try {
            handler.keyGenerator = KeyGenerator.getInstance("AES");
            handler.keyGenerator.init(256);
            handler.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            return handler;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println("Невозможно инициализировать AES обработчик: " + e.getMessage());
            throw new InitHandlerException(e.getMessage());
        }
    }

    public byte[] encodeData(byte[] data) {

        return null;
    }

    public byte[] decodeData(byte[] data, SecretKey secretKey) {

        return null;
    }
}

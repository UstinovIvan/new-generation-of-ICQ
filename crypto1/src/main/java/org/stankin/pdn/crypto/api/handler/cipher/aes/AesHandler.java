package org.stankin.pdn.crypto.api.handler.cipher.aes;

import org.stankin.pdn.crypto.exception.InitHandlerException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class AesHandler {

    private KeyGenerator keyGenerator;
    private Cipher cipher;

    private SecretKey secretKey;

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
        secretKey = keyGenerator.generateKey();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("Ошибка AES шифрования " + e.getMessage());
        }

        return null;
    }

    public byte[] decodeData(byte[] data, byte[] secretKeyBytes) {
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, "AES");
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("Ошибка AES дешифрования " + e.getMessage());
        }

        return null;
    }

    public byte[] getLastSecretKey() {
        byte[] temp = this.secretKey.getEncoded();
        secretKey = null;
        return temp;
    }
}

package org.stankin.pdn.crypto.api.handler.cipher;

import org.stankin.pdn.crypto.api.handler.cipher.rsa.RsaHandler;
import org.stankin.pdn.crypto.exception.InitHandlerException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;

public class SignatureHandler {

    private RsaHandler handler;
    private Signature signature;

    private SignatureHandler() {
    }

    public static SignatureHandler init(RsaHandler rsaHandler) throws InitHandlerException {
        SignatureHandler handler = new SignatureHandler();
        handler.handler = rsaHandler;

        try {
            handler.signature = Signature.getInstance("SHA256WithRSA");

            return handler;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Невозможно инициализировать обработчик подписи: " + e.getMessage());
            throw new InitHandlerException(e.getMessage());
        }
    }

    public byte[] getSignature(byte[] message) {
        try {
            signature = handler.sign(signature);
            signature.update(message);
            return signature.sign();
        } catch (Exception e) {
            System.out.println("Ошибка создания подписи " + e.getMessage());
        }

        return null;
    }

    public boolean checkSignature(byte[] origin, byte[] digitalSignature, PublicKey publicKey)  {
        try {
            this.signature.initVerify(publicKey);
            this.signature.update(origin);
            return this.signature.verify(digitalSignature);
        } catch (Exception e) {
            System.out.println("Ошибка проверки подписи " + e);
            return false;
        }
    }
}

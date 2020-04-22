package org.stankin.pdn.crypto.api;

import org.stankin.pdn.crypto.api.handler.cipher.SignatureHandler;
import org.stankin.pdn.crypto.api.handler.cipher.aes.AesHandler;
import org.stankin.pdn.crypto.api.handler.cipher.rsa.RsaHandler;
import org.stankin.pdn.crypto.api.model.EncrytedData;
import org.stankin.pdn.crypto.exception.InitHandlerException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.PublicKey;

public final class IcqCryptoApiImpl implements IcqCryptoApi {

    private AesHandler aesHandler;
    private RsaHandler rsaHandler;
    private SignatureHandler signatureHandler;

    @Override
    public EncrytedData encryteMessage(String message, PublicKey publicKey) {
        EncrytedData encrytedData = new EncrytedData();
        encrytedData.setSignature(signatureHandler.getSignature(message.getBytes()));
        encrytedData.setData(aesHandler.encodeData(message.getBytes()));
        encrytedData.setKey(rsaHandler.encodeData(aesHandler.getLastSecretKey(), publicKey));

        return encrytedData;
    }

    @Override
    public EncrytedData encryteMessage(File file, PublicKey publicKey) throws IOException {
        EncrytedData encrytedData = new EncrytedData();
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        encrytedData.setSignature(signatureHandler.getSignature(fileBytes));
        encrytedData.setData(aesHandler.encodeData(fileBytes));
        encrytedData.setKey(rsaHandler.encodeData(aesHandler.getLastSecretKey(), publicKey));
        encrytedData.setName(file.getName());

        return encrytedData;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T decryteData(EncrytedData data, PublicKey publicKey) throws Exception {
        T decodedData1;

        byte[] decodedKey = rsaHandler.decodeData(data.getKey());
        byte[] decodedData = aesHandler.decodeData(data.getData(), decodedKey);
        if (signatureHandler.checkSignature(decodedData, data.getSignature(), publicKey)) {
            if (data.getName() != null) {
                decodedData1 = (T) createAnswer(decodedData, data.getName());
            } else {
                decodedData1 = (T) createAnswer(decodedData);
            }
            return decodedData1;

        } else return null;
    }

    @Override
    public PublicKey getPublicKey() {
        return this.rsaHandler.getPublicKey();
    }

    @Override
    public void init() throws InitHandlerException {
        this.aesHandler = AesHandler.init();
        this.rsaHandler = RsaHandler.init();
        this.signatureHandler = SignatureHandler.init(rsaHandler);
    }

    private File createAnswer(byte[] data, String name) throws Exception {
        File file = new File(name);
        Files.write(file.toPath(), data);

        return file;
    }

    private String createAnswer(byte[] data) {
        return new String(data);
    }
}

package org.stankin.pdn.crypto.api;

import org.stankin.pdn.crypto.api.handler.aes.AesHandler;
import org.stankin.pdn.crypto.api.handler.rsa.RsaHandler;
import org.stankin.pdn.crypto.api.model.EncrytedData;
import org.stankin.pdn.crypto.exception.InitHandlerException;

import java.security.PublicKey;

public final class IcqCryptoApiImpl implements IcqCryptoApi {

    private AesHandler aesHandler;
    private RsaHandler rsaHandler;

    @Override
    public <T> EncrytedData<T> encryteMessage(T message) {
        return new EncrytedData<>();
    }

    @Override
    public <T> T decryteData(EncrytedData<T> data, PublicKey publicKey) {
        return null;
    }

    @Override
    public PublicKey getPublicKey() {
        return this.rsaHandler.getPublicKey();
    }

    @Override
    public void init() throws InitHandlerException {
        this.aesHandler = AesHandler.init();
        this.rsaHandler = RsaHandler.init();
    }
}

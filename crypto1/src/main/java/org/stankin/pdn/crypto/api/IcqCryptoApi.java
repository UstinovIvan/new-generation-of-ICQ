package org.stankin.pdn.crypto.api;

import org.stankin.pdn.crypto.api.model.EncrytedData;
import org.stankin.pdn.crypto.exception.InitHandlerException;

import java.security.PublicKey;

public interface IcqCryptoApi {
    <T> EncrytedData<T> encryteMessage(T message);

    <T> T decryteData(EncrytedData<T> data, PublicKey publicKey);

    PublicKey getPublicKey();

    void init() throws InitHandlerException;
}

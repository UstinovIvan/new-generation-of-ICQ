package org.stankin.pdn.crypto.api;

import org.stankin.pdn.crypto.api.model.EncrytedData;
import org.stankin.pdn.crypto.exception.InitHandlerException;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

public interface IcqCryptoApi {
    EncrytedData encryteMessage(String message, PublicKey publicKey);

    EncrytedData encryteMessage(File file, PublicKey publicKey) throws IOException;

    <T> T decryteData(EncrytedData data, PublicKey publicKey) throws Exception;

    PublicKey getPublicKey();

    void init() throws InitHandlerException;
}

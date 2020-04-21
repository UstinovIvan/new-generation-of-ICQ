package org.stankin.pdn.crypto.api.model;

import java.io.File;

public class EncrytedData<T> {

    private byte[] signature;
    private byte[] key;
    private T data;


    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

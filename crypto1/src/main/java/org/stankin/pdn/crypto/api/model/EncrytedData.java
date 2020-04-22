package org.stankin.pdn.crypto.api.model;

import java.util.Arrays;

public class EncrytedData {

    private byte[] signature;
    private byte[] key;
    private byte[] data;
    private String name;


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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EncrytedData{" +
                "signature=" + Arrays.toString(signature) +
                ", key=" + Arrays.toString(key) +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

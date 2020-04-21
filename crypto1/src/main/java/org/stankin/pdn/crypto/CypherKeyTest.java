package org.stankin.pdn.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.util.Arrays;

public class CypherKeyTest {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey aesSecretKey = keyGenerator.generateKey();

        cipher.init(Cipher.PUBLIC_KEY, keyPair.getPublic());
        byte[] encryptedKey = cipher.doFinal(aesSecretKey.getEncoded());

        cipher.init(Cipher.PRIVATE_KEY, keyPair.getPrivate());
        byte[] decryptedKey = cipher.doFinal(encryptedKey);

        SecretKey originalKey = new SecretKeySpec(decryptedKey, 0, decryptedKey.length, "AES");
        System.out.println("Orig: " + Arrays.toString(aesSecretKey.getEncoded()));
        System.out.println("Enc : " + Arrays.toString(encryptedKey));
        System.out.println("Orig: " + Arrays.toString(originalKey.getEncoded()));

        assert aesSecretKey.getEncoded() == originalKey.getEncoded();
        System.out.println("Success");
    }


}

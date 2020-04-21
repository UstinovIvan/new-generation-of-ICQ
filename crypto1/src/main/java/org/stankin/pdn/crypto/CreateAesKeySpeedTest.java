package org.stankin.pdn.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Security;

public class CreateAesKeySpeedTest {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();

            System.out.println(System.currentTimeMillis() - start + " ms");
        }
    }
}

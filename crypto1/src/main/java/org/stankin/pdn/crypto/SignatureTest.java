package org.stankin.pdn.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.Signature;
import java.util.Arrays;

public class SignatureTest {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(keyPair.getPrivate());

        long start = System.currentTimeMillis();
        File defaultFile = new File("/Users/ivanustinov/Downloads/labs onit.zip");

        byte[] fileInBytes = Files.readAllBytes(defaultFile.toPath());
        signature.update(fileInBytes);
        
        byte[] digitalSignature = signature.sign();

        System.out.println("File length: " + fileInBytes.length);
        System.out.println("Signature length: " + digitalSignature.length);

        /*
        В update подается исходная последовательность
        В verify подпись.
         */
        signature.initVerify(keyPair.getPublic());
        signature.update(fileInBytes);
        System.out.println(signature.verify(digitalSignature));

        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}

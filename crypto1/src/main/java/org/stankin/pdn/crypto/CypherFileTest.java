package org.stankin.pdn.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Security;
import java.util.Arrays;

public class CypherFileTest {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        System.out.println("AES key length = " + secretKey.getEncoded().length);

        File defaultFile = new File("/Users/ivanustinov/Downloads/labs onit.zip");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        File encryptedFile = new File("/Users/ivanustinov/Downloads/test/enc" + defaultFile.getName());
        assert encryptedFile.delete();

        encryptDecryptFile(cipher, encryptedFile, Files.readAllBytes(defaultFile.toPath()));

        File decryptedFile = new File("/Users/ivanustinov/Downloads/test/dec" + defaultFile.getName());
        assert decryptedFile.delete();
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        encryptDecryptFile(cipher, decryptedFile, Files.readAllBytes(encryptedFile.toPath()));
    }

    private static void encryptDecryptFile(Cipher cipher, File destFile, byte[] srcFileByteArr) throws Exception {
        long start = System.currentTimeMillis();

        Files.write(destFile.toPath(), new byte[0], StandardOpenOption.CREATE_NEW);
        int totalLength = srcFileByteArr.length;
        int byteLeft = totalLength;
        int currentPosition = 0;

        byte[] arr;
        while (byteLeft >= 2048) {
            arr = Arrays.copyOfRange(srcFileByteArr, currentPosition, currentPosition += 64);
            Files.write(destFile.toPath(), cipher.update(arr), StandardOpenOption.APPEND);
            byteLeft -= 2048;
        }
        arr = Arrays.copyOfRange(srcFileByteArr, currentPosition, totalLength);
        Files.write(destFile.toPath(), cipher.doFinal(arr), StandardOpenOption.APPEND);

        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}

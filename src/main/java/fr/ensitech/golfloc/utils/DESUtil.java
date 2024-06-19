package fr.ensitech.golfloc.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DESUtil {
    private static final String ALGORITHM = "DES";
    private static final byte[] keyValue;

    static {
        String key = System.getenv("DES_SECRET_KEY");
        System.out.println("DES_SECRET_KEY: " + key);
        if (key == null || key.length() != 8) {
            throw new IllegalArgumentException("The DES secret key must be 8 characters long and set in the DES_SECRET_KEY environment variable.");
        }
        keyValue = key.getBytes();
    }

    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String decrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(data);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue);
    }
}

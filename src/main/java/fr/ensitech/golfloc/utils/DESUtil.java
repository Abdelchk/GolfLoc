package fr.ensitech.golfloc.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DESUtil {
    private static final String ALGORITHM = "DES";
    private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";
    private static final byte[] keyValue;

    static {
        String key = "GoLfLoc1";
        System.out.println("DES_SECRET_KEY: " + key);
        if (key == null || key.length() != 8) {
            throw new IllegalArgumentException("La clé secrète DES doit être doit être d'au moins 8 caractères "
            		+ "et doit être initialisée dans la variable d'environnement DES_SECRET_KEY.");
        }
        keyValue = key.getBytes();
    }

    public static String encrypt(String data) throws Exception {
        System.out.println("Original Data: " + data);
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(data.getBytes());
        String encodedValue = Base64.getEncoder().encodeToString(encryptedValue);
        System.out.println("Encrypted Data: " + encodedValue);
        return encodedValue;
    }

    public static String decrypt(String data) throws Exception {
        System.out.println("Encrypted Data: " + data);
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(data);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        String decodedString = new String(decryptedValue);
        System.out.println("Decrypted Data: " + decodedString);
        return decodedString;
    }
}

package com.example.e_commercewithapi.data.dataSourse.Local.KeyStore.EncryptionManager;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class EncryptionManager implements EncryptionManagerImpl {

    private static final String KEY_ALIAS = "MyAESKeyAlias";
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final int IV_SIZE = 12; // 96-bit IV
    private static final int GCM_TAG_LENGTH = 128; // 128-bit tag

    private void generateKeyIfNeeded() throws CertificateException, IOException,
            NoSuchAlgorithmException, KeyStoreException, InvalidAlgorithmParameterException,
            NoSuchProviderException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);

        if (!keyStore.containsAlias(KEY_ALIAS)) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);

            keyGenerator.init(new KeyGenParameterSpec.Builder(
                    KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .build());

            keyGenerator.generateKey();
        }
    }

    @Override
    public String encrypt(String plainText) throws Exception {
        generateKeyIfNeeded();

        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);

        SecretKey secretKey = ((KeyStore.SecretKeyEntry)
                keyStore.getEntry(KEY_ALIAS, null)).getSecretKey();

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] iv = cipher.getIV(); // Initialization vector
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        byte[] combined = concatenate(iv, encryptedBytes);

        return Base64.encodeToString(combined, Base64.NO_WRAP);
    }

    @Override
    public String decrypt(String cipherText) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);

        SecretKey secretKey = ((KeyStore.SecretKeyEntry)
                keyStore.getEntry(KEY_ALIAS, null)).getSecretKey();

        byte[] combined = Base64.decode(cipherText, Base64.NO_WRAP);
        byte[] iv = Arrays.copyOfRange(combined, 0, IV_SIZE);
        byte[] encryptedBytes = Arrays.copyOfRange(combined, IV_SIZE, combined.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private byte[] concatenate(byte[] iv, byte[] encryptedBytes) {
        byte[] combined = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);
        return combined;
    }
    public boolean hasKey() {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            keyStore.load(null);
            return keyStore.containsAlias(KEY_ALIAS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

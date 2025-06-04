package com.example.e_commercewithapi.data.dataSourse.Local.KeyStore.EncryptionManager;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface EncryptionManagerImpl {
     String encrypt(String plainText) throws  Exception;
     String decrypt(String cipherText) throws  Exception;
     public boolean hasKey();
}

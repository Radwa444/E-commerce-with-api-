package com.example.e_commercewithapi.data.repository.user.repository;

import com.example.e_commercewithapi.data.dataSourse.Local.KeyStore.EncryptionManager.EncryptionManager;
import com.example.e_commercewithapi.data.dataSourse.Local.KeyStore.EncryptionManager.EncryptionManagerImpl;
import com.example.e_commercewithapi.data.dataSourse.Local.SharedPreferences.SharedPreferencesManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository implements UserRepositoryImpl {
    SharedPreferencesManager sharedPreferencesManager;
    EncryptionManagerImpl encryptionManager;
    @Inject
    UserRepository(SharedPreferencesManager sharedPreferencesManager,EncryptionManagerImpl encryptionManager){
        this.sharedPreferencesManager=sharedPreferencesManager;
        this.encryptionManager=encryptionManager;
    }

    @Override
    public void setToken(String token) {
        try {
            sharedPreferencesManager.setToken(encrypt(token));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getToken() {
        try {
            return decrypt(sharedPreferencesManager.getToken());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean checkLogin() {
        String encryptedToken=sharedPreferencesManager.getToken();
        return encryptedToken!=null && encryptedToken.isEmpty();
    }

    @Override
    public String encrypt(String plainText) throws Exception {
        return encryptionManager.encrypt(plainText);
    }

    @Override
    public String decrypt(String cipherText) throws Exception {
        return encryptionManager.decrypt(cipherText);
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        try {
            sharedPreferencesManager.setRefreshToken(encrypt(refreshToken));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRefreshToken() {
       try {
          return decrypt( sharedPreferencesManager.getRefreshToken());
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void logout() {
        sharedPreferencesManager.setToken(null);
        sharedPreferencesManager.setRefreshToken(null);
    }
}

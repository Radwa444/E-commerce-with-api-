package com.example.e_commercewithapi.data.repository.user.repository;
import com.example.e_commercewithapi.data.dataSourse.Local.KeyStore.EncryptionManager.EncryptionManagerImpl;
import com.example.e_commercewithapi.data.dataSourse.Local.SharedPreferences.SharedPreferencesManager;
import com.example.e_commercewithapi.data.dataSourse.remote.Api.ApiService;
import com.example.e_commercewithapi.data.models.local.SignUp.ResponseSignUp;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class UserRepository implements UserRepositoryImpl {
   private final ApiService apiService;
  private final   SharedPreferencesManager sharedPreferencesManager;
  private final   EncryptionManagerImpl encryptionManager;
    @Inject
    UserRepository(SharedPreferencesManager sharedPreferencesManager,EncryptionManagerImpl encryptionManager,ApiService apiService){
        this.sharedPreferencesManager=sharedPreferencesManager;
        this.encryptionManager=encryptionManager;
        this.apiService=apiService;
    }

    @Override
    public void setToken(String token) {
        try {
            if (!encryptionManager.hasKey()) {
                sharedPreferencesManager.setToken(encrypt(token));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public String getToken() {
        try {
            String token=sharedPreferencesManager.getToken();
            if ((token == null) || token.isEmpty())return null;
            return decrypt(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Boolean checkLogin() {
        String encryptedToken=sharedPreferencesManager.getToken();
        return encryptedToken!=null && !encryptedToken.isEmpty();
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
           String token=sharedPreferencesManager.getRefreshToken();
           if ((token == null) || token.isEmpty())return null;
           return decrypt(token);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void logout() {
        sharedPreferencesManager.setToken(null);
        sharedPreferencesManager.setRefreshToken(null);
    }

    @Override
    public Single<ResponseSignUp> getUserProfile() {
        try {
            return apiService.getUserProfile("Bearer " +getToken());
        } catch (Exception e) {
            return Single.error(e);
        }
    }

}

package com.example.e_commercewithapi.data.repository.user.repository;

import com.example.e_commercewithapi.data.models.local.SignUp.ResponseSignUp;

import io.reactivex.Single;

public interface UserRepositoryImpl {
    public void setToken(String token);
    public String getToken();
    public Boolean checkLogin();
    String encrypt(String plainText) throws  Exception;
    String decrypt(String cipherText) throws  Exception;
    public void setRefreshToken(String refreshToken);
    String getRefreshToken();
    void logout();
    Single<ResponseSignUp> getUserProfile();
}

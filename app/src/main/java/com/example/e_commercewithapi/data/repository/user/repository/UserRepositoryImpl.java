package com.example.e_commercewithapi.data.repository.user.repository;

public interface UserRepositoryImpl {
    public void setToken(String token);
    public String getToken();
    public Boolean checkLogin();
    String encrypt(String plainText) throws  Exception;
    String decrypt(String cipherText) throws  Exception;
    public void setRefreshToken(String refreshToken);
    String getRefreshToken();
    void logout();
}

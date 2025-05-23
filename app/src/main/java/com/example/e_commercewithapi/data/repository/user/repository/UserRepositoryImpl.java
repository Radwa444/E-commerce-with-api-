package com.example.e_commercewithapi.data.repository.user.repository;

public interface UserRepositoryImpl {
    public void setToken(String token);
    public String getToken();
    public Boolean checkLogin(String token);
}

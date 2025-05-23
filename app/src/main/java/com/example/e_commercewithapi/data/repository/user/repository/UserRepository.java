package com.example.e_commercewithapi.data.repository.user.repository;

import com.example.e_commercewithapi.data.dataSourse.Local.SharedPreferences.SharedPreferencesManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository implements UserRepositoryImpl {
    SharedPreferencesManager sharedPreferencesManager;
    @Inject
    UserRepository(SharedPreferencesManager sharedPreferencesManager){
        this.sharedPreferencesManager=sharedPreferencesManager;
    }

    @Override
    public void setToken(String token) {
         sharedPreferencesManager.setToken(token);

    }

    @Override
    public String getToken() {
        return sharedPreferencesManager.getToken();
    }

    @Override
    public Boolean checkLogin(String token) {
        token=sharedPreferencesManager.getToken();

        return sharedPreferencesManager.checkLogin(token);
    }
}

package com.example.e_commercewithapi.data.repository.auth.Login;

import com.example.e_commercewithapi.data.dataSourse.remote.Api.ApiService;
import com.example.e_commercewithapi.data.models.Login.RequestLogin;
import com.example.e_commercewithapi.data.models.Login.ResponseLogin;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginRepositoryImpl implements  LoginRepository{
    ApiService apiService;
    @Inject
    LoginRepositoryImpl( ApiService apiService){
        this.apiService=apiService;
    }
    @Override
    public Single<ResponseLogin> login(RequestLogin requestLogin) {
        return apiService.login(requestLogin);
    }
}

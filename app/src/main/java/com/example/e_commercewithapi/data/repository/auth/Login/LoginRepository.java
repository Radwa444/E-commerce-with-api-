package com.example.e_commercewithapi.data.repository.auth.Login;

import com.example.e_commercewithapi.data.models.Login.RequestLogin;
import com.example.e_commercewithapi.data.models.Login.ResponseLogin;

import io.reactivex.Single;

public interface LoginRepository {
    Single<ResponseLogin> login(RequestLogin requestLogin);
}

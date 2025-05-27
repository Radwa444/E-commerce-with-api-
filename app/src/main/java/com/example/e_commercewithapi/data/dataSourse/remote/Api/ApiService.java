package com.example.e_commercewithapi.data.dataSourse.remote.Api;

import com.example.e_commercewithapi.data.models.Login.RequestLogin;
import com.example.e_commercewithapi.data.models.Login.ResponseLogin;
import com.example.e_commercewithapi.data.models.SignUp.RequestSignUp;
import com.example.e_commercewithapi.data.models.SignUp.ResponseSignUp;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/login")
    Single<ResponseLogin> login(@Body RequestLogin requestLogin);
    @POST("users/")
    Single<ResponseSignUp> signUp(@Body RequestSignUp requestSignUp);

}

package com.example.e_commercewithapi.data.dataSourse.remote.Api;

import android.content.Context;

import com.example.e_commercewithapi.Config;
import com.example.e_commercewithapi.data.models.Login.RequestLogin;
import com.example.e_commercewithapi.data.models.Login.ResponseLogin;
import com.example.e_commercewithapi.data.models.SignUp.RequestSignUp;
import com.example.e_commercewithapi.data.models.SignUp.ResponseSignUp;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST(Config.LOGIN_URL)
    Single<ResponseLogin> login(@Body RequestLogin requestLogin);
    @POST(Config.USERS_URL)
    @Headers("Content-Type: application/json;charset=UTF-8")
    Single<ResponseSignUp> signUp(@Body RequestSignUp requestSignUp);
    @GET(Config.TOKEN_URL)
    Single<ResponseSignUp> getUserProfile(@Header("Authorization")String token);

}

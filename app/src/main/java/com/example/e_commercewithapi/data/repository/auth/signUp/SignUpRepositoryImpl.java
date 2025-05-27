package com.example.e_commercewithapi.data.repository.auth.signUp;

import com.example.e_commercewithapi.data.dataSourse.remote.Api.ApiService;
import com.example.e_commercewithapi.data.models.SignUp.RequestSignUp;
import com.example.e_commercewithapi.data.models.SignUp.ResponseSignUp;

import javax.inject.Inject;

import io.reactivex.Single;

public class SignUpRepositoryImpl implements SignUpRepository{
    ApiService apiService;
   @Inject
   SignUpRepositoryImpl(ApiService apiService){
       this.apiService=apiService;
   }
    @Override
    public Single<ResponseSignUp> signUp(RequestSignUp requestSignUp) {
        return apiService.signUp(requestSignUp);
    }
}

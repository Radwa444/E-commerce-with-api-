package com.example.e_commercewithapi.data.repository.auth.signUp;

import com.example.e_commercewithapi.data.models.SignUp.RequestSignUp;
import com.example.e_commercewithapi.data.models.SignUp.ResponseSignUp;

import io.reactivex.Single;

public interface SignUpRepository {
    Single<ResponseSignUp> signUp(RequestSignUp requestSignUp);
}

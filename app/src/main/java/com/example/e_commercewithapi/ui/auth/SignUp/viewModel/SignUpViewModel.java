package com.example.e_commercewithapi.ui.auth.SignUp.viewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.models.SignUp.RequestSignUp;
import com.example.e_commercewithapi.data.repository.auth.signUp.SignUpRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class SignUpViewModel extends ViewModel {
    SignUpRepository signUpRepository;
    private final MutableLiveData<String> _signUpStatus=new MutableLiveData<>();
    public LiveData<String>  signUpStatus=_signUpStatus;

    @Inject
    SignUpViewModel(SignUpRepository signUpRepository){
        this.signUpRepository=signUpRepository;
    }
   @SuppressLint("CheckResult")
   public void signUp(RequestSignUp requestSignUp){
        if (requestSignUp.getEmail().isEmpty() && requestSignUp.getName().isEmpty() && requestSignUp.getPassword().isEmpty()){
         _signUpStatus.setValue("Please fill in all fields.");
        }else if (requestSignUp.getEmail().isEmpty()){
            _signUpStatus.setValue("Email is required.");
        }else if (requestSignUp.getPassword().isEmpty()){
           _signUpStatus.setValue("Password is required.");
        } else if (requestSignUp.getName().isEmpty()) {
            _signUpStatus.setValue("Name is required.");

        }else{
            signUpRepository.signUp(requestSignUp).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(
                            responseSignUp -> {
                                Log.d("SignUpSuss",responseSignUp.toString());
                                _signUpStatus.setValue("Sign up successful!");
                            },throwable -> Log.e("SignUpError",throwable.toString())

                    );
        }

   }


}

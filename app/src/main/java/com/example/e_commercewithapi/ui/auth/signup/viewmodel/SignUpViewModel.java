package com.example.e_commercewithapi.ui.auth.signup.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.models.local.SignUp.RequestSignUp;
import com.example.e_commercewithapi.data.repository.auth.signUp.SignUpRepository;
import com.example.e_commercewithapi.utils.UiStates;
import com.example.e_commercewithapi.utils.ValidationExtenstions;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class SignUpViewModel extends ViewModel {
    SignUpRepository signUpRepository;
    private final MutableLiveData<UiStates> _signUpUiStates = new MutableLiveData<>();
    public LiveData<UiStates> signUpUiStates = _signUpUiStates;

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> re_password = new MutableLiveData<>();

    @Inject
    SignUpViewModel(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @SuppressLint("CheckResult")
    public void signUp() {
        String nameVal = name.getValue() != null ? name.getValue().trim() : "";
        String emailVal = email.getValue() != null ? email.getValue().trim() : "";
        String passVal = password.getValue() != null ? password.getValue().trim() : "";
        String rePassVal = re_password.getValue() != null ? re_password.getValue().trim() : "";
        if (nameVal.isEmpty() || emailVal.isEmpty() || passVal.isEmpty() || rePassVal.isEmpty()) {
            _signUpUiStates.setValue(new UiStates.Error("Please fill in all fields."));
            return;
        }
        if (!ValidationExtenstions.isEmailValid(emailVal)) {
            _signUpUiStates.setValue(new UiStates.Error("Email is required."));
            return;
        }
        if (!ValidationExtenstions.isPasswordValid(passVal)) {
            _signUpUiStates.setValue(new UiStates.Error("Password must be at least 6 characters."));
            return;
        }

        if (!isRePasswordValid(passVal, rePassVal)) {
            _signUpUiStates.setValue(new UiStates.Error("Passwords do not match. Please re-enter."));
            return;

        }
        _signUpUiStates.postValue(new UiStates.Loading());
        RequestSignUp requestSignUp = new RequestSignUp(nameVal, emailVal, passVal, "https://media.istockphoto.com/id/1495088043/vector/user-profile-icon-avatar-or-person-icon-profile-picture-portrait-symbol-default-portrait.jpg?s=1024x1024&w=is&k=20&c=oGqYHhfkz_ifeE6-dID6aM7bLz38C6vQTy1YcbgZfx8=");

        signUpRepository.signUp(requestSignUp).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        responseSignUp -> _signUpUiStates.setValue(new UiStates.Success("Sign up successful!"))
                        ,
                        throwable -> _signUpUiStates.setValue(new UiStates.Error("Sign up failed: " + throwable.getMessage()))


                );


    }



    private Boolean isRePasswordValid(String password, String rePassword) {
        return password.equals(rePassword);
    }


}

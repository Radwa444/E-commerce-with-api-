package com.example.e_commercewithapi.ui.auth.login.viewmodel;
import android.annotation.SuppressLint;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.e_commercewithapi.data.models.Login.RequestLogin;
import com.example.e_commercewithapi.data.repository.auth.Login.LoginRepository;
import com.example.e_commercewithapi.data.repository.user.repository.UserRepositoryImpl;
import com.example.e_commercewithapi.utils.UiStates;
import com.example.e_commercewithapi.utils.ValidationExtenstions;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    UserRepositoryImpl userRepository;
    LoginRepository loginRepository;
    private final MutableLiveData<UiStates> _loginUiStates = new MutableLiveData<>();
    public LiveData<UiStates> loginUiStates = _loginUiStates;
    public MutableLiveData<String> email = new MutableLiveData<>("");
    public MutableLiveData<String> password = new MutableLiveData<>("");
    public String token;

    @Inject
    public LoginViewModel(UserRepositoryImpl userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    public String getToken() {
        return userRepository.getToken();
    }

    public Boolean checkLogin(String token) {
        return userRepository.checkLogin(token);

    }

    @SuppressLint("CheckResult")
    public void login() {
        String emailVal = email.getValue() != null ? email.getValue().trim() : "";
        String passVal = password.getValue() != null ? password.getValue().trim() : "";
        if (emailVal.isEmpty() || passVal.isEmpty()) {
            _loginUiStates.setValue(new UiStates.Error("Please fill in all fields."));
            return;
        }
        if (!ValidationExtenstions.isEmailValid(emailVal)) {
            _loginUiStates.setValue(new UiStates.Error("Email is required."));
            return;
        }
        if (!ValidationExtenstions.isPasswordValid(passVal)) {
            _loginUiStates.setValue(new UiStates.Error("Password must be at least 6 characters."));
            return;
        }
        _loginUiStates.setValue(new UiStates.Loading());
        RequestLogin requestLogin = new RequestLogin(emailVal, passVal);

        loginRepository.login(requestLogin).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        repository -> {
                            _loginUiStates.setValue(new UiStates.Success("Login successful!"));
                            token = repository.getAccess_token();
                            userRepository.setToken(token);
                            Log.d("LoginViewModel", userRepository.getToken());
                        },
                        throwable -> {
                            _loginUiStates.setValue(new UiStates.Error("Login failed: " + throwable.getMessage()));
                        }

                );


    }


}

package com.example.e_commercewithapi.ui.auth.Login.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.models.Login.RequestLogin;
import com.example.e_commercewithapi.data.models.Login.ResponseLogin;
import com.example.e_commercewithapi.data.repository.auth.Login.LoginRepository;
import com.example.e_commercewithapi.data.repository.user.repository.UserRepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Single;

@HiltViewModel
public class LoginViewModel extends ViewModel {

UserRepositoryImpl userRepository;
LoginRepository loginRepository;

@Inject
public LoginViewModel(UserRepositoryImpl userRepository, LoginRepository loginRepository){
    this.userRepository =userRepository;
    this.loginRepository=loginRepository;
}
public String getToken(){
    return userRepository.getToken();
}
public void saveToken(String token){
     userRepository.setToken(token);
}
public Boolean checkLogin(String token){
    return userRepository.checkLogin(token);

}
public Single<ResponseLogin> login(RequestLogin requestLogin){
    return loginRepository.login(requestLogin);
    }
}

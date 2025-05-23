package com.example.e_commercewithapi.ui.auth.Login.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.repository.user.repository.UserRepositoryImpl;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

UserRepositoryImpl userRepository;
@Inject
public LoginViewModel(UserRepositoryImpl userRepository){
    this.userRepository =userRepository;
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
}

package com.example.e_commercewithapi.ui.auth.Login.fargment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.e_commercewithapi.MainActivity;
import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.data.models.Login.RequestLogin;
import com.example.e_commercewithapi.databinding.FragmentLoginBinding;
import com.example.e_commercewithapi.ui.auth.Login.viewModel.LoginViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding=FragmentLoginBinding.inflate(inflater,container,false);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        RequestLogin requestLogin=new RequestLogin("ra@gmail.com","1234567");
        loginViewModel.checkLogin(String.valueOf(requestLogin));
        toSignUp();
        return binding.getRoot();


    }

    private void toSignUp() {
        binding.onClickSignup.setOnClickListener(it->{
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_loginFragment_to_signUpFragment);
        });
    }
}
package com.example.e_commercewithapi.ui.auth.login.fargment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.databinding.FragmentLoginBinding;
import com.example.e_commercewithapi.ui.auth.login.viewmodel.LoginViewModel;
import com.example.e_commercewithapi.utils.UiStates;

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
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        observeViewModel();
        setupLoginClick();

        toSignUp();
        return binding.getRoot();


    }

    private void setupLoginClick() {
        binding.onClickLogin.setOnClickListener(
                view -> {
                    loginViewModel.login();
                    loginViewModel.getUserProfile();
                }
        );
    }

    private void observeViewModel() {
        View loadingCard = binding.getRoot().findViewById(R.id.loadingCardLogin);
        loginViewModel.loginUiStates.observe(getViewLifecycleOwner(),uiStates -> {
            if(uiStates instanceof UiStates.Loading){
                loadingCard.setVisibility(View.VISIBLE);
            } else if (uiStates instanceof UiStates.Error) {
                loadingCard.setVisibility(View.GONE);
                Toast.makeText(getContext(), ((UiStates.Error) uiStates).error, Toast.LENGTH_SHORT).show();

            } else if (uiStates instanceof  UiStates.Success) {
                loadingCard.setVisibility(View.GONE);
                Toast.makeText(getContext(),((UiStates.Success) uiStates).message.toString(),Toast.LENGTH_LONG).show();

            }
                }

                );
    }

    private void toSignUp() {
        binding.onClickSignup.setOnClickListener(it->{
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_loginFragment_to_signUpFragment);
        });
    }
}
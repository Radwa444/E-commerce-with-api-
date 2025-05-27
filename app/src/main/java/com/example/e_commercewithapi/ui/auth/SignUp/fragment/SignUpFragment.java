package com.example.e_commercewithapi.ui.auth.SignUp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commercewithapi.data.models.SignUp.RequestSignUp;
import com.example.e_commercewithapi.databinding.FragmentSignUpBinding;
import com.example.e_commercewithapi.ui.auth.SignUp.viewModel.SignUpViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    SignUpViewModel signUpViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding=FragmentSignUpBinding.inflate(inflater,container,false);
       signUpViewModel=new ViewModelProvider(this).get(SignUpViewModel.class);
        //RequestSignUp requestSignUp=new RequestSignUp(binding.etNameSignUp.toString(),binding.etEmailSignUp.toString(),binding.etPasswordSignUp.toString(),"https://media.istockphoto.com/id/1495088043/vector/user-profile-icon-avatar-or-person-icon-profile-picture-portrait-symbol-default-portrait.jpg?s=1024x1024&w=is&k=20&c=oGqYHhfkz_ifeE6-dID6aM7bLz38C6vQTy1YcbgZfx8=");
        observeViewModel();
        setupSignUpClick();


       return binding.getRoot();
    }

    private void observeViewModel() {
        signUpViewModel.signUpStatus.observe(getViewLifecycleOwner(),status->{
            Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
        });
    }

    private void setupSignUpClick() {
        binding.buttonSignUp.setOnClickListener(view -> {
            String name=binding.etNameSignUp.getText().toString().trim();
            String email=binding.etEmailSignUp.getText().toString().trim();
            String password=binding.etPasswordSignUp.getText().toString().trim();
            RequestSignUp requestSignUp=new RequestSignUp(name,email,password,"https://media.istockphoto.com/id/1495088043/vector/user-profile-icon-avatar-or-person-icon-profile-picture-portrait-symbol-default-portrait.jpg?s=1024x1024&w=is&k=20&c=oGqYHhfkz_ifeE6-dID6aM7bLz38C6vQTy1YcbgZfx8=");

            signUpViewModel.signUp(requestSignUp);
        });

    }
}
package com.example.e_commercewithapi.ui.auth.signup.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.data.models.SignUp.RequestSignUp;
import com.example.e_commercewithapi.databinding.FragmentSignUpBinding;
import com.example.e_commercewithapi.ui.auth.signup.viewmodel.SignUpViewModel;
import com.example.e_commercewithapi.utils.UiStates;

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
        binding.setViewModel(signUpViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        observeViewModel();
        setupSignUpClick();


       return binding.getRoot();
    }

    public void observeViewModel() {
        View loadingCard = binding.getRoot().findViewById(R.id.loadingCard);
        signUpViewModel.signUpUiStates.observe(getViewLifecycleOwner(), state -> {
            if (state instanceof UiStates.Loading) {
                loadingCard.setVisibility(View.VISIBLE);
            } else if (state instanceof UiStates.Success) {
                loadingCard.setVisibility(View.GONE);
                Toast.makeText(getContext(), ((UiStates.Success) state).message, Toast.LENGTH_SHORT).show();
            } else if (state instanceof UiStates.Error) {
                loadingCard.setVisibility(View.GONE);
                Toast.makeText(getContext(), ((UiStates.Error) state).error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSignUpClick() {
        binding.buttonSignUp.setOnClickListener(view -> {
//            String name=binding.etNameSignUp.getText().toString().trim();
//            String email=binding.etEmailSignUp.getText().toString().trim();
//            String password=binding.etPasswordSignUp.getText().toString().trim();
//            if(binding.etPasswordSignUp.getText().equals(binding.etReEnterYourPasswordLogin.getText())){
//                RequestSignUp requestSignUp=new RequestSignUp(name,email,password,"https://media.istockphoto.com/id/1495088043/vector/user-profile-icon-avatar-or-person-icon-profile-picture-portrait-symbol-default-portrait.jpg?s=1024x1024&w=is&k=20&c=oGqYHhfkz_ifeE6-dID6aM7bLz38C6vQTy1YcbgZfx8=");
             signUpViewModel.signUp();
//            }else {
//                Toast.makeText(getContext(),"Passwords do not match. Please re-enter",Toast.LENGTH_LONG).show();
//            }


        });

    }
}
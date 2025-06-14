package com.example.e_commercewithapi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commercewithapi.databinding.FragmentProductDetailsBinding;


public class ProductDetailsFragment extends Fragment {
    FragmentProductDetailsBinding binding;
    private static String TAG="ProductDetailsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentProductDetailsBinding.inflate(getLayoutInflater(),container,false);
        int productId=ProductDetailsFragmentArgs.fromBundle(getArguments()).getProductId();
        Log.d(TAG, String.valueOf(productId));
        return binding.getRoot();

    }
}
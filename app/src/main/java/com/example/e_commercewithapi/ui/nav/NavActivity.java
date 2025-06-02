package com.example.e_commercewithapi.ui.nav;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.e_commercewithapi.databinding.ActivityNavBinding;

import com.example.e_commercewithapi.R;

public class NavActivity extends AppCompatActivity {


private ActivityNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityNavBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

       }}
package com.example.e_commercewithapi.ui.nav;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.databinding.ActivityNavBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NavActivity extends AppCompatActivity {

    private ActivityNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());

        binding.bottomNav.setItemIconTintList(null);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView2);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);// ده ID بتاع NavHostFragment
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }


}
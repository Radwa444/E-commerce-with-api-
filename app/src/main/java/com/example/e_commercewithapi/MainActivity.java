package com.example.e_commercewithapi;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commercewithapi.databinding.ActivityMainBinding;
import com.example.e_commercewithapi.ui.auth.AuthActivity;
import com.example.e_commercewithapi.ui.auth.login.viewmodel.LoginViewModel;
import com.example.e_commercewithapi.ui.nav.NavActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
private ActivityMainBinding activityMainBinding;
private String data="radwa";
private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        activityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=activityMainBinding.getRoot();
        setContentView(view);
        loginViewModel=new ViewModelProvider(this).get(LoginViewModel.class);
        initSplashSreen();
        isLogin();

    }




    private void isLogin() {
        if(loginViewModel.getToken()!=null){
           toAuthActivity();
        }else {
            toAuthActivity();
        }
    }
    private void toNavActivity(){
        Intent intent=new Intent(MainActivity.this, NavActivity.class);
        startActivity(intent
        );
    }


    private void toAuthActivity() {

                    Intent intent=new Intent(MainActivity.this, AuthActivity.class);
                    startActivity(intent);

    }



    private void initSplashSreen() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
            SplashScreen.installSplashScreen(this);
            getSplashScreen().setOnExitAnimationListener(splashScreenView -> {
                final ObjectAnimator slideUp = ObjectAnimator.ofFloat(
                        splashScreenView,
                        View.TRANSLATION_Y,
                        0f,
                        -splashScreenView.getHeight()
                );
                slideUp.setInterpolator(new AnticipateInterpolator());
                slideUp.setDuration(1000L);

                // Call SplashScreenView.remove at the end of your custom animation.
                slideUp.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        splashScreenView.remove();
                    }
                });

                // Run your animation.
                slideUp.start();
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityMainBinding=null;
    }
}
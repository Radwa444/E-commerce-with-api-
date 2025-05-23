package com.example.e_commercewithapi;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
@HiltAndroidApp
public class MyApplication extends Application {
    private static String TAG="My App";
    @Override
    public void onCreate() {
        super.onCreate();
        listenToNetworkConnectivity();

    }

    @SuppressLint("CheckResult")
    private void listenToNetworkConnectivity() {
        ReactiveNetwork
                .observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToInternet -> {
                    Log.d(TAG,isConnectedToInternet.toString());
                });
    }

}

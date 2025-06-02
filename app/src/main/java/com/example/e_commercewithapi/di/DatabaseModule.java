package com.example.e_commercewithapi.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.e_commercewithapi.Config;
import com.example.e_commercewithapi.data.dataSourse.Local.KeyStore.EncryptionManager.EncryptionManager;
import com.example.e_commercewithapi.data.dataSourse.Local.KeyStore.EncryptionManager.EncryptionManagerImpl;
import com.example.e_commercewithapi.data.dataSourse.Local.SharedPreferences.SharedPreferencesManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public SharedPreferences providerSharedPerferences(@ApplicationContext Context context){
         return context.getSharedPreferences(Config.E_COMMERCE_PREFERCES,Context.MODE_PRIVATE);
    }
    @Provides
    @Singleton
    public SharedPreferencesManager sharedPreferencesManager(SharedPreferences sharedPreferences){
        return new SharedPreferencesManager(sharedPreferences);
    }

    @Provides
    @Singleton
    public EncryptionManagerImpl encryptionManager(){
        return new EncryptionManager();
    }

}

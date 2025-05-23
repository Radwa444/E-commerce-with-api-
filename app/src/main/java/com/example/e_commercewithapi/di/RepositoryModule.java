package com.example.e_commercewithapi.di;

import com.example.e_commercewithapi.data.repository.user.repository.UserRepository;
import com.example.e_commercewithapi.data.repository.user.repository.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    @Binds
    @Singleton
    public abstract UserRepositoryImpl providerUserRepository(UserRepository userRepository);


}

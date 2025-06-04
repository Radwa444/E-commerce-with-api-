package com.example.e_commercewithapi.di;

import com.example.e_commercewithapi.data.repository.Categories.CategoriesRepository;
import com.example.e_commercewithapi.data.repository.Categories.CategoriesRepositoryImpl;
import com.example.e_commercewithapi.data.repository.auth.Login.LoginRepository;
import com.example.e_commercewithapi.data.repository.auth.Login.LoginRepositoryImpl;
import com.example.e_commercewithapi.data.repository.auth.signUp.SignUpRepository;
import com.example.e_commercewithapi.data.repository.auth.signUp.SignUpRepositoryImpl;
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
    @Binds
    @Singleton
    public abstract LoginRepository providerLoginRepository(LoginRepositoryImpl loginRepository);
    @Binds
    @Singleton
    public abstract SignUpRepository providerSignUpRepository(SignUpRepositoryImpl signUpRepository);
    @Binds
    @Singleton
    public abstract CategoriesRepository providerCategoriesRepository(CategoriesRepositoryImpl categoriesRepository);


}

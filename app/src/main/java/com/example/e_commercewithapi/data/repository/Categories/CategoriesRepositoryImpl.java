package com.example.e_commercewithapi.data.repository.Categories;

import android.util.Log;

import com.example.e_commercewithapi.data.dataSourse.remote.Api.ApiService;
import com.example.e_commercewithapi.data.models.Categories.ResponseCategories;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
@Singleton
public class CategoriesRepositoryImpl implements CategoriesRepository{
    ApiService apiService;
    @Inject
    CategoriesRepositoryImpl(ApiService apiService){
        this.apiService=apiService;    }
    @Override
    public Observable<List<ResponseCategories>> getCategories() {

            return apiService.getCategories();


    }
}

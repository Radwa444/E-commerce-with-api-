package com.example.e_commercewithapi.data.repository.Categories;

import com.example.e_commercewithapi.data.dataSourse.remote.Api.ApiService;
import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class CategoriesRepositoryImpl implements CategoriesRepository{
    ApiService apiService;
    @Inject
    CategoriesRepositoryImpl(ApiService apiService){
        this.apiService=apiService;    }
    @Override
    public Observable<List<Category>> getCategories() {

            return apiService.getCategories();


    }

    @Override
    public Single<Category> getCategoryById(int categoryId) {
        return apiService.getCategoryById(categoryId);
    }

    @Override
    public Single<Category> getCategoryBySlug(String categorySlug) {
        return apiService.getCategoryBySlug(categorySlug);
    }

    @Override
    public Observable<List<Product>> getAllProductByCategory(int categoryId) {
        return apiService.getAllProductByCategory(categoryId);
    }
}

package com.example.e_commercewithapi.data.repository.Categories;

import com.example.e_commercewithapi.data.models.Categories.ResponseCategories;

import java.util.List;

import io.reactivex.Observable;

public interface CategoriesRepository {
    Observable<List<ResponseCategories>> getCategories();
}

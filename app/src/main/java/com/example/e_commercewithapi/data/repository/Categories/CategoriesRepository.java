package com.example.e_commercewithapi.data.repository.Categories;

import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Single;


public interface CategoriesRepository {
    Observable<List<Category>> getCategories();
    Single<Category> getCategoryById(int categoryId);
    Single<Category> getCategoryBySlug(String categorySlug);
    Observable<List<Product>> getAllProductByCategory(int categoryId);
}

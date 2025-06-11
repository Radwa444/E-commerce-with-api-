package com.example.e_commercewithapi.data.repository.Products;

import com.example.e_commercewithapi.data.dataSourse.remote.Api.ApiService;
import com.example.e_commercewithapi.data.models.Prodect.Product;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
@Singleton
public class ProductRepositoryImpl implements ProductRepository {
    ApiService apiService;
    @Inject
    ProductRepositoryImpl(ApiService apiService){
        this.apiService=apiService;
    }
        @Override
    public Observable<List<Product>> getAllProducts() {
        return apiService.getAllProducts() ;
    }

    @Override
    public Single<Product> getProductById(int productId) {
        return apiService.getProductById(productId);
    }

    @Override
    public Single<Product> getProductBySlug(String productSlug) {
        return apiService.getProductBySlug(productSlug);
    }

    @Override
    public Observable<List<Product>> getProductWithPagination(int offset, int limit) {
        return apiService.getProductWithPagination(offset, limit);
    }
}

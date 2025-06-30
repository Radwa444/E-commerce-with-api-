package com.example.e_commercewithapi.data.repository.Products;

import com.example.e_commercewithapi.data.models.local.Prodect.Product;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ProductRepository {
    Observable<List<Product>> getAllProducts();
    Single<Product> getProductById(int productId);
    Single<Product> getProductBySlug(String productSlug);
    Observable<List<Product>> getProductWithPagination(int offset,int limit );
}

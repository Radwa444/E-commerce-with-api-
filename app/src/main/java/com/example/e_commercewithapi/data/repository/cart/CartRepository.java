package com.example.e_commercewithapi.data.repository.cart;

import androidx.lifecycle.LiveData;

import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CartRepository {

    Completable addProductInCart(ProductEntity product);

    Completable deleteProductInCart(ProductEntity product);

    Completable updateProductInCart(ProductEntity product);

    Completable deleteAll();

    Single<List<ProductEntity>> getProduct();
    Single<Product> getProductById(int Id);
}

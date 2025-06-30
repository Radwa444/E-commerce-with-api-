package com.example.e_commercewithapi.data.repository.cart;

import androidx.lifecycle.LiveData;

import com.example.e_commercewithapi.data.dataSourse.Local.roomDatabase.CartDAO;
import com.example.e_commercewithapi.data.dataSourse.remote.Api.ApiService;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;
@Singleton
public class CartRepositoryImpl implements CartRepository{
    private final CartDAO cartDAO;
    private final ApiService apiService;
    @Inject
    CartRepositoryImpl(CartDAO cartDAO,ApiService apiService){
        this.cartDAO=cartDAO;
        this.apiService=apiService;
    }
    @Override
    public Completable addProductInCart(ProductEntity product) {

       return cartDAO.addProductInCart(product);
    }

    @Override
    public Completable deleteProductInCart(ProductEntity product) {
      return   cartDAO.deleteProductInCart(product);

    }

    @Override
    public Completable updateProductInCart(ProductEntity product) {
       return cartDAO.updateProductInCart(product);

    }

    @Override
    public Completable deleteAll() {
       return cartDAO.deleteAll();

    }

    @Override
    public Single<List<ProductEntity>> getProduct() {
        return cartDAO.getProduct();
    }

    @Override
    public Single<Product> getProductById(int Id) {
        return apiService.getProductById(Id);
    }
}

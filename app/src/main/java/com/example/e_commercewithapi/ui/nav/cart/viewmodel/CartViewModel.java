package com.example.e_commercewithapi.ui.nav.cart.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.mappers.ProductMapper;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;
import com.example.e_commercewithapi.data.repository.Products.ProductRepository;
import com.example.e_commercewithapi.data.repository.cart.CartRepository;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class CartViewModel extends ViewModel {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final MutableLiveData<UiStates<Product>> _uiStateProduct = new MutableLiveData<>();
    public LiveData<UiStates<Product>> uiStateProduct = _uiStateProduct;
    private final MutableLiveData<UiStates<List<ProductEntity>>> _uiStateProductList = new MutableLiveData<>();
    public LiveData<UiStates<List<ProductEntity>>> uiStateProductList = _uiStateProductList;
    private final MutableLiveData<Float> itemPrices=new MutableLiveData<>(0.0f);
    private final MutableLiveData<Float> shippingPrice=new MutableLiveData<>(40.0f);

    CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    CartViewModel(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public void getProducts() {
        disposable.add(
                cartRepository.getProduct()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                productEntities -> {
                                    _uiStateProductList.setValue(new UiStates.Success<>(productEntities));

                                     },
                                throwable -> {
                                    _uiStateProductList.setValue(new UiStates.Error<>(throwable.toString()));

                                }

                        )

        );
    }


    public void addProductInCart(ProductEntity product) {


        try {
            disposable.add(
                    cartRepository.addProductInCart(product)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    () -> {Log.d("CartVM", "Product added successfully");
                                        getProducts();

                                    }
                            ));
        } catch (Exception e) {
            Log.e("cartVM", e.toString());
        }

    }

    @SuppressLint("CheckResult")
    public void deleteProductInCart(ProductEntity product) {
        cartRepository.deleteProductInCart(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Log.d("CartVM", "Product delete successfully");
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void getProductById(int id) {
        disposable.add(
                cartRepository.getProduct()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((productEntities, throwable) -> {
                            boolean exit=false;
                            for (ProductEntity product1:productEntities){
                                if(product1.getId()==id){
                                    exit=true;
                                    break;
                                }}
                           if (!exit){
                               disposable.add(productRepository.getProductById(id)
                                       .subscribeOn(Schedulers.io())
                                       .observeOn(AndroidSchedulers.mainThread())
                                       .subscribe(
                                               product -> {
                                                   ProductEntity entity = ProductMapper.mapToEntity(product);
                                                   Log.d("Entity", String.valueOf(entity.getPrice()));
                                                   entity.setCounter(1);
                                                   addProductInCart(entity);
                                                   //if (product.getCounter() == 0) deleteProductInCart(entity);
                                                   _uiStateProduct.setValue(new UiStates.Success<>(product));

                                               }

                                       ));
                           }



                        })
        );



    }

    @SuppressLint("CheckResult")
   public void updateProduct(ProductEntity product){
        cartRepository.updateProductInCart(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->Log.d("CartVM", "Product upData successfully"));
    }


    public LiveData<Float> getItemPrices() {
        return itemPrices;
    }

public void setItemPrices(float prices){
        this.itemPrices.setValue(prices);

}

    public float getTotalCost() {
        Float item = itemPrices.getValue();
        Float shipping = shippingPrice.getValue();

        if (item == null) item = 0.0f;
        if (shipping == null) shipping = 0.0f;

        return item + shipping;
    }

    public LiveData<Float> getShippingPrice() {
        return shippingPrice;
    }


}

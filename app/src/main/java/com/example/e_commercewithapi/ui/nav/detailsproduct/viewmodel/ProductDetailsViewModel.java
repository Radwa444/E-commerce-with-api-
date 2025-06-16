package com.example.e_commercewithapi.ui.nav.detailsproduct.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.models.Prodect.Product;
import com.example.e_commercewithapi.data.repository.Products.ProductRepository;
import com.example.e_commercewithapi.utils.UiStates;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class ProductDetailsViewModel extends ViewModel {
    ProductRepository productRepository;
    private final MutableLiveData<UiStates<Product>> _uiStateProduct=new MutableLiveData<>();
    public LiveData<UiStates<Product>> uiStateProduct=_uiStateProduct;
    @Inject
   public ProductDetailsViewModel(ProductRepository productRepository){
       this.productRepository=productRepository;
    }

   @SuppressLint("CheckResult")
  public void getProduct(int productId){
        productRepository.getProductById(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(product -> {_uiStateProduct.setValue(new  UiStates.Success<>(product));},
                        throwable -> {
                    _uiStateProduct.setValue(new UiStates.Error<>(throwable.toString()));
                        });
   }


}

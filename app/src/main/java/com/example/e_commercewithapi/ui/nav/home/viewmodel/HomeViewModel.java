package com.example.e_commercewithapi.ui.nav.home.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.repository.Categories.CategoriesRepository;
import com.example.e_commercewithapi.data.repository.Products.ProductRepository;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    CategoriesRepository categoriesRepository;
    ProductRepository productRepository;
    private final MutableLiveData<UiStates<List<Category>>> _categoriesUiSates=new MutableLiveData<>();
    public LiveData<UiStates<List<Category>>> categoriesUiSates=_categoriesUiSates;
    private final MutableLiveData<UiStates<List<Product>>> _productsUiState=new MutableLiveData<>();
    public MutableLiveData<UiStates<List<Product>>> productsUiState=_productsUiState;
    private static final String TAG="HomeViewModel";
    public MutableLiveData<UiStates<Integer>> idCategory=new MutableLiveData<>();

    @Inject
    HomeViewModel(CategoriesRepository categoriesRepository ,ProductRepository productRepository){
        this.categoriesRepository=categoriesRepository;
        this.productRepository=productRepository;
    }
    @SuppressLint("CheckResult")
    public void getCategories(){
         categoriesRepository.getCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseCategories -> {
                        _categoriesUiSates.setValue(new UiStates.Success<>(responseCategories));

                        },
                        throwable -> {
                            Log.e(TAG, String.valueOf(throwable));
                            _categoriesUiSates.setValue(new UiStates.Error<>(throwable.toString()));
                        }
                        );
    }
    @SuppressLint("CheckResult")
    public void getAllProduct(){
        productRepository.getAllProducts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        products -> {
                            _productsUiState.setValue(new UiStates.Success<>(products));
                        },
                        throwable -> _productsUiState.setValue(new UiStates.Error<>(throwable.toString()))
                );
    }


}

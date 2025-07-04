package com.example.e_commercewithapi.ui.nav.category.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.repository.Categories.CategoriesRepository;
import com.example.e_commercewithapi.data.repository.Products.ProductRepository;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class CategoryViewModel extends ViewModel {
    ProductRepository productRepository;
    CategoriesRepository categoriesRepository;
    private final MutableLiveData<UiStates<List<Product>>> _productsUiState=new MutableLiveData<>();
    public MutableLiveData<UiStates<List<Product>>> productsUiState=_productsUiState;
    private final MutableLiveData<Integer> _idCategory =new MutableLiveData<>() ;
 private final CompositeDisposable compositeDisposable=new CompositeDisposable();
            @Inject
   public CategoryViewModel(ProductRepository productRepository,CategoriesRepository categoriesRepository){
        this.productRepository=productRepository;
        this.categoriesRepository=categoriesRepository;
            }


            @SuppressLint("CheckResult")
            public void getAllProduct(){
                compositeDisposable.add(
                        productRepository.getAllProducts()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(products -> _productsUiState.setValue(new UiStates.Success<>(products)),
                                        throwable -> _productsUiState.setValue(new UiStates.Error<>(throwable.toString())))


                );

               }
            @SuppressLint("CheckResult")
            public void allProductsByCategory(int idCategory){
                compositeDisposable.add(

                        categoriesRepository.getAllProductByCategory(idCategory)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        products -> {
                                                    _productsUiState.setValue(new UiStates.Success<>(products));
                                                  },
                                        throwable -> _productsUiState.setValue(new UiStates.Error<>(throwable.toString()))
                                )
                );

            }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }



    public int get_idCategory() {
        Integer idCategory=_idCategory.getValue();
        return idCategory!=null?idCategory:1;
    }

    public void set_idCategory(int _idCategory) {
        this._idCategory.setValue(_idCategory);
    }
}

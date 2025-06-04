package com.example.e_commercewithapi.ui.nav.home.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commercewithapi.data.models.Categories.ResponseCategories;
import com.example.e_commercewithapi.data.repository.Categories.CategoriesRepository;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    CategoriesRepository categoriesRepository;
    private final MutableLiveData<UiStates<List<ResponseCategories>>> _categoriesUiSates=new MutableLiveData<>();
    public LiveData<UiStates<List<ResponseCategories>>> categoriesUiSates=_categoriesUiSates;
    private static final String TAG="HomeViewModel";

    @Inject
    HomeViewModel(CategoriesRepository categoriesRepository){
        this.categoriesRepository=categoriesRepository;
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
}

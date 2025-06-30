package com.example.e_commercewithapi.ui.nav.category.fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.data.mappers.ProductMapper;
import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;
import com.example.e_commercewithapi.databinding.FragmentCategoryBinding;
import com.example.e_commercewithapi.ui.nav.category.adapter.ItemsIsSelected;
import com.example.e_commercewithapi.ui.nav.category.adapter.ProductAdapter;
import com.example.e_commercewithapi.ui.nav.category.viewmodel.CategoryViewModel;
import com.example.e_commercewithapi.ui.nav.home.adapter.CategoriesAdapter;
import com.example.e_commercewithapi.ui.nav.home.viewmodel.HomeViewModel;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryFragment extends Fragment {
    FragmentCategoryBinding binding;
    HomeViewModel homeViewModel;
    CategoryViewModel categoryViewModel;
    boolean isFirstTime = true;

    private static final String TAG = "CategoryFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(getLayoutInflater(), container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        Log.d(TAG+" _idCategory", String.valueOf(categoryViewModel.get_idCategory()));
        toHomeFragment();
        return binding.getRoot();
    }

    private void toHomeFragment() {
        binding.backButtonCategory.setOnClickListener(
                view -> NavHostFragment.findNavController(this).navigate(R.id.action_categoryFragment_to_homeFragment)
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.getCategories();
        observeHomeViewModel();
        observeCategoryViewModel();


    }

    private void observeCategoryViewModel() {
        categoryViewModel.productsUiState.observe(getViewLifecycleOwner(), listUiStates -> {
            if (listUiStates instanceof UiStates.Error) {
                Log.e(TAG, ((UiStates.Error<List<Product>>) listUiStates).error);

            } else if (listUiStates instanceof UiStates.Success) {
                Log.d(TAG, ((UiStates.Success<List<Product>>) listUiStates).message.toString());
                List<Product> products = ((UiStates.Success<List<Product>>) listUiStates).message;
                setUpProduct(products);


            }
        });
    }

    private void observeHomeViewModel() {
        homeViewModel.categoriesUiSates.observe(getViewLifecycleOwner(), listUiStates -> {
            if (listUiStates instanceof UiStates.Error) {
                Log.d(TAG, ((UiStates.Error<?>) listUiStates).error);

            } else if (listUiStates instanceof UiStates.Success) {
                List<Category> categories = ((UiStates.Success<List<Category>>) listUiStates).message;
                if (isFirstTime) {
                    categoryViewModel.getAllProduct();
                    isFirstTime = false;
                }

                setUpCategories(categories);

            }
        });
    }


    private void setUpCategories(List<Category> categories) {
        for (Category category : categories) {
            if (category.getId() == categoryViewModel.get_idCategory())
                category.setSelected(true);

        }
        ItemsIsSelected adapter = new ItemsIsSelected(categories, idCategory -> {
            getIdProducts(idCategory);
            categoryViewModel.set_idCategory(idCategory);

        });
        CategoriesAdapter adapter1 = new CategoriesAdapter(categories);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.itemIsSelected.setLayoutManager(layoutManager);
        binding.itemIsSelected.setAdapter(adapter);
        binding.itemsImage.setLayoutManager(layoutManager2);
        binding.itemsImage.setAdapter(adapter1);

    }

    private void setUpProduct(List<Product> products) {
        ProductAdapter adapter = new ProductAdapter(products, idProduct -> {
            Log.d(TAG, String.valueOf(idProduct));
            NavDirections action = CategoryFragmentDirections.actionCategoryFragmentToProductDetailsFragment(idProduct);
            NavHostFragment.findNavController(this).navigate(action);
        }, idProduct -> {

        });
        // LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.itemsProductsRec.setLayoutManager(gridLayoutManager);
        binding.itemsProductsRec.setAdapter(adapter);

    }

    private void getIdProducts(int idCategory) {
        categoryViewModel.allProductsByCategory(idCategory);
        Log.d(TAG, String.valueOf(idCategory));
    }
}
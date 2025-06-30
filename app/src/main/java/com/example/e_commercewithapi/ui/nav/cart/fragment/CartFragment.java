package com.example.e_commercewithapi.ui.nav.cart.fragment;

import android.annotation.SuppressLint;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.util.Log;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;
import com.example.e_commercewithapi.databinding.FragmentCartBinding;
import com.example.e_commercewithapi.ui.nav.cart.adapters.AddCartAdapter;
import com.example.e_commercewithapi.ui.nav.cart.viewmodel.CartViewModel;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartFragment extends Fragment {
FragmentCartBinding binding;
CartViewModel cartViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentCartBinding.inflate(getLayoutInflater(),container,false);
        int id=CartFragmentArgs.fromBundle(getArguments()).getProductIdInCart();
        cartViewModel=new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getProductById(id);
        cartViewModel.getProducts();
        onClickBackButton();
        Log.d("priceAll",String.valueOf(cartViewModel.getItemPrices().getValue()));
        return binding.getRoot();
    }

    private void onClickBackButton() {
        binding.backButtonInCart.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeViewModel();
        //setUpPrices();

    }

    @SuppressLint("SetTextI18n")
    private void setUpPrices() {
        cartViewModel.getItemPrices().observe(getViewLifecycleOwner(),aFloat -> {
            binding.ItemsPrice.setText("$"+aFloat);
        });
    }

    private void observeViewModel() {
        cartViewModel.uiStateProductList.observe(getViewLifecycleOwner(),productUiStates -> {
            if(productUiStates instanceof UiStates.Error){
                Toast.makeText(getContext(), ((UiStates.Error<List<ProductEntity>>) productUiStates).error, Toast.LENGTH_SHORT).show();
                Log.e("CartREC",String.valueOf(((UiStates.Error<List<ProductEntity>>) productUiStates).error));
            } else if (productUiStates instanceof UiStates.Success) {
                List<ProductEntity> productEntities=((UiStates.Success<List<ProductEntity>>) productUiStates).message;
                if (productEntities.isEmpty()){
                     binding.textView27.setVisibility(View.GONE);
                     binding.ifCartIsEmpty.cartIsEmpty.setVisibility(View.VISIBLE);
                }else {
                    binding.ifCartIsEmpty.cartIsEmpty.setVisibility(View.GONE);
                    setUpProduct(productEntities);
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setUpProduct(List<ProductEntity> product) {
        @SuppressLint("SetTextI18n") AddCartAdapter adapter=new AddCartAdapter(product,
                product1 -> cartViewModel.deleteProductInCart(product1),
                (product1, counter) ->{

                  cartViewModel.updateProduct(product1);

                  Log.d("setPrice",String.valueOf(product1.getItemPrices()));
                },totalItemPrices -> {
            try {
                cartViewModel.setItemPrices(totalItemPrices);
                cartViewModel.getItemPrices().observe(getViewLifecycleOwner(),aFloat -> {
                    binding.ItemsPrice.setText("$"+aFloat);
                    binding.totalPricesAll.setText("$"+cartViewModel.getTotalCost());

                });


            } catch (Exception e) {
                Log.e("cartFragment",e.toString());
            }


        } );

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.addCartRec.setAdapter(adapter);
        binding.addCartRec.setLayoutManager(linearLayoutManager);


    }
    }

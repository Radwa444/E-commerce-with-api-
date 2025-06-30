package com.example.e_commercewithapi.ui.nav.home.fragment;

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

import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.databinding.FragmentHomeBinding;
import com.example.e_commercewithapi.ui.nav.home.adapter.BannerAdapter;
import com.example.e_commercewithapi.ui.nav.home.adapter.CategoriesAdapter;
import com.example.e_commercewithapi.ui.nav.home.viewmodel.HomeViewModel;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
FragmentHomeBinding binding;
private HomeViewModel homeViewModel;
    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        homeViewModel=new ViewModelProvider(this).get(HomeViewModel.class);
        toCategoryFragment();
        return binding.getRoot();
    }

    private void toCategoryFragment() {
        binding.textViewSeeAll.setOnClickListener(
                view -> NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_categoryFragment)
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpBanner();
        homeViewModel.getCategories();
        homeViewModel.categoriesUiSates.observe(getViewLifecycleOwner(),state->{
            if(state instanceof UiStates.Error){
                Log.d(TAG,((UiStates.Error<?>) state).error);
            } else if (state instanceof UiStates.Success) {
                List<Category> categories=((UiStates.Success<List<Category>>) state).message;
                setUpCategories(categories);

                    Log.d(TAG, categories.get(1).getImage());




            }
        });

    }

    private void setUpCategories(List<Category> categories) {
        CategoriesAdapter categoriesAdapter=new CategoriesAdapter(categories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.recCategories.setLayoutManager(layoutManager);
            binding.recCategories.setAdapter(categoriesAdapter);





    }

    private void setUpBanner() {

        List<String> bannerImages= Arrays.asList(
                "https://cdn.shopify.com/app-store/listing_images/12e32a5d89e3f895ba56a3e938e9c2e9/promotional_image/CLGul4DswfsCEAE=.jpeg?height=900&quality=90&width=1600"
                ,"https://cdn.shopify.com/app-store/listing_images/12e32a5d89e3f895ba56a3e938e9c2e9/promotional_image/CLGul4DswfsCEAE=.jpeg?height=900&quality=90&width=1600"
                ,"https://cdn.shopify.com/app-store/listing_images/12e32a5d89e3f895ba56a3e938e9c2e9/promotional_image/CLGul4DswfsCEAE=.jpeg?height=900&quality=90&width=1600"

        );
        BannerAdapter bannerAdapter=new BannerAdapter(bannerImages);
        binding.bannerViewPager.setAdapter(bannerAdapter);
        binding.dotsIndicator.setViewPager2(binding.bannerViewPager);
    }
}
package com.example.e_commercewithapi.ui.nav.detailsproduct.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.data.models.local.Prodect.Colors;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.data.models.local.Prodect.Review;
import com.example.e_commercewithapi.data.models.local.Prodect.Size;
import com.example.e_commercewithapi.databinding.FragmentProductDetailsBinding;
import com.example.e_commercewithapi.ui.nav.detailsproduct.adapters.GroupColorAdapter;
import com.example.e_commercewithapi.ui.nav.detailsproduct.adapters.GroupImageAdapter;
import com.example.e_commercewithapi.ui.nav.detailsproduct.adapters.GroupSizeAdapter;
import com.example.e_commercewithapi.ui.nav.detailsproduct.adapters.ReviewsAdapter;
import com.example.e_commercewithapi.ui.nav.detailsproduct.viewmodel.ProductDetailsViewModel;
import com.example.e_commercewithapi.utils.UiStates;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductDetailsFragment extends Fragment {
    FragmentProductDetailsBinding binding;
    ProductDetailsViewModel productDetailsViewModel;
    private static final String TAG="ProductDetailsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentProductDetailsBinding.inflate(getLayoutInflater(),container,false);
        int productId= ProductDetailsFragmentArgs.fromBundle(getArguments()).getProductId();
        Log.d(TAG, String.valueOf(productId));
        productDetailsViewModel=new ViewModelProvider(this).get(ProductDetailsViewModel.class);
        productDetailsViewModel.getProduct(productId);
        OnClickBackButton();
        onClickIconCart(productId);
        onClickAddCart(productId);
        return binding.getRoot();

    }



    private void onClickAddCart(int productId) {
        binding.AddToCart.setOnClickListener(view -> {
            NavDirections action=ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartFragment(productId);
            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    private void onClickIconCart(int productId) {
        binding.addAndGoToCart.setOnClickListener(view -> {
            NavDirections action=ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartFragment(productId);
            NavHostFragment.findNavController(this).navigate(action);
            //   NavHostFragment.findNavController(this).navigate(R.id.action_productDetailsFragment_to_cartFragment);
        });
    }

    private void OnClickBackButton() {
        binding.backButtonProductDetails.setOnClickListener(view ->NavHostFragment.findNavController(this).popBackStack() );

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeViewModel();
       //Since the current API doesn't provide a color list, I implemented this part in advance to ensure compatibility with the UI I'm using and to be prepared for potential integration with another API in the future

        List<Colors> colorList = new ArrayList<>();
        colorList.add(new Colors("#FF0000", false));
        colorList.add(new Colors("#00FF00", false));
        colorList.add(new Colors("#0000FF", false));
        colorList.add(new Colors("#FFFF00", false));
        colorList.add(new Colors("#000000", false));
        colorList.add(new Colors("#FFFFFF", false));
        List<Size> sizeList=new ArrayList<>();
        sizeList.add(new Size("S",false));
        sizeList.add(new Size("L",false));
        sizeList.add(new Size("XL",false));
        sizeList.add(new Size("XXL",false));
        List<Review> reviewList = new ArrayList<>();

        reviewList.add(new Review("Radwa", "https://static-00.iconduck.com/assets.00/profile-circle-icon-2048x2048-cqe5466q.png", 4.5f, "Great product!"));
        reviewList.add(new Review("Alaa", "https://static-00.iconduck.com/assets.00/profile-circle-icon-2048x2048-cqe5466q.png", 5.0f, "Excellent quality."));
        reviewList.add(new Review("Ahmed", "https://static-00.iconduck.com/assets.00/profile-circle-icon-2048x2048-cqe5466q.png", 3.8f, "Good, but delivery was slow."));
        reviewList.add(new Review("Nada", "https://static-00.iconduck.com/assets.00/profile-circle-icon-2048x2048-cqe5466q.png", 4.0f, "Satisfied overall."));
        setUpGroupSize(sizeList);
        setUpReview(reviewList);
        setUpGroupColor(colorList);


    }

    private void setUpReview(List<Review> reviewList) {
        ReviewsAdapter adapter=new ReviewsAdapter(reviewList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.ReviewsRec.setLayoutManager(linearLayoutManager);
        binding.ReviewsRec.setAdapter(adapter);


    }

    private void setUpGroupSize(List<Size> sizeList) {
        GroupSizeAdapter adapter=new GroupSizeAdapter(sizeList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.groupSize.setLayoutManager(linearLayoutManager);
        binding.groupSize.setAdapter(adapter);

    }

    private void setUpGroupColor(List<Colors> colorsList) {
        GroupColorAdapter adapter=new GroupColorAdapter(colorsList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.GroupColor.setLayoutManager(linearLayoutManager);
        binding.GroupColor.setAdapter(adapter);
    }

    private void observeViewModel() {
        View loading=binding.getRoot().findViewById(R.id.loading_in_product_details);
        productDetailsViewModel.uiStateProduct.observe(getViewLifecycleOwner(),productUiStates -> {
          if (productUiStates instanceof  UiStates.Loading){
              loading.setVisibility(View.VISIBLE);
          }
           else if(productUiStates instanceof UiStates.Error){
                Log.e(TAG,((UiStates.Error<Product>) productUiStates).error);
                loading.setVisibility(View.GONE);
            } else if (productUiStates instanceof  UiStates.Success) {
                Product product=((UiStates.Success<Product>) productUiStates).message;
                setUpProduct(product);
                setUpGroupImage(product);
                loading.setVisibility(View.GONE);


            }
        });
    }

    private void setUpGroupImage(Product product) {
        GroupImageAdapter adapter=new GroupImageAdapter(product);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        binding.groubImage.setLayoutManager(linearLayoutManager);
        binding.groubImage.setAdapter(adapter);


    }

    private void setUpProduct(Product product) {
        Glide.with(binding.imageViewImageProduct.getContext())
                .load(product.getImages().get(0))
                .into(binding.imageViewImageProduct);
        binding.textViewTitle.setText(product.getTitle());
        binding.textViewPrice.setText(String.valueOf(product.getPrice()));
        binding.productDetails.setText(product.getDescription());
    }



}
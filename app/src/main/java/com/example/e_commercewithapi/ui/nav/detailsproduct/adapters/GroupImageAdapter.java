package com.example.e_commercewithapi.ui.nav.detailsproduct.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercewithapi.data.models.local.Prodect.Product;
import com.example.e_commercewithapi.databinding.GroupImagesProductDetailsBinding;

public class GroupImageAdapter extends RecyclerView.Adapter<GroupImageAdapter.GroupImageViewHolder> {
    private final Product product;
    public GroupImageAdapter(Product product){
        this.product=product;
    }
    @NonNull
    @Override
    public GroupImageAdapter.GroupImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupImagesProductDetailsBinding binding=GroupImagesProductDetailsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new GroupImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupImageAdapter.GroupImageViewHolder holder, int position) {
     String image= product.getImages().get(position);
        Glide.with(holder.binding.imageView11.getContext())
                .load(image)
                .into(holder.binding.imageView11);
    }

    @Override
    public int getItemCount() {
        return product.getImages().size();
    }

    public static class GroupImageViewHolder extends RecyclerView.ViewHolder {
       private final GroupImagesProductDetailsBinding binding;
        public GroupImageViewHolder(@NonNull GroupImagesProductDetailsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

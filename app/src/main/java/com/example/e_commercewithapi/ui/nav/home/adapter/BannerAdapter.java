package com.example.e_commercewithapi.ui.nav.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercewithapi.databinding.BannerInHomeBinding;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private List<String> bannerImage;
   public BannerAdapter(List<String> bannerImage){
        this.bannerImage=bannerImage;
    }
    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       BannerInHomeBinding binding=BannerInHomeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new BannerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
       String imageRec=bannerImage.get(position);
        Glide.with(holder.binding.imageView9.getContext())
                .load(imageRec)
                .into(holder.binding.imageView9);

    }

    @Override
    public int getItemCount() {
        return bannerImage.size();
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
             BannerInHomeBinding binding;
        public BannerViewHolder(BannerInHomeBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

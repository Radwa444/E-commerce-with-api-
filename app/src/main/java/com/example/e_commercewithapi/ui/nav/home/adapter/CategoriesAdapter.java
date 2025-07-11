package com.example.e_commercewithapi.ui.nav.home.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercewithapi.data.models.local.Categories.Category;
import com.example.e_commercewithapi.databinding.CategoriesItemInHomeBinding;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    List<Category> categories;
    public CategoriesAdapter(List<Category> categories){
        this.categories =categories;

    }
    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       CategoriesItemInHomeBinding binding=CategoriesItemInHomeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CategoriesViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        try {
            Category categoriesREC=categories.get(position);

            Log.d("Adapter", "Binding: " + categoriesREC.getName());
            Glide.with(holder.binding.imageView10.getContext())
                    .load(categoriesREC.getImage())
                    .into(holder.binding.imageView10);
            holder.binding.imageView10.setSelected(categoriesREC.isSelected());
            if(categoriesREC.isSelected()) {
                Log.d("adapterCatgory", String.valueOf(categoriesREC.getId()));

            }
           holder.binding.imageView10.setOnClickListener(view -> {
               for(Category items :categories){
                items.setSelected(false);
               }
               categoriesREC.setSelected(true);

               notifyDataSetChanged();

           });

        } catch (RuntimeException e) {
            Log.e("Adapter", "Binding: " + e.toString());
        }

    }



    @Override
    public int getItemCount() {
        return categories.size();

    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        CategoriesItemInHomeBinding binding;
        public CategoriesViewHolder(CategoriesItemInHomeBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

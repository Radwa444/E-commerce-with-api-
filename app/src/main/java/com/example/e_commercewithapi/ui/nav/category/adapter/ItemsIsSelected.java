package com.example.e_commercewithapi.ui.nav.category.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.data.models.Categories.Category;
import com.example.e_commercewithapi.databinding.ItemTextIsSelectedBinding;

import java.util.List;

public class ItemsIsSelected extends RecyclerView.Adapter<ItemsIsSelected.ItemsIsSelectedViewHolder> {
private final List<Category> itemsCategory;
private final OnCategoryClickListener listener;
   @FunctionalInterface
public interface OnCategoryClickListener {
    void onCategorySelected(int idCategory);
   }

    public ItemsIsSelected(List<Category> itemsCategory ,OnCategoryClickListener listener){
    this.itemsCategory=itemsCategory;
    this.listener=listener;
}
    @NonNull
    @Override
    public ItemsIsSelected.ItemsIsSelectedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTextIsSelectedBinding binding=ItemTextIsSelectedBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ItemsIsSelectedViewHolder(binding);
    }

    @SuppressLint({"NotifyDataSetChanged", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ItemsIsSelectedViewHolder holder, int position) {
    Category category=itemsCategory.get(position);
    holder.binding.textCategorySelector.setText(category.getName());
    holder.binding.textCategorySelector.setSelected(category.isSelected());
    if(category.isSelected()) {

        holder.binding.textCategorySelector.setTextColor(
                ContextCompat.getColor(holder.itemView.getContext(), R.color.white)

        );
    }
    else holder.binding.textCategorySelector.setTextColor(
            ContextCompat.getColor(holder.itemView.getContext(),R.color.black)
    );
    holder.binding.textCategorySelector.setOnClickListener(
            view -> {
                for(Category items : itemsCategory){
                    items.setSelected(false);
                }
                category.setSelected(true);
                listener.onCategorySelected(category.getId());
                notifyDataSetChanged();
            }
    );

    }

    @Override
    public int getItemCount() {
        return itemsCategory.size();
    }

    public static class ItemsIsSelectedViewHolder extends RecyclerView.ViewHolder {
    ItemTextIsSelectedBinding binding;
        public ItemsIsSelectedViewHolder(@NonNull ItemTextIsSelectedBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

package com.example.e_commercewithapi.ui.nav.detailsproduct.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercewithapi.data.models.local.Prodect.Colors;
import com.example.e_commercewithapi.databinding.GroupColorBinding;

import java.util.List;

public class GroupColorAdapter extends RecyclerView.Adapter<GroupColorAdapter.GroupImageViewHolder> {
    List<Colors> groupColor;

    public GroupColorAdapter(List<Colors> groupColor) {
        this.groupColor=groupColor;
    }



    @NonNull
    @Override
    public GroupImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GroupColorBinding binding=GroupColorBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new GroupImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupImageViewHolder holder, int position) {
     Colors color=groupColor.get(position);

        try {
            int colorInt = Color.parseColor(color.getColor());
            holder.binding.cardColor.setCardBackgroundColor(colorInt);
        } catch (Exception e) {
            Log.e("GroupColor",e.toString());
        }



     holder.binding.cardColor.setOnClickListener(view -> {
         for (Colors items:groupColor ){
             items.setSelected(false);
         }
         color.setSelected(true);




     });
    }

    @Override
    public int getItemCount() {
        return groupColor.size();
    }
   static class GroupImageViewHolder extends RecyclerView.ViewHolder {
     GroupColorBinding binding;

       public GroupImageViewHolder(@NonNull  GroupColorBinding binding) {
           super(binding.getRoot());
           this.binding=binding;
       }

   }
}

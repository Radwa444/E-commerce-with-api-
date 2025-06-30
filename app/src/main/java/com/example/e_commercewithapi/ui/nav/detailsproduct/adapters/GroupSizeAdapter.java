package com.example.e_commercewithapi.ui.nav.detailsproduct.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercewithapi.R;
import com.example.e_commercewithapi.data.models.local.Prodect.Size;
import com.example.e_commercewithapi.databinding.GroupSizeBinding;

import java.util.List;

public class GroupSizeAdapter extends RecyclerView.Adapter<GroupSizeAdapter.GroupImageViewHolder> {
    List<Size> sizeList;
   public GroupSizeAdapter(List<Size> sizeList){
       this.sizeList=sizeList;

    }
    @NonNull
    @Override
    public GroupImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       GroupSizeBinding binding=GroupSizeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new GroupImageViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull GroupImageViewHolder holder, int position) {
        Size size=sizeList.get(position);
        holder.binding.textView18.setText(size.getSize());
        holder.binding.textView18.setSelected(size.isSelected());
        if (size.isSelected()){
            holder.binding.textView18.setTextColor(
                    ContextCompat.getColor(holder.binding.textView18.getContext(), R.color.white)
            );
        }else {
            holder.binding.textView18.setTextColor(
                    ContextCompat.getColor(holder.binding.textView18.getContext(), R.color.black)
            );
        }

        holder.binding.textView18.setOnClickListener(view -> { for(Size item :sizeList){
            item.setSelected(false);
        }
            size.setSelected(true);
            notifyDataSetChanged();});
    }



    @Override
    public int getItemCount() {
        return sizeList.size();
    }
    static class GroupImageViewHolder extends RecyclerView.ViewHolder{
private final GroupSizeBinding binding;


        public GroupImageViewHolder(@NonNull GroupSizeBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }}

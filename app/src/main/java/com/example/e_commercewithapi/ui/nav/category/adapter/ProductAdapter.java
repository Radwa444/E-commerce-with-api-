package com.example.e_commercewithapi.ui.nav.category.adapter;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.e_commercewithapi.data.models.Prodect.Product;
import com.example.e_commercewithapi.databinding.ItemProdcutBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<Product> listProduct;
    private final OnProductClickListener listener;
    private final OnProductClickListener onClickCart;
    @FunctionalInterface
    public interface OnProductClickListener {
        void listener(int idProduct);

    }
    public ProductAdapter(List<Product> listProduct, OnProductClickListener listener, OnProductClickListener onClickCart) {
        this.listProduct = listProduct;
        this.listener = listener;
        this.onClickCart = onClickCart;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProdcutBinding binding = ItemProdcutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product itemOfProduct = listProduct.get(position);
        holder.binding.textNameProduct.setText(itemOfProduct.getTitle());
        holder.binding.textPriceProduct.setText(itemOfProduct.getPrice());
        Glide.with(holder.binding.imageProduct.getContext())
                .load(itemOfProduct.getImages().get(0))
                .into(holder.binding.imageProduct);
        holder.binding.itemOfProduct.setSelected((Boolean.TRUE.equals(itemOfProduct.getSelected())));
        holder.binding.onClickOnCart.setOnClickListener(
                view -> onClickCart.listener(itemOfProduct.getId())
        );
        holder.binding.itemOfProduct.setOnClickListener(view -> {

            for (Product items : listProduct) {
                items.setSelected(false);


            }
            itemOfProduct.setSelected(true);
            listener.listener(itemOfProduct.getId());
            notifyDataSetChanged();
        });


    }


    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemProdcutBinding binding;

        public ProductViewHolder(@NonNull ItemProdcutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}

package com.example.e_commercewithapi.ui.nav.cart.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercewithapi.data.models.remote.product.ProductEntity;
import com.example.e_commercewithapi.databinding.ProductInCartBinding;

import java.util.List;

public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.AddCartViewHolder> {
  private final List<ProductEntity> products;
  private final OnClickListener listener;
 private final OnClickBtnDecrease listener_decrease;
 private final GetTotalItemPrices getTotalItemPrices;
    public AddCartAdapter(List<ProductEntity> products,OnClickListener listener,OnClickBtnDecrease listener_decrease,GetTotalItemPrices getTotalItemPrices){
        this.products=products;
        this.listener=listener;
        this.listener_decrease=listener_decrease;
        this.getTotalItemPrices=getTotalItemPrices;
    }

    public interface OnClickBtnDecrease{
        void onClickBtnDecrease(ProductEntity product, int counter);
    }


    public interface OnClickListener{
        void listener(ProductEntity product);


    }
    public interface GetTotalItemPrices{
        void getTotalItemPrices(float totalItemPrices);

    }
    @NonNull
    @Override
    public AddCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductInCartBinding binding=ProductInCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new AddCartViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AddCartViewHolder holder, int position) {
        ProductEntity product=products.get(position);
       // product.setCounter(1);
        float  totle = 0;
        for(ProductEntity productEntity:products){
            totle+= productEntity.getItemPrices();
        }
        getTotalItemPrices.getTotalItemPrices(totle);
Log.d("totlePrice","prices"+totle);
        holder.binding.userNameInCart.setText(product.getTitle());

       holder.binding.priceInCart.setText("$"+product.getPrice()*product.getCounter());
       holder.binding.numberProduct.setText(String.valueOf(product.getCounter()));
        Glide.with(holder.binding.imageView16.getContext())
                .load(product.getImages().get(0))
                .into(holder.binding.imageView16);
        
        holder.binding.numberProduct.setText(String.valueOf(product.getCounter()));

        holder.binding.btnDelete.setOnClickListener(view -> {
            int index=holder.getBindingAdapterPosition();
            listener.listener(product);
            if (index!=RecyclerView.NO_POSITION ){
                products.remove(index);
                notifyItemRemoved(index);
            }});
            holder.binding.buttonDecrease.setOnClickListener(view1 -> {
                int index=holder.getBindingAdapterPosition();
                int counter=product.getCounter();
                if (counter >0){
                     counter--;
                    product.setCounter(counter);
                    holder.binding.numberProduct.setText(String.valueOf(counter));
                    holder.binding.priceInCart.setText("$"+product.getPrice()*counter);
                    product.setItemPrices((float) product.getPrice()*counter);
                    Log.d("itemsPrice","prices "+product.getItemPrices());
                    notifyItemChanged(index);
                }
                if(counter==0 && index != RecyclerView.NO_POSITION){
                    listener.listener(product);
                   products.remove(index);
                   notifyItemRemoved(index);
                }
                listener_decrease.onClickBtnDecrease(product,counter);
        });
            holder.binding.buttonAdd.setOnClickListener(view -> {
                int index=holder.getBindingAdapterPosition();
                int counter=product.getCounter();
                counter++;
                product.setCounter(counter);
                holder.binding.numberProduct.setText(String.valueOf(counter));
                holder.binding.priceInCart.setText("$"+product.getPrice()*counter);
                product.setItemPrices(product.getPrice()*counter);
                Log.d("itemsPrice","prices "+product.getItemPrices());
                listener_decrease.onClickBtnDecrease(product,counter);
                notifyItemChanged(index);
            });




    }

    static class AddCartViewHolder extends RecyclerView.ViewHolder{
     private ProductInCartBinding binding;
        public AddCartViewHolder(@NonNull ProductInCartBinding binding) {

            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

package com.example.e_commercewithapi.ui.nav.detailsproduct.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercewithapi.data.models.Prodect.Review;
import com.example.e_commercewithapi.databinding.ReviewBinding;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {
 List<Review> reviews;

    public ReviewsAdapter(List<Review> reviewList) {
        this.reviews=reviewList;
    }

    @NonNull
    @Override
    public ReviewsAdapter.ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewBinding reviewBinding=ReviewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ReviewsViewHolder(reviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ReviewsViewHolder holder, int position) {
        Review review=reviews.get(position);
        holder.binding.userName.setText(review.getName());
        holder.binding.ratingBar.setRating(review.getRating());
        Glide.with(holder.binding.profile.getContext())
                .load(review.getImage())
                .into(holder.binding.profile);
        holder.binding.textView21.setText(review.getComment());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder {
        ReviewBinding binding;
        public ReviewsViewHolder(@NonNull ReviewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

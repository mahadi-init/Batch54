package com.example.batch54.ui.fragments.trending.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batch54.databinding.TrendingCardviewBinding;

import java.util.ArrayList;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {
    private final ArrayList<String> trendingArraylist;

    public TrendingAdapter(ArrayList<String> trendingArraylist) {
        this.trendingArraylist = trendingArraylist;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TrendingCardviewBinding binding;

        public ViewHolder(@NonNull TrendingCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(TrendingCardviewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String trending = trendingArraylist.get(position);
        holder.binding.trend.setText(trending);
    }

    @Override
    public int getItemCount() {
        return trendingArraylist.size();
    }
}

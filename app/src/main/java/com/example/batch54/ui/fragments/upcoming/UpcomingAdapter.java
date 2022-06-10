package com.example.batch54.ui.fragments.upcoming;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batch54.databinding.UpcomingCardviewBinding;
import com.example.batch54.ui.fragments.upcoming.model.UpcomingModel;

import java.util.ArrayList;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {
    private final ArrayList<UpcomingModel> upcomingModelArrayList;

    public UpcomingAdapter(ArrayList<UpcomingModel> upcomingModelArrayList) {
        this.upcomingModelArrayList = upcomingModelArrayList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final UpcomingCardviewBinding binding;

        public ViewHolder(@NonNull UpcomingCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(UpcomingCardviewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UpcomingModel data = upcomingModelArrayList.get(position);
        holder.binding.upcomingTitle.setText(data.getTitle());
        holder.binding.upcomingDate.setText(data.getDate());
        holder.binding.upcomingDetails.setText(data.getDetails());
    }

    @Override
    public int getItemCount() {
        return upcomingModelArrayList.size();
    }
}

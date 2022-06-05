package com.example.batch54.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batch54.R;
import com.example.batch54.databinding.HomeCardviewBinding;
import com.example.batch54.models.HomeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final ArrayList<HomeModel> homeModelArrayList;

    public HomeAdapter(ArrayList<HomeModel> homeModelArrayList) {
        this.homeModelArrayList = homeModelArrayList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final HomeCardviewBinding binding;

        public ViewHolder(@NonNull HomeCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(HomeCardviewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel data = homeModelArrayList.get(position);
        Picasso.get()
                .load(R.drawable.diu_logo)
                .placeholder(R.drawable.progress_animation)
                .into(holder.binding.homeImage);
        holder.binding.homeTitle.setText(data.getTitle());
        holder.binding.homeAuthor.setText(data.getAuthor());
        holder.binding.homeUploadDate.setText(data.getDate());

        // TODO: 6/4/2022 after onclick
        // FIXME: 6/4/2022
        holder.binding.upvote.setText(data.getUpvote());
        holder.binding.downvote.setText(data.getDownvote());
    }

    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }
}

package com.example.batch54.ui.fragments.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batch54.R;
import com.example.batch54.databinding.HomeCardviewBinding;
import com.example.batch54.ui.fragments.home.model.HomeModel;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final ArrayList<HomeModel> homeModelArrayList;
    private static final FirebaseStorage storage = FirebaseStorage.getInstance();
    private static final StorageReference storageRef = storage.getReference();

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
        holder.binding.homeTitle.setText(data.getTitle());
        holder.binding.homeAuthor.setText(data.getAuthor());
        holder.binding.homeUploadDate.setText(data.getDate());

        StorageReference imageRef = storageRef.child(data.getUrl());

        imageRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get()
                .load(uri)
                .resize(600,600)
                .placeholder(R.drawable.progress_animation)
                .into(holder.binding.homeImage));

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

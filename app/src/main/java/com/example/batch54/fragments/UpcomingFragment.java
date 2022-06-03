package com.example.batch54.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.batch54.R;
import com.example.batch54.adapters.UpcomingAdapter;
import com.example.batch54.databinding.FragmentUpcomingBinding;
import com.example.batch54.databinding.UpcomingCardviewBinding;

public class UpcomingFragment extends Fragment {
    private FragmentUpcomingBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpcomingBinding.inflate(inflater,container,false);

        binding.upcomingRecyclerview.setAdapter(new UpcomingAdapter());
        binding.upcomingRecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return binding.getRoot();
    }
}
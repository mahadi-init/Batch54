package com.example.batch54.ui.fragments.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.batch54.databinding.FragmentUpcomingBinding;
import com.example.batch54.ui.fragments.upcoming.viewmodel.UpcomingFragmentViewmodel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UpcomingFragment extends Fragment {
    private FragmentUpcomingBinding binding;
    private UpcomingFragmentViewmodel viewmodel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        viewmodel = new ViewModelProvider(this).get(UpcomingFragmentViewmodel.class);

        binding.upcomingRecyclerview.setAdapter(new UpcomingAdapter(new ArrayList<>()));
        binding.upcomingRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        Thread thread = new Thread(viewmodel);
        thread.start();

        viewmodel.isSucceed().observe(getViewLifecycleOwner(), aBoolean ->
                viewmodel.upcomingEvents().observe(getViewLifecycleOwner(), upcomingModels ->
                binding.upcomingRecyclerview.setAdapter(new UpcomingAdapter(upcomingModels))));

        viewmodel.isFailed().observe(getViewLifecycleOwner(), aBoolean -> {
            Snackbar.make(binding.upcomingLayout, "Login Failed", Snackbar.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }
}
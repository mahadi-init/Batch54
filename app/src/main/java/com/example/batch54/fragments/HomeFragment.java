package com.example.batch54.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.batch54.adapters.HomeAdapter;
import com.example.batch54.databinding.FragmentHomeBinding;
import com.example.batch54.viewmodels.HomeFragmentViewmodel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeFragmentViewmodel viewmodel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        viewmodel = new ViewModelProvider(this).get(HomeFragmentViewmodel.class);

        binding.homeRecyclerview.setAdapter(new HomeAdapter(new ArrayList<>()));
        binding.homeRecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        Thread thread = new Thread(viewmodel);
        thread.start();

        viewmodel.isSucceed().observe(getViewLifecycleOwner(), aBoolean ->
                viewmodel.homeStories().observe(getViewLifecycleOwner(), homeStories ->
                        binding.homeRecyclerview.setAdapter(new HomeAdapter(homeStories))));

        viewmodel.isFailed().observe(getViewLifecycleOwner(), aBoolean -> {
            Snackbar.make(binding.homeFragmentLayout, "Login Failed", Snackbar.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }
}
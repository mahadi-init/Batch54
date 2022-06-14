package com.example.batch54.ui.fragments.trending;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.batch54.R;
import com.example.batch54.databinding.FragmentTrendingBinding;
import com.example.batch54.ui.fragments.trending.adapter.TrendingAdapter;
import com.example.batch54.ui.fragments.trending.viewmodel.TrendingViewmodel;
import com.example.batch54.ui.fragments.upcoming.viewmodel.UpcomingFragmentViewmodel;

import java.util.ArrayList;

public class TrendingFragment extends Fragment {
    private FragmentTrendingBinding binding;
    private TrendingViewmodel viewmodel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrendingBinding.inflate(inflater,container,false);
        viewmodel = new ViewModelProvider(this).get(TrendingViewmodel.class);

        binding.trendingRecyclerview.setAdapter(new TrendingAdapter(new ArrayList<>()));
        binding.trendingRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),4));

        observers();

        return binding.getRoot();
    }

    private void observers() {
        Thread thread = new Thread(viewmodel);
        thread.start();

        viewmodel.getTrendingData().observe(getViewLifecycleOwner(), strings ->
                binding.trendingRecyclerview.setAdapter(new TrendingAdapter(strings)));
    }
}
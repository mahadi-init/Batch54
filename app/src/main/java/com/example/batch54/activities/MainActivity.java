package com.example.batch54.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.batch54.R;
import com.example.batch54.databinding.ActivityMainBinding;
import com.example.batch54.enums.MainActivityFragments;
import com.example.batch54.fragments.ChatFragment;
import com.example.batch54.fragments.HomeFragment;
import com.example.batch54.fragments.TrendingFragment;
import com.example.batch54.fragments.UpcomingFragment;
import com.example.batch54.viewmodels.MainActivityViewmodel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewmodel viewmodel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewmodel = new ViewModelProvider(this).get(MainActivityViewmodel.class);

        bottomNavigationSelector();
        currentFragmentObserver();
    }

    private void bottomNavigationSelector() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_menu) {
                viewmodel.setActiveFragment(MainActivityFragments.HOME);
                return true;
            }
            if (item.getItemId() == R.id.upcoming_menu) {
                viewmodel.setActiveFragment(MainActivityFragments.UPCOMING);
                return true;
            }
            if (item.getItemId() == R.id.trending_menu) {
                viewmodel.setActiveFragment(MainActivityFragments.TRENDING);
                return true;
            }
            if (item.getItemId() == R.id.chat_menu) {
                viewmodel.setActiveFragment(MainActivityFragments.CHAT);
                return true;
            }

            return false;
        });
    }

    private void currentFragmentObserver() {
        viewmodel.currentActiveFragment().observe(this, mainActivityFragments -> {
            switch (mainActivityFragments) {
                case HOME:
                    setFragment(new HomeFragment());
                    break;
                case UPCOMING:
                    setFragment(new UpcomingFragment());
                    break;
                case TRENDING:
                    setFragment(new TrendingFragment());
                    break;
                case CHAT:
                    setFragment(new ChatFragment());
                    break;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
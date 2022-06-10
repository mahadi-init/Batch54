package com.example.batch54.ui.activities.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.batch54.R;
import com.example.batch54.databinding.ActivityMainBinding;
import com.example.batch54.ui.activities.main.enums.MainActivityEnum;
import com.example.batch54.ui.activities.main.viewmodel.MainActivityViewmodel;
import com.example.batch54.ui.fragments.chat.ChatFragment;
import com.example.batch54.ui.fragments.home.HomeFragment;
import com.example.batch54.ui.fragments.trending.TrendingFragment;
import com.example.batch54.ui.fragments.upcoming.UpcomingFragment;

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
                viewmodel.setActiveFragment(MainActivityEnum.HOME);
                return true;
            }
            if (item.getItemId() == R.id.upcoming_menu) {
                viewmodel.setActiveFragment(MainActivityEnum.UPCOMING);
                return true;
            }
            if (item.getItemId() == R.id.trending_menu) {
                viewmodel.setActiveFragment(MainActivityEnum.TRENDING);
                return true;
            }
            if (item.getItemId() == R.id.chat_menu) {
                viewmodel.setActiveFragment(MainActivityEnum.CHAT);
                return true;
            }

            return false;
        });

        binding.bottomNavigation.setOnItemReselectedListener(item -> {
            if (item.getItemId() == R.id.home_menu) {
                viewmodel.setActiveFragment(MainActivityEnum.HOME);
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void currentFragmentObserver() {
        viewmodel.currentActiveFragment().observe(this, mainActivityEnum -> {
            switch (mainActivityEnum) {
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
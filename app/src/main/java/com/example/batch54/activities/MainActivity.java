package com.example.batch54.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.batch54.R;
import com.example.batch54.databinding.ActivityMainBinding;
import com.example.batch54.fragments.ChatFragment;
import com.example.batch54.fragments.HomeFragment;
import com.example.batch54.fragments.TrendingFragment;
import com.example.batch54.fragments.UpcomingFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home_menu){
                    setFragment(new HomeFragment());
                    return true;
                }
                if(item.getItemId() == R.id.upcoming_menu){
                    setFragment(new UpcomingFragment());
                    return true;
                }
                if(item.getItemId() == R.id.trending_menu){
                    setFragment(new TrendingFragment());
                    return true;
                }
                if(item.getItemId() == R.id.chat_menu){
                    setFragment(new ChatFragment());
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setFragment(new HomeFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
package com.example.batch54.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.batch54.databinding.ActivityStartBinding;
import com.example.batch54.viewmodels.HomeFragmentViewmodel;
import com.example.batch54.viewmodels.StoryLayoutViewmodel;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.WHITE);
        ActivityStartBinding binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // cache the data on runtime to save load time
        HomeFragmentViewmodel viewmodel = new ViewModelProvider(this).get(HomeFragmentViewmodel.class);
        Thread thread = new Thread(viewmodel);
        thread.start();

        //set animation to a targeted view
        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .playOn(binding.diuLogoIcon);

        // move to new activity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }, 1500);
    }
}
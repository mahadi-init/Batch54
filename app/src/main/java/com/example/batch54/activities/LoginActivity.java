package com.example.batch54.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.batch54.databinding.ActivityLoginBinding;
import com.example.batch54.viewmodels.LoginActivityViewmodel;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private LoginActivityViewmodel viewmodel;
    private ActivityLoginBinding binding;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewmodel = new ViewModelProvider(this).get(LoginActivityViewmodel.class);
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);

        binding.submit.setOnClickListener(v -> viewmodel.setValues(
                Objects.requireNonNull(binding.studentName.getText()).toString(),
                Objects.requireNonNull(binding.studentId.getText()).toString(),
                Objects.requireNonNull(binding.studentEmail.getText()).toString()
        ));

        inputObserver();
        signInObserver();
        progressBarObserver();
    }

    private void inputObserver() {
        viewmodel.areInputsValid().observe(this, it -> {
            if (!it.getName()) {
                binding.studentName.setError("Invalid name");
            }

            if (!it.getStudentID()) {
                binding.studentId.setError("Invalid Student ID");
            }

            if (!it.getEmail()) {
                binding.studentEmail.setError("Invalid Email");
            }

            if (it.getName() && it.getStudentID() && it.getEmail()) {
                viewmodel.loginUsingFirebase(
                        Objects.requireNonNull(binding.studentEmail.getText()).toString(),
                        Objects.requireNonNull(binding.studentId.getText()).toString());
            }
        });
    }

    private void signInObserver() {
        viewmodel.isLoggedIn().observe(this, aBoolean -> {
            if (aBoolean){
                moveToHomeActivity();
            }
        });

        viewmodel.isFailed().observe(this, aBoolean -> {
            if(aBoolean){
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void progressBarObserver() {
        viewmodel.loading().observe(this, aBoolean -> {
            if(aBoolean){
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(this, "see", Toast.LENGTH_SHORT).show();
            }
            else{
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void moveToHomeActivity(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    // FIXME: 6/1/2022 do progressbar properly
}
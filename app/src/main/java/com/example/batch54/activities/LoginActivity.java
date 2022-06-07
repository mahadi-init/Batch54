package com.example.batch54.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.batch54.databinding.ActivityLoginBinding;
import com.example.batch54.viewmodels.LoginActivityViewmodel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private LoginActivityViewmodel viewmodel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewmodel = new ViewModelProvider(this).get(LoginActivityViewmodel.class);

        binding.submit.setOnClickListener(v -> viewmodel.setValues(
                Objects.requireNonNull(binding.studentName.getText()).toString(),
                Objects.requireNonNull(binding.studentId.getText()).toString(),
                Objects.requireNonNull(binding.studentEmail.getText()).toString()
        ));

        inputObserver();
        signInObserver();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            moveToHomeActivity();
        }
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

            if(it.getName() && it.getStudentID() && it.getEmail()){
                binding.progressbar.setVisibility(View.VISIBLE);
                viewmodel.loginUsingFirebase(
                        Objects.requireNonNull(binding.studentEmail.getText()).toString(),
                        Objects.requireNonNull(binding.studentId.getText()).toString()
                );
            }
        });
    }

    private void signInObserver() {
        viewmodel.isLoggedIn().observe(this, aBoolean -> {
            if (aBoolean){
                binding.progressbar.setVisibility(View.GONE);

                SharedPreferences sharedpreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("login_name", Objects.requireNonNull(binding.studentName.getText()).toString());
                editor.apply();

                moveToHomeActivity();
            }
        });

        viewmodel.isFailed().observe(this, aBoolean -> {
            if(aBoolean){
                binding.progressbar.setVisibility(View.GONE);
                Snackbar.make(binding.loginLayout, "Login Failed", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToHomeActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}


// TODO: 6/2/2022 work with name
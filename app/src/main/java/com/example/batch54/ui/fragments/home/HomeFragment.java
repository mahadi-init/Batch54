package com.example.batch54.ui.fragments.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.batch54.databinding.FragmentHomeBinding;
import com.example.batch54.databinding.StoryLayoutBinding;
import com.example.batch54.ui.fragments.home.adapter.HomeAdapter;
import com.example.batch54.ui.fragments.home.viewmodel.HomeFragmentViewmodel;
import com.example.batch54.ui.fragments.home.viewmodel.StoryLayoutViewmodel;
import com.example.batch54.utils.formatter.Formatter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeFragmentViewmodel viewmodel;
    private StoryLayoutViewmodel storyViewmodel;
    private Dialog dialog;
    private StoryLayoutBinding storyBinding;
    private Uri imageUri = null;
    private String login_name;
    private final String todayDate = Formatter.getTodayDate();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        storyBinding = StoryLayoutBinding.inflate(inflater, container, false);
        viewmodel = new ViewModelProvider(this).get(HomeFragmentViewmodel.class);
        storyViewmodel = new ViewModelProvider(this).get(StoryLayoutViewmodel.class);

        SharedPreferences sharedpreferences = this.requireActivity().getSharedPreferences("login_data", Context.MODE_PRIVATE);
        login_name = sharedpreferences.getString("login_name", "DEFAULT");


        initializeDialog();
        homeObservers();

        binding.homeRecyclerview.setAdapter(new HomeAdapter(new ArrayList<>()));
        binding.homeRecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        binding.extendedFab.setOnClickListener(v -> {
            storyBinding.name.setText(login_name);
            storyBinding.date.setText(todayDate);
            dialog.show();
        });

        storyBinding.name.setEnabled(false);
        storyBinding.date.setEnabled(false);

        storyBinding.buttonLoadPicture.setOnClickListener(v -> openGallery());

        Thread thread = new Thread(storyViewmodel);
        storyBinding.confirm.setOnClickListener(v -> {
            if (imageUri != null) {
                storyViewmodel.setValues(
                        imageUri,
                        Objects.requireNonNull(storyBinding.title.getText()).toString(),
                        login_name,
                        todayDate
                        );

                thread.start();
            }
            else{
                Snackbar.make(binding.homeFragmentLayout,"Can't Upload!! Image Can't be empty",Snackbar.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });

        storyViewmodel.isSucceed().observe(getViewLifecycleOwner(), aBoolean -> {
            if(aBoolean){
                Snackbar.make(binding.homeFragmentLayout,"Upload done! refresh",Snackbar.LENGTH_SHORT).show();
            }
            else{
                Snackbar.make(binding.homeFragmentLayout,"Upload failed!!", Snackbar.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void initializeDialog(){
        dialog = new Dialog(this.getActivity());
        dialog.setContentView(storyBinding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    imageUri = data.getData();
                    storyBinding.image.setImageURI(imageUri);
                } else {
                    //cancelled
                    Toast.makeText(getActivity(), "Cancelled...", Toast.LENGTH_SHORT).show();
                }
            }
    );

    private void homeObservers() {
        Thread thread = new Thread(viewmodel);
        thread.start();

        viewmodel.isSucceed().observe(getViewLifecycleOwner(), aBoolean ->
                viewmodel.homeStories().observe(getViewLifecycleOwner(), homeStories ->
                        binding.homeRecyclerview.setAdapter(new HomeAdapter(homeStories))));

        viewmodel.isFailed().observe(getViewLifecycleOwner(), aBoolean ->
                Snackbar.make(binding.homeFragmentLayout, "Can't Load", Snackbar.LENGTH_SHORT).show());
    }
}
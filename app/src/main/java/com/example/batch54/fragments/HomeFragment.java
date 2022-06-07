package com.example.batch54.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.batch54.R;
import com.example.batch54.adapters.HomeAdapter;
import com.example.batch54.databinding.FragmentHomeBinding;
import com.example.batch54.databinding.StoryLayoutBinding;
import com.example.batch54.viewmodels.HomeFragmentViewmodel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeFragmentViewmodel viewmodel;
    private Dialog dialog;
    private StoryLayoutBinding storyBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        storyBinding = StoryLayoutBinding.inflate(inflater,container,false);
        viewmodel = new ViewModelProvider(this).get(HomeFragmentViewmodel.class);

        binding.homeRecyclerview.setAdapter(new HomeAdapter(new ArrayList<>()));
        binding.homeRecyclerview.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        setDialog();
        observers();

        binding.extendedFab.setOnClickListener(v -> dialog.show());
        storyBinding.buttonLoadPicture.setOnClickListener(v -> {
            openGallery();
        });

        storyBinding.confirm.setOnClickListener(v -> {
            dialog.dismiss();
        });

        return binding.getRoot();
    }

    private void setDialog(){
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
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        Uri imageUri = data.getData();

                        storyBinding.image.setImageURI(imageUri);
                    }
                    else {
                        //cancelled
                        Toast.makeText(getActivity(), "Cancelled...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private void observers() {
        Thread thread = new Thread(viewmodel);
        thread.start();

        viewmodel.isSucceed().observe(getViewLifecycleOwner(), aBoolean ->
                viewmodel.homeStories().observe(getViewLifecycleOwner(), homeStories ->
                        binding.homeRecyclerview.setAdapter(new HomeAdapter(homeStories))));

        viewmodel.isFailed().observe(getViewLifecycleOwner(), aBoolean -> {
            Snackbar.make(binding.homeFragmentLayout, "Login Failed", Snackbar.LENGTH_SHORT).show();
        });
    }
}
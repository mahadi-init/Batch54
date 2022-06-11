package com.example.batch54.ui.fragments.home.viewmodel;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.batch54.ui.fragments.home.model.StoryModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StoryLayoutViewmodel extends ViewModel implements Runnable {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final MutableLiveData<StoryModel> _storyModel = new MutableLiveData<>();

    private final MutableLiveData<Boolean> _isSucceed = new MutableLiveData<>();
    public LiveData<Boolean> isSucceed() {
        return _isSucceed;
    }

    public void setValues(Uri uri, @NonNull String title, String author, String date) {
        if (title.length() == 0) {
            title = "No Caption";
        }

        _storyModel.setValue(new StoryModel(
                uri,
                title,
                author,
                date,
                "0", "0"
        ));
    }

    @Override
    public void run() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        String imageName = Objects.requireNonNull(_storyModel.getValue()).getUri().getLastPathSegment();

        StorageReference imageRef = storageRef.child(imageName);
        UploadTask uploadTask = imageRef.putFile(Objects.requireNonNull(_storyModel.getValue()).getUri());

        uploadTask
                .addOnSuccessListener(taskSnapshot -> {
                    Map<String, Object> story = new HashMap<>();
                    story.put("image", imageName);
                    story.put("title", _storyModel.getValue().getTitle());
                    story.put("author", _storyModel.getValue().getAuthor());
                    story.put("date", _storyModel.getValue().getDate());

                    db.collection("stories").document()
                            .set(story)
                            .addOnSuccessListener(aVoid -> _isSucceed.setValue(true))
                            .addOnFailureListener(e -> _isSucceed.setValue(false));
                })
                .addOnFailureListener(e -> _isSucceed.setValue(false));
    }
}
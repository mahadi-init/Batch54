package com.example.batch54.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.batch54.models.HomeModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class HomeFragmentViewmodel extends ViewModel implements Runnable{
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final MutableLiveData<Boolean> _isSucceed = new MutableLiveData<>();
    public LiveData<Boolean> isSucceed() {
        return _isSucceed;
    }

    private final MutableLiveData<Boolean> _isFailed = new MutableLiveData<>();
    public LiveData<Boolean> isFailed() {
        return _isFailed;
    }

    private final MutableLiveData<ArrayList<HomeModel>> _homeStories = new MutableLiveData<>();
    public LiveData<ArrayList<HomeModel>> homeStories() {
        return _homeStories;
    }

    private void setHomeStories() {
        db.collection("stories")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        _isSucceed.setValue(true);

                        ArrayList<HomeModel> list = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add(new HomeModel(
                                    document.getString("image"),
                                    document.getString("title"),
                                    document.getString("author"),
                                    document.getString("date"),
                                    document.getString("upvote"),
                                    document.getString("downvote")
                            ));
                        }
                        _homeStories.setValue(list);
                    } else {
                        _isFailed.setValue(true);
                    }
                });
    }

    @Override
    public void run() {
        setHomeStories();
    }
}

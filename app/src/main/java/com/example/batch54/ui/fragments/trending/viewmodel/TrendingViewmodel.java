package com.example.batch54.ui.fragments.trending.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class TrendingViewmodel extends ViewModel implements Runnable {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final MutableLiveData<ArrayList<String>> _trendingData = new MutableLiveData<>();
    public LiveData<ArrayList<String>> getTrendingData() {
        return _trendingData;
    }


    @Override
    public void run() {
        db.collection("messages")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<String> list = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add(document.getString("message"));
                        }
                        _trendingData.setValue(list);
                    } else {
//                        _isFailed.setValue(true);
                    }
                });
    }
}

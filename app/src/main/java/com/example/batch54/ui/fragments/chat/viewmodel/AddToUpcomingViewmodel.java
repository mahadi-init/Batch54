package com.example.batch54.ui.fragments.chat.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddToUpcomingViewmodel extends ViewModel implements Runnable {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final MutableLiveData<String> _hashedMessage = new MutableLiveData<>();

    public void setHashedMessage(String message) {
        _hashedMessage.setValue(message);
    }

    private final MutableLiveData<Boolean> _isSuccessfullyAdded = new MutableLiveData<>();

    public LiveData<Boolean> getIsSuccessfullyAdded() {
        return _isSuccessfullyAdded;
    }

    @Override
    public void run() {
        Map<String, String> message = new HashMap<>();
        message.put("message", _hashedMessage.getValue());

        db.collection("messages")
                .add(message)
                .addOnSuccessListener(documentReference -> _isSuccessfullyAdded.setValue(true));
    }
}

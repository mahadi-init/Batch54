package com.example.batch54.ui.fragments.chat.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatViewmodel extends ViewModel implements Runnable{

    private final MutableLiveData<String> _message = new MutableLiveData<>();
    public LiveData<String> getMessage(){
        return _message;
    }

    private final MutableLiveData<Boolean> _isMessageHashed = new MutableLiveData<>();
    public LiveData<Boolean> getIsMessageHashed(){
        return _isMessageHashed;
    }


    public void setMessage(@NonNull String message){
        if(message.charAt(0) == '#'){
            _isMessageHashed.setValue(true);
        }

        _message.setValue(message);
    }

    @Override
    public void run() {

    }
}

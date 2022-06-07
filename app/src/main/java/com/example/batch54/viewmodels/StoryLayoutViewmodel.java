package com.example.batch54.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.batch54.models.StoryModel;

public class StoryLayoutViewmodel extends ViewModel implements Runnable{

    private final MutableLiveData<StoryModel> _storyModel = new MutableLiveData<>();
    public LiveData<StoryModel> storyModel() {
        return _storyModel;
    }



    @Override
    public void run() {
        _storyModel.setValue(new StoryModel("","","",null));
    }
}
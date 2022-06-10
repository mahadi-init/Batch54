package com.example.batch54.ui.activities.main.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.batch54.ui.activities.main.enums.MainActivityEnum;

public class MainActivityViewmodel extends ViewModel {

    private final MutableLiveData<MainActivityEnum> _currentActiveFragment = new MutableLiveData<>();
    public LiveData<MainActivityEnum> currentActiveFragment(){
        return _currentActiveFragment;
    }

    public MainActivityViewmodel() {
        _currentActiveFragment.setValue(MainActivityEnum.HOME);
    }

    public void setActiveFragment(MainActivityEnum fragmentName){
        _currentActiveFragment.setValue(fragmentName);
    }
}

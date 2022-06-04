package com.example.batch54.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.batch54.enums.MainActivityFragments;

public class MainActivityViewmodel extends ViewModel {

    private final MutableLiveData<MainActivityFragments> _currentActiveFragment = new MutableLiveData<>();
    public LiveData<MainActivityFragments> currentActiveFragment(){
        return _currentActiveFragment;
    }

    public MainActivityViewmodel() {
        _currentActiveFragment.setValue(MainActivityFragments.HOME);
    }

    public void setActiveFragment(MainActivityFragments fragmentName){
        _currentActiveFragment.setValue(fragmentName);
    }
}

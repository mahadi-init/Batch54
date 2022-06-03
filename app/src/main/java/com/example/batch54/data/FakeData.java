package com.example.batch54.data;

import com.example.batch54.models.UpcomingModel;

import java.util.ArrayList;

public class FakeData {

    public ArrayList<UpcomingModel> getUpcomingData(){
        ArrayList<UpcomingModel> upcomingModelArrayList = new ArrayList<>();
        upcomingModelArrayList.add(new UpcomingModel("Math exam", "Feb 20","First 3 chapter"));
        upcomingModelArrayList.add(new UpcomingModel("Math exam", "Feb 20","First 3 chapter"));
        upcomingModelArrayList.add(new UpcomingModel("Math exam", "Feb 20","First 3 chapter"));
        upcomingModelArrayList.add(new UpcomingModel("Math exam", "Feb 20","First 3 chapter"));
        upcomingModelArrayList.add(new UpcomingModel("Math exam", "Feb 20","First 3 chapter"));

        return upcomingModelArrayList;
    }


    private void lol(){

    }
}

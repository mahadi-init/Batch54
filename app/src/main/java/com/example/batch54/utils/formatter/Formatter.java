package com.example.batch54.utils.formatter;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Formatter {

    @NonNull
    public static String getTodayDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        return dateFormat.format(date);
    }
}

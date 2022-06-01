package com.example.batch54.utils.validation;


import androidx.annotation.NonNull;
import androidx.core.util.PatternsCompat;

import java.util.regex.Pattern;

public class Validity {

    public Boolean isEmailValid(@NonNull String email) {
        return email.length() > 11 &&
                PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() &&
                email.endsWith("@diu.edu.bd");
    }

    public Boolean isNameValid(@NonNull String name){
        return name.length() > 2;
    }

    public Boolean isStudentIdValid(@NonNull String studentID){
        Pattern pattern = Pattern.compile("[0-9]{3}" + "-" + "[0-9]{2}" + "-" + "[0-9]{5}");

        return studentID.length() == 12 && pattern.matcher(studentID).matches();
    }
}

package com.example.batch54.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.batch54.data.LoginInputsCheck;
import com.example.batch54.utils.validation.Validity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivityViewmodel extends ViewModel {
    private static final LoginInputsCheck inputsCheck = new LoginInputsCheck();
    private static final Validity validity = new Validity();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private final MutableLiveData<Boolean> _isLoggedIn = new MutableLiveData<>();
    public LiveData<Boolean> isLoggedIn() {
        return _isLoggedIn;
    }

    private final MutableLiveData<Boolean> _isFailed = new MutableLiveData<>();
    public LiveData<Boolean> isFailed() {
        return _isFailed;
    }

    private final MutableLiveData<LoginInputsCheck> _areInputsValid = new MutableLiveData<>();
    public LiveData<LoginInputsCheck> areInputsValid() {
        return _areInputsValid;
    }

    public void setValues(String name, String studentID, String email) {
        inputsCheck.setName(validity.isNameValid(name));
        inputsCheck.setStudentID(validity.isStudentIdValid(studentID));
        inputsCheck.setEmail(validity.isEmailValid(email));

        _areInputsValid.setValue(inputsCheck);
    }

    public void loginUsingFirebase(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(e -> _isLoggedIn.setValue(true))
                .addOnFailureListener(e -> _isFailed.setValue(true));
    }
}

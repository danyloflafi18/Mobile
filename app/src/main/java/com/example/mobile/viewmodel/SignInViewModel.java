package com.example.mobile.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.validatorinterface.ICredentialValidator;
import com.example.mobile.validators.EmailValidator;
import com.example.mobile.validators.PasswordValidator;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

public class SignInViewModel extends ViewModel {
    private final ICredentialValidator emailValidator = new EmailValidator();
    private final ICredentialValidator passwordValidator = new PasswordValidator();

    private final MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public boolean signIn(String email, String password) {
        boolean isDisplayStartScreen = false;
        boolean isValidEmail = emailValidator.isValid(email);
        boolean isValidPassword = passwordValidator.isValid(password);

        if (!isValidEmail) {
            error.setValue("Invalid credential!");
        } else if (!isValidPassword) {
            error.setValue("Invalid credential!");
        } else {

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            Log.d(TAG, "logInWithEmail:success");
                        } else {
                            // If sign up fails, display a message to the user.
                            Log.w(TAG, "logInWithEmail:failure", task.getException());
                        }
                    });
            isDisplayStartScreen = true;

        }
        return isDisplayStartScreen;
    }

    public MutableLiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}

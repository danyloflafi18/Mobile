package com.example.mobile.peopleapi.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.peopleapi.domain.validatorInterface.ICredentialValidator;
import com.example.mobile.peopleapi.domain.validators.EmailValidator;
import com.example.mobile.peopleapi.domain.validators.PasswordValidator;
import com.google.firebase.auth.FirebaseAuth;

import timber.log.Timber;

public class SignInViewModel extends ViewModel {
    private final ICredentialValidator emailValidator = new EmailValidator();
    private final ICredentialValidator passwordValidator = new PasswordValidator();

    private final MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private void firebaseSignIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        isLoggedIn.setValue(true);
                        Timber.d("Logged in");
                    } else {
                        isLoggedIn.setValue(false);
                        Timber.e("User with those credential doesn't exist!");
                    }
                });
    }

    public void signIn(String email, String password) {
        boolean isValidEmail = emailValidator.isValid(email);
        boolean isValidPassword = passwordValidator.isValid(password);

        if (!isValidEmail || !isValidPassword) {
            error.setValue("Invalid credential!");
        } else {
            firebaseSignIn(email, password);
        }
    }

    public MutableLiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}

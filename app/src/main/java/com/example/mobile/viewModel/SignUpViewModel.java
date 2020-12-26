package com.example.mobile.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.validatorInterface.IConfirmPasswordValidator;
import com.example.mobile.validatorInterface.ICredentialValidator;
import com.example.mobile.validators.ConfirmPassword;
import com.example.mobile.validators.EmailValidator;
import com.example.mobile.validators.NameValidator;
import com.example.mobile.validators.PasswordValidator;
import com.google.firebase.auth.FirebaseAuth;

import timber.log.Timber;

public class SignUpViewModel extends ViewModel {
    private final ICredentialValidator nameValidator = new NameValidator();
    private final ICredentialValidator emailValidator = new EmailValidator();
    private final ICredentialValidator passwordValidator = new PasswordValidator();
    private final IConfirmPasswordValidator confirmPasswordValidator = new ConfirmPassword();

    private final MutableLiveData<Boolean> isSignedUp = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private void firebaseSignUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        isSignedUp.setValue(true);
                        Timber.d("Signed up");
                    } else {
                        isSignedUp.setValue(false);
                        Timber.e("User with those credential already exist!");
                    }
                });
    }

    public void signUp(String name, String email, String password, String confirmPassword) {
        boolean isValidName = nameValidator.isValid(name);
        boolean isValidEmail = emailValidator.isValid(email);
        boolean isValidPassword = passwordValidator.isValid(password);
        boolean isValidConfirmPassword = confirmPasswordValidator.isValidConfirmPassword(password, confirmPassword);

        if (!isValidName) {
            error.setValue("Invalid name!");
        } else if (!isValidEmail) {
            error.setValue("Invalid email!");
        } else if (!isValidPassword) {
            error.setValue("Invalid password!");
        } else if (!isValidConfirmPassword) {
            error.setValue("Confirm password doesn't equal original password!");
        } else {
            firebaseSignUp(email, password);
        }
    }


    public MutableLiveData<Boolean> getIsSignedUp() {
        return isSignedUp;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}
package com.example.mobile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.validatorinterface.IConfirmPasswordValidator;
import com.example.mobile.validatorinterface.ICredentialValidator;
import com.example.mobile.validators.ConfirmPassword;
import com.example.mobile.validators.EmailValidator;
import com.example.mobile.validators.NameValidator;
import com.example.mobile.validators.PasswordValidator;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpViewModel extends ViewModel {
    private final ICredentialValidator nameValidator = new NameValidator();
    private final ICredentialValidator emailValidator = new EmailValidator();
    private final ICredentialValidator passwordValidator = new PasswordValidator();
    private final IConfirmPasswordValidator confirmPasswordValidator = new ConfirmPassword();

    private final MutableLiveData<Boolean> isSignedUp = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public boolean signUp(String name, String email, String password, String confirmPassword) {
        boolean isDisplayStartScreen = false;
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
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Task::isSuccessful);
            isDisplayStartScreen = true;
        }
        return isDisplayStartScreen;
    }


    public MutableLiveData<Boolean> getIsSignedUp() {
        return isSignedUp;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}
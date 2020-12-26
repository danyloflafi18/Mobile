package com.example.mobile.peopleapi.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.peopleapi.domain.validatorInterface.ICredentialValidator;
import com.example.mobile.peopleapi.domain.validators.EmailValidator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import timber.log.Timber;

public class MyProfileViewModel extends ViewModel {
    private final ICredentialValidator emailValidator = new EmailValidator();
    private final MutableLiveData<Boolean> isEmailChanged = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    boolean isValidPassword;

    public void updateEmail(String email){
        validateEmail(email);
        firebaseUpdatePassword(email);
    }

    private void firebaseUpdatePassword(String email) {
        FirebaseUser myUser = FirebaseAuth.getInstance().getCurrentUser();

        if (myUser != null) {
            myUser.updateEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Timber.i("Email updated");
                            isEmailChanged.setValue(true);
                        } else {
                            Timber.i("Couldn't update email");
                            isEmailChanged.setValue(false);
                        }
                    });
        } else {
            error.setValue("Incorrect email");
        }
    }

    private void validateEmail(String email) {
        isValidPassword = emailValidator.isValid(email);
    }

    public MutableLiveData<Boolean> getIsEmailChanged() {
        return isEmailChanged;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}

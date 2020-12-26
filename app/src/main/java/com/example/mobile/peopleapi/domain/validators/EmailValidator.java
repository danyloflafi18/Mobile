package com.example.mobile.peopleapi.domain.validators;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.mobile.peopleapi.domain.validatorInterface.ICredentialValidator;

public class EmailValidator  implements ICredentialValidator {
    @Override
    public boolean isValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

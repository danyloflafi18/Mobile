package com.example.mobile.validators;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.mobile.validatorinterface.ICredentialValidator;

public class EmailValidator  implements ICredentialValidator {
    @Override
    public boolean isValid(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

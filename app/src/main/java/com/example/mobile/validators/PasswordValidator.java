package com.example.mobile.validators;

import android.text.TextUtils;

import com.example.mobile.validatorInterface.ICredentialValidator;

public class PasswordValidator implements ICredentialValidator {
    private static final int PASSWORD_MIN_LENGTH = 8;
    @Override
    public boolean isValid(String password) {
        return !TextUtils.isEmpty(password) || password.length() > PASSWORD_MIN_LENGTH;
    }
}

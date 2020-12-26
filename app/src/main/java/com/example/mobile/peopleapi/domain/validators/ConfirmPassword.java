package com.example.mobile.peopleapi.domain.validators;

import android.text.TextUtils;

import com.example.mobile.peopleapi.domain.validatorInterface.IConfirmPasswordValidator;

public class ConfirmPassword implements IConfirmPasswordValidator {
    @Override
    public boolean isValidConfirmPassword(String password, String confirmPassword) {
        return !TextUtils.isEmpty(confirmPassword) && confirmPassword.equals(password);
    }
}

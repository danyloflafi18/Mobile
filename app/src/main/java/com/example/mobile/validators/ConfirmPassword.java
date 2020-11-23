package com.example.mobile.validators;

import android.text.TextUtils;

import com.example.mobile.validatorinterface.IConfirmPasswordValidator;

public class ConfirmPassword implements IConfirmPasswordValidator {
    @Override
    public boolean isValidConfirmPassword(String password, String confirmPassword) {
        return !TextUtils.isEmpty(confirmPassword) && confirmPassword.equals(password);
    }
}

package com.example.mobile.validators;

import android.text.TextUtils;

import com.example.mobile.validatorinterface.ICredentialValidator;

public class NameValidator implements ICredentialValidator {
    @Override
    public boolean isValid(String name) {
        return !TextUtils.isEmpty(name);
    }
}

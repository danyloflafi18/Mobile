package com.example.mobile.peopleapi.domain.validators;

import android.text.TextUtils;

import com.example.mobile.peopleapi.domain.validatorInterface.ICredentialValidator;

public class NameValidator implements ICredentialValidator {
    @Override
    public boolean isValid(String name) {
        return !TextUtils.isEmpty(name);
    }
}

package com.example.mobile.peopleapi.domain.validatorInterface;

public interface IConfirmPasswordValidator {
    boolean isValidConfirmPassword(String password, String confirmPassword);
}

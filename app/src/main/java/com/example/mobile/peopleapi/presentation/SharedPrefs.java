package com.example.mobile.peopleapi.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobile.peopleapi.presentation.fragments.StartScreenFragment;

public class SharedPrefs {
    SharedPreferences prefs;

    private static final String KEY_USER_NAME = "logged_user_name";

    public void initSharedPrefs(StartScreenFragment fragment) {
        prefs = fragment.getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
    }

    public void setUserName(String userName) {
        prefs.edit().putString(KEY_USER_NAME, userName).apply();
    }
}

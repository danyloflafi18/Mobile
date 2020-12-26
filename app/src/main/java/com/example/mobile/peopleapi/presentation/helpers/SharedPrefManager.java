package com.example.mobile.peopleapi.presentation.helpers;

import androidx.fragment.app.FragmentActivity;

import com.example.mobile.peopleapi.presentation.shared_pref.SharedPrefs;

public class SharedPrefManager {
    public SharedPrefs initSharedPrefs(FragmentActivity activity) {
        SharedPrefs sharedPrefs = new SharedPrefs();
        sharedPrefs.initSharedPrefs(activity);
        return sharedPrefs;
    }
}

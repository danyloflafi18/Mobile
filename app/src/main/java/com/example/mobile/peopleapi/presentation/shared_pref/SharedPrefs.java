package com.example.mobile.peopleapi.presentation.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

public class SharedPrefs {
    private SharedPreferences prefs;

    private static final String LANGUAGE_KEY = "logged_user_name";

    public void initSharedPrefs(FragmentActivity activity) {
        prefs = activity.getSharedPreferences("app_data", Context.MODE_PRIVATE);
    }

    public void setLanguage(String language) {
        prefs.edit().putString(LANGUAGE_KEY, language).apply();
    }

    public String getLanguage() {
        return prefs.getString(LANGUAGE_KEY, "en");
    }
}

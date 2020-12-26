package com.example.mobile.peopleapi.presentation.helpers;

import android.app.AlertDialog;
import android.content.Context;

public class AlertManager {
    private Context context;

    public AlertManager(Context context) {
        this.context = context;
    }

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

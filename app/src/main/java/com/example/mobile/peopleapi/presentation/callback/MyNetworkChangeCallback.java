package com.example.mobile.peopleapi.presentation.callback;

import com.example.mobile.peopleapi.presentation.helpers.AlertManager;
import com.example.mobile.peopleapi.presentation.listener.ConnectionChangeCallback;

public class MyNetworkChangeCallback implements ConnectionChangeCallback {
    private AlertManager alertManager;

    public MyNetworkChangeCallback(AlertManager alertManager) {
        this.alertManager = alertManager;
    }

    @Override
    public void onConnectionsStatusChange(boolean isConnected) {
        if (!isConnected) {
            alertManager.showAlertDialog(
                    "Failed to connect",
                    "No internet connection!"
            );
        }
    }
}

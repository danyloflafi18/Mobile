package com.example.mobile.peopleapi.presentation.callback;

import android.net.ConnectivityManager;
import android.net.Network;

import androidx.annotation.NonNull;

import com.example.mobile.peopleapi.presentation.listener.ConnectionChangeCallback;

public class NetworkChangeCallback extends ConnectivityManager.NetworkCallback {
    private final ConnectionChangeCallback changeCallback;

    public NetworkChangeCallback(ConnectionChangeCallback changeCallback) {
        this.changeCallback = changeCallback;
    }

    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        changeCallback.onConnectionsStatusChange(true);
    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        changeCallback.onConnectionsStatusChange(false);
    }
}
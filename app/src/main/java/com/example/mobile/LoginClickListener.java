package com.example.mobile;

import android.view.View;
import android.widget.Toast;

public class LoginClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),
                "successfully logged in!",Toast.LENGTH_SHORT).show();
    }

}

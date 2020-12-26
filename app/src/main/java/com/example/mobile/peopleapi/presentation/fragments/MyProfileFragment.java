package com.example.mobile.peopleapi.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import androidx.fragment.app.Fragment;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.viewModel.MyProfileViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class MyProfileFragment extends Fragment {

    private MyProfileViewModel profileViewModel;
    private TextInputEditText editEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View profileView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        initUI(profileView);
        registerViewModel();

        return profileView;
    }

    private void initUI(View profileView) {
        editEmail = profileView.findViewById(R.id.email_change_input);
        Button changeButton = profileView.findViewById(R.id.email_change_button);

        changeButton.setOnClickListener(view -> profileViewModel.updateEmail(editEmail.getText().toString()));
    }

    private void registerViewModel() {
        profileViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        getIsEmailChanged();
        getError();
    }

    private void getError() {
        profileViewModel.getError().observe(getViewLifecycleOwner(), this::showToast);
    }

    private void getIsEmailChanged() {
        profileViewModel.getIsEmailChanged().observe(getViewLifecycleOwner(), isPasswordChanged -> {
            if (isPasswordChanged) {
                showToast("Email is changed");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
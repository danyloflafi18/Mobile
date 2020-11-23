package com.example.mobile.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.StartScreenActivity;
import com.example.mobile.viewmodel.SignUpViewModel;


public class SignUpFragment extends Fragment {

    private SignUpViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        EditText nameEditText = rootView.findViewById(R.id.nameSignUp);
        EditText emailAddressEditText = rootView.findViewById(R.id.emailSignUp);
        EditText passwordEditText = rootView.findViewById(R.id.passwordSignUp);
        EditText confirmPasswordEditText = rootView.findViewById(R.id.confirmPasswordSignUp);
        Button signUpButton = rootView.findViewById(R.id.signUpButton);

        registerViewModel();

        signUpButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            String email = emailAddressEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            if(viewModel.signUp(name, email, password, confirmPassword)) {
                Intent startScreenIntent = new Intent(getContext(), StartScreenActivity.class);
                startActivity(startScreenIntent);
            }
        });
        return rootView;
    }

    private void registerViewModel() {
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        viewModel.getIsSignedUp().observe(this, isSignedUp -> {
            if (isSignedUp) {
                showMessage("Successfully signed up!");
            }else{
                showMessage("Signing up is failed!");
            }
        });
        viewModel.getError().observe(this, this::showMessage);
    }

    private void showMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
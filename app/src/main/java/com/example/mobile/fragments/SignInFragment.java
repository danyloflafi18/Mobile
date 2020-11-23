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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.StartScreenActivity;
import com.example.mobile.viewmodel.SignInViewModel;
import com.example.mobile.SignUpActivity;

public class SignInFragment extends Fragment {

    private SignInViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        EditText emailAddressEditText = rootView.findViewById(R.id.emailSignIn);
        EditText passwordEditText = rootView.findViewById(R.id.passwordSignIn);
        Button signInButton = rootView.findViewById(R.id.signInButton);
        TextView signUp = rootView.findViewById(R.id.goToSignUp);


        registerViewModel();

        signInButton.setOnClickListener(view -> {
            String password = passwordEditText.getText().toString();
            String email = emailAddressEditText.getText().toString();
            if(viewModel.signIn(email, password)){
                Intent startScreenIntent = new Intent(getContext(), StartScreenActivity.class);
                startActivity(startScreenIntent);
            }
        });
        signUp.setOnClickListener(view -> {
            Intent signUpIntent = new Intent(getContext(), SignUpActivity.class);
            startActivity(signUpIntent);
        });
        return rootView;
    }

    private void registerViewModel() {
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        viewModel.getIsLoggedIn().observe(this, isLoggedIn -> {
            if (isLoggedIn) {
                showMessage("Successfully logged in!");
            }else{
                showMessage("Logging is failed!");
            }
        });
        viewModel.getError().observe(this, this::showMessage);
    }

    private void showMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
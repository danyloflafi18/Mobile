package com.example.mobile.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.mobile.R;
import com.example.mobile.clickListener.ISignUp;
import com.example.mobile.viewModel.SignUpViewModel;

import org.jetbrains.annotations.NotNull;

public class SignUpFragment extends Fragment {

    private SignUpViewModel signUpViewModel;
    private EditText nameEditText;
    private EditText emailAddressEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signUpButton;
    private Toolbar signUpToolbar;
    private ISignUp signUp;
    private ISignInPage signInPage;

    public interface ISignInPage {
        void onSignInPageClicked();
    }

    private final View.OnClickListener onSignUpClickListener = view -> {
        String name = nameEditText.getText().toString();
        String email = emailAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        signUpViewModel.signUp(name, email, password, confirmPassword);
    };

    private final View.OnClickListener onToolbarClickListener = view -> signInPage.onSignInPageClicked();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            signInPage = (SignUpFragment.ISignInPage) context;
            signUp = (ISignUp) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ISignUpPage");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signInPage = null;
        signUp = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initUI(rootView);

        signUpButton.setOnClickListener(onSignUpClickListener);
        signUpToolbar.setNavigationOnClickListener(onToolbarClickListener);

        registerViewModel();
        checkIsSignedUp();
        return rootView;
    }

    private void registerViewModel() {
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        signUpViewModel.getError().observe(getViewLifecycleOwner(), this::showMessage);
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void initUI(View signUpView) {
        nameEditText = signUpView.findViewById(R.id.nameSignUp);
        emailAddressEditText = signUpView.findViewById(R.id.emailSignUp);
        passwordEditText = signUpView.findViewById(R.id.passwordSignUp);
        confirmPasswordEditText = signUpView.findViewById(R.id.confirmPasswordSignUp);
        signUpButton = signUpView.findViewById(R.id.signUpButton);
        signUpToolbar = signUpView.findViewById(R.id.toolbarSignUp);
    }

    private void toStartScreen(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setTitle("Info");
        builder.setPositiveButton("OK", (dialog, which) -> {
            signUp.onSignUpClicked();
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void checkIsSignedUp() {
        signUpViewModel.getIsSignedUp().observe(getViewLifecycleOwner(), isSignedUp -> {
            if (isSignedUp) {
                toStartScreen("Signed up was successful!");
            } else {
                showMessage("Could not sign up!");
            }
        });
    }
}
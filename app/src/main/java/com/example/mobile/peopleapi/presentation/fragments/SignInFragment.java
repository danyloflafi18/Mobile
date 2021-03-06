package com.example.mobile.peopleapi.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.clickListener.ISignIn;
import com.example.mobile.peopleapi.presentation.clickListener.OnLanguageListener;
import com.example.mobile.peopleapi.presentation.viewModel.SignInViewModel;

import org.jetbrains.annotations.NotNull;

public class SignInFragment extends Fragment {

    private SignInViewModel signInViewModel;
    private EditText emailAddressEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private Button languageButton;
    private TextView signUp;
    private ISignUpPage signUpPage;
    private ISignIn signIn;
    private OnLanguageListener onLanguageListener;

    public interface ISignUpPage {
        void onSignUpPageClicked();
    }

    private final View.OnClickListener onSignInClickListener = view -> {
        String email = emailAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        signInViewModel.signIn(email, password);
    };

    private final View.OnClickListener onSignUpClickListener = view -> signUpPage.onSignUpPageClicked();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            signUpPage = (ISignUpPage) context;
            signIn = (ISignIn) context;
            onLanguageListener = (OnLanguageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ISignUpPage or ISignIn or OnLanguageListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        signUpPage = null;
        signIn = null;
        onLanguageListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initUI(rootView);

        signInButton.setOnClickListener(onSignInClickListener);
        signUp.setOnClickListener(onSignUpClickListener);

        registerViewModel();

        checkIsLoggedIn();

        return rootView;
    }

    private void registerViewModel() {
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        signInViewModel.getError().observe(getViewLifecycleOwner(), this::showMessage);
    }

    private void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void initUI(View signInView) {
        emailAddressEditText = signInView.findViewById(R.id.emailSignIn);
        passwordEditText = signInView.findViewById(R.id.passwordSignIn);
        signInButton = signInView.findViewById(R.id.signInButton);
        signUp = signInView.findViewById(R.id.goToSignUp);
        languageButton = signInView.findViewById(R.id.language_button);
        languageButton.setOnClickListener(view -> onLanguageListener.onLanguageClick());
    }

    private void toStartScreen() {
        signIn.onSignInClicked();
    }

    private void checkIsLoggedIn() {
        signInViewModel.getIsLoggedIn().observe(getViewLifecycleOwner(), isLoggedIn -> {
            if (isLoggedIn) {
                toStartScreen();
                showMessage("Login is successful!");
            } else {
                showMessage("Could not sign in!");
            }
        });
    }
}
package com.jelvix.jelvixproject.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jelvix.jelvixproject.R;
import com.jelvix.jelvixproject.ui.listeners.AuthListener;
import com.jelvix.jelvixproject.ui.listeners.NavigateListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationFragment extends Fragment {

    @BindView(R.id.registration_email)
    EditText email;

    @BindView(R.id.registration_password)
    EditText password;

    @BindView(R.id.registration_repeat_password)
    EditText repeatPassword;

    private AuthListener authListener;
    private NavigateListener navigateListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() != null) {
            authListener = (AuthListener) getActivity();
            navigateListener = (NavigateListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.registration_btn)
    void registration() {
        authListener.onSignUp(
                email.getText().toString(),
                password.getText().toString(),
                repeatPassword.getText().toString());
    }

    @OnClick(R.id.registration_back)
    void back() {
        navigateListener.navigateToLogin();
    }
}

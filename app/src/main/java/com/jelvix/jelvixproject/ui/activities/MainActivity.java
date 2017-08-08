package com.jelvix.jelvixproject.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jelvix.jelvixproject.R;
import com.jelvix.jelvixproject.mvp.models.User;
import com.jelvix.jelvixproject.mvp.presenters.LoginPresenter;
import com.jelvix.jelvixproject.mvp.views.LoginView;
import com.jelvix.jelvixproject.ui.fragments.LoginFragment;
import com.jelvix.jelvixproject.ui.fragments.ProfileFragment;
import com.jelvix.jelvixproject.ui.fragments.RegistrationFragment;
import com.jelvix.jelvixproject.ui.fragments.UsersFragment;
import com.jelvix.jelvixproject.ui.listeners.AuthListener;
import com.jelvix.jelvixproject.ui.listeners.NavigateListener;
import com.jelvix.jelvixproject.ui.listeners.ProgressListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.jelvix.jelvixproject.utils.Constants.EMAIL_REGEX;
import static com.jelvix.jelvixproject.utils.Constants.PASSWORD_REGEX;
import static com.jelvix.jelvixproject.utils.Constants.USER_BUNDLE_KEY;

public class MainActivity extends MvpAppCompatActivity
        implements LoginView, AuthListener, ProgressListener, NavigateListener {

    @InjectPresenter
    LoginPresenter loginPresenter;

    @BindView(R.id.spinner)
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        ButterKnife.bind(this);
        navigateToLogin();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void onStartRequest() {
        showProgress();
    }

    @Override
    public void onFinishRequest() {
        hideProgress();
    }

    @Override
    public void showProgress() {
        spinner.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        spinner.setVisibility(GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLogin(String email, String password) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            loginPresenter.login(email, password);
        }
    }

    @Override
    public void onSignUp(String email, String password, String repeatPassword) {
        if (isEmailValid(email) && isPasswordsValid(password, repeatPassword)) {
            loginPresenter.registration(email, password);
        }
    }

    @Override
    public void navigateToUsers() {
        switchFragments(new UsersFragment());
    }

    @Override
    public void navigateToSignUp() {
        switchFragments(new RegistrationFragment());
    }

    @Override
    public void navigateToLogin() {
        switchFragments(new LoginFragment());
    }

    @Override
    public void navigateToProfile(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER_BUNDLE_KEY, user);
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.setArguments(bundle);
        switchFragments(profileFragment);
    }

    private void switchFragments(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_container, fragment)
                .commit();
    }

    private boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email) || !email.matches(EMAIL_REGEX)) {
            Toast.makeText(this,
                    getResources().getString(R.string.err_email), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        if (TextUtils.isEmpty(password) || !password.matches(PASSWORD_REGEX)) {
            Toast.makeText(this,
                    getResources().getString(R.string.err_pass), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isPasswordsValid(String password, String repeatPassword) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(repeatPassword)
                || !password.equals(repeatPassword) || !password.matches(PASSWORD_REGEX)) {
            Toast.makeText(this,
                    getResources().getString(R.string.err_identical_pass), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

package com.jelvix.jelvixproject.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.jelvix.jelvixproject.JelvixApplication;
import com.jelvix.jelvixproject.mvp.views.LoginView;
import com.jelvix.jelvixproject.network.AuthAPI;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.jelvix.jelvixproject.network.SerialisedNames.EMAIL;
import static com.jelvix.jelvixproject.network.SerialisedNames.PASSWORD;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject
    Retrofit retrofit;

    private AuthAPI authAPI;

    @Inject
    public LoginPresenter() {
        JelvixApplication.getAppComponent().inject(this);
    }

    public void login(String email, String password) {
        getViewState().onStartRequest();
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put(EMAIL, email);
        loginMap.put(PASSWORD, password);

        AuthAPI authAPI = getAuthAPI();
        authAPI.login(loginMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    successAuth();
                }, e -> {
                    unsuccessAuth(e.getMessage());
                });
    }

    public void registration(String email, String password) {
        getViewState().onFinishRequest();
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put(EMAIL, email);
        loginMap.put(PASSWORD, password);

        AuthAPI authAPI = getAuthAPI();
        authAPI.registration(loginMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    successAuth();
                }, e -> {
                    unsuccessAuth(e.getMessage());
                });
    }

    private AuthAPI getAuthAPI() {
        if (authAPI == null) {
            authAPI = retrofit.create(AuthAPI.class);
        }
        return authAPI;
    }

    private void successAuth() {
        getViewState().onFinishRequest();
        getViewState().navigateToUsers();
    }

    private void unsuccessAuth(String message) {
        getViewState().onFinishRequest();
        getViewState().showErrorMessage(message);
    }
}

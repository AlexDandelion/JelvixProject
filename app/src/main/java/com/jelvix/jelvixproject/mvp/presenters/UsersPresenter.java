package com.jelvix.jelvixproject.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.jelvix.jelvixproject.JelvixApplication;
import com.jelvix.jelvixproject.mvp.views.UsersView;
import com.jelvix.jelvixproject.network.UsersAPI;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.jelvix.jelvixproject.network.SerialisedNames.DATA;

@InjectViewState
public class UsersPresenter extends MvpPresenter<UsersView> {

    private UsersAPI usersAPI;

    @Inject
    Retrofit retrofit;

    @Inject
    public UsersPresenter() {
        JelvixApplication.getAppComponent().inject(this);
    }

    public void getUsers() {
        getViewState().onStartRequest();
        UsersAPI usersAPI = getUsersAPI();
        usersAPI.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    getViewState().onFinishRequest();
                    getViewState().showUsers(o.getUsers());
                }, e -> {
                    unsuccess(e.getMessage());
                });
    }

    public void getUser(long id) {
        getViewState().onStartRequest();
        UsersAPI usersAPI = getUsersAPI();
        usersAPI.getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    getViewState().onFinishRequest();
                    getViewState().showUser(o.get(DATA));
                }, e -> {
                    unsuccess(e.getMessage());
                });
    }

    private UsersAPI getUsersAPI() {
        if (usersAPI == null) {
            usersAPI = retrofit.create(UsersAPI.class);
        }
        return usersAPI;
    }

    private void unsuccess(String message) {
        getViewState().onFinishRequest();
        getViewState().showErrorMessage(message);
    }
}

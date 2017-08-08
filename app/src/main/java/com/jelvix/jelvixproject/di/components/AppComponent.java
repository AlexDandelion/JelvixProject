package com.jelvix.jelvixproject.di.components;

import com.jelvix.jelvixproject.di.modules.AppModule;
import com.jelvix.jelvixproject.di.modules.NetworkModule;
import com.jelvix.jelvixproject.mvp.presenters.LoginPresenter;
import com.jelvix.jelvixproject.mvp.presenters.UsersPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(LoginPresenter loginPresenter);

    void inject(UsersPresenter usersPresenter);
}

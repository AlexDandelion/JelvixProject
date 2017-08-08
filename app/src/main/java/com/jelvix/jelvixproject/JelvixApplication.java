package com.jelvix.jelvixproject;

import android.app.Application;

import com.jelvix.jelvixproject.di.components.AppComponent;
import com.jelvix.jelvixproject.di.components.DaggerAppComponent;
import com.jelvix.jelvixproject.di.modules.AppModule;
import com.jelvix.jelvixproject.di.modules.NetworkModule;

public class JelvixApplication extends Application {

    private static final String BASE_URL = BuildConfig.SERVER_URL;
    private static final String BASE_API_PATH = BASE_URL + BuildConfig.API_PATH;

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent =
                DaggerAppComponent
                        .builder()
                        .appModule(new AppModule(this))
                        .networkModule(new NetworkModule(BASE_API_PATH))
                        .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}

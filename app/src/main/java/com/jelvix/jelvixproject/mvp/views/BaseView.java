package com.jelvix.jelvixproject.mvp.views;

import com.arellomobile.mvp.MvpView;

interface BaseView extends MvpView {

    void onStartRequest();

    void onFinishRequest();

    void showErrorMessage(String errorMessage);
}

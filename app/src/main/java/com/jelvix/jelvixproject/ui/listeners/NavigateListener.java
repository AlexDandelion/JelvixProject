package com.jelvix.jelvixproject.ui.listeners;

import com.jelvix.jelvixproject.mvp.models.User;

public interface NavigateListener {

    void navigateToSignUp();

    void navigateToUsers();

    void navigateToLogin();

    void navigateToProfile(User user);
}

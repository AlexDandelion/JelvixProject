package com.jelvix.jelvixproject.ui.listeners;

public interface AuthListener {

    void onLogin(String email, String password);

    void onSignUp(String email, String password, String repeatPassword);
}

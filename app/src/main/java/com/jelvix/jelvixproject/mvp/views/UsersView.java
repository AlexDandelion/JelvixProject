package com.jelvix.jelvixproject.mvp.views;

import com.jelvix.jelvixproject.mvp.models.User;

import java.util.List;

public interface UsersView extends BaseView {

    void showUsers(List<User> users);

    void showUser(User user);
}

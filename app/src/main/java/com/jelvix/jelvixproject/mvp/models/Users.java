package com.jelvix.jelvixproject.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.jelvix.jelvixproject.network.SerialisedNames.DATA;

public class Users {

    @SerializedName(DATA)
    @Expose
    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}

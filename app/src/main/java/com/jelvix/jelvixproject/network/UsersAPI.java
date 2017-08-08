package com.jelvix.jelvixproject.network;

import com.jelvix.jelvixproject.mvp.models.User;
import com.jelvix.jelvixproject.mvp.models.Users;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.jelvix.jelvixproject.network.SerialisedNames.ID;
import static com.jelvix.jelvixproject.network.Urls.USER;
import static com.jelvix.jelvixproject.network.Urls.USERS;

public interface UsersAPI {

    @GET(USERS)
    Observable<Users> getUsers();

    @GET(USER)
    Observable<Map<Object, User>> getUserById(@Path(ID) long id);
}

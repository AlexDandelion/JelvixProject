package com.jelvix.jelvixproject.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.jelvix.jelvixproject.network.Urls.LOGIN;
import static com.jelvix.jelvixproject.network.Urls.REGISTER;

public interface AuthAPI {

    @POST(LOGIN)
    @FormUrlEncoded
    Observable<ResponseBody> login(@FieldMap Map<String, String> loginParams);

    @POST(REGISTER)
    @FormUrlEncoded
    Observable<ResponseBody> registration(@FieldMap Map<String, String> registrationParams);
}

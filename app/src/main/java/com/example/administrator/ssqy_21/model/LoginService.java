package com.example.administrator.ssqy_21.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hongjinde on 2017/9/27.
 */

public interface LoginService {
    @GET("login")
    public Call<LoginEntity> doLoginNet(@Query("usercount") String username, @Query("userpwd") String userpwd);

}

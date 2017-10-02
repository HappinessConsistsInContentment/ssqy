package com.example.administrator.ssqy_21.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hongjinde on 2017/9/26.
 */

public class LoginDao implements ILoginDao {

    @Override
    public void saveUserAndPwd(String userAcount, String userPasswd) {

    }

    @Override
    public String getUser(String Acount) {
        return null;
    }

    @Override
    public String getPasswd(String Passwd) {
        return null;
    }

    @Override
    public void loginNet(String userAcount, String userPasswd, retrofit2.Callback<LoginEntity> callback) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://106.3.41.199:8066/jeesite/app/zy/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);
        Call<LoginEntity> call = loginService.doLoginNet(userAcount, userPasswd);
        call.enqueue(callback);
    }


}

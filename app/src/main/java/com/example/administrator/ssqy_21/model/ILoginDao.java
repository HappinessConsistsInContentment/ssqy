package com.example.administrator.ssqy_21.model;

/**
 * Created by hongjinde on 2017/9/26.
 */

public interface ILoginDao {


    public void saveUserAndPwd(String userAcount, String userPasswd);

    public String getUser(String Acount);

    public String getPasswd(String Passwd);

    public void loginNet(String userAcount, String userPasswd, retrofit2.Callback<LoginEntity> callback);

}

package com.example.administrator.ssqy_21.view;

/**
 * Created by hongjinde on 2017/9/26.
 */

public interface ILoginView {
    public void setUserAcount();
    public void setUserPasswd();

    public String getUserAcount();
    public String getUserPasswd();

    public void showLoading();
    public void hideLoading();

    public void onSuccess();
    public void onFail();

    public void changePasswdVisible();
    public void doLogin();

    public void setLoginEnable(boolean enable);
}

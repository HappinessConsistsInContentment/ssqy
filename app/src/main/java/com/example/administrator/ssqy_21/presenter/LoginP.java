package com.example.administrator.ssqy_21.presenter;

import android.widget.Toast;

import com.example.administrator.ssqy_21.model.ILoginDao;
import com.example.administrator.ssqy_21.model.LoginDao;
import com.example.administrator.ssqy_21.model.LoginEntity;
import com.example.administrator.ssqy_21.view.ILoginView;
import com.example.administrator.ssqy_21.common.MyApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by hongjinde on 2017/9/26.
 */

public class LoginP implements IloginP {
    private ILoginDao mILoginDao;
    private ILoginView mILoginView;

    public LoginP(ILoginView ILoginView) {
        mILoginView = ILoginView;
        mILoginDao=new LoginDao();
    }

    @Override
    public void doLogin() {
        String userAcount = mILoginView.getUserAcount();
        String userPasswd = mILoginView.getUserPasswd();

        mILoginDao.loginNet(userAcount, userPasswd, new Callback<LoginEntity>() {
            @Override
            public void onResponse(Call<LoginEntity> call, Response<LoginEntity> response) {
                LoginEntity loginEntity = response.body();
                if (loginEntity.getUid()==null||loginEntity.getUid().length()==0) {
                    //登录成功
                    Toast.makeText(MyApp.sContext, "用户名密码错误!", Toast.LENGTH_SHORT).show();
                }else {
                    //登录失败
                    Toast.makeText(MyApp.sContext, "登录成功", Toast.LENGTH_SHORT).show();
            }

            }

            @Override
            public void onFailure(Call<LoginEntity> call, Throwable t) {
                Toast.makeText(MyApp.sContext, "网络异常", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

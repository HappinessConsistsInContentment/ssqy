package com.example.administrator.ssqy_21.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.ssqy_21.R;
import com.example.administrator.ssqy_21.common.MyApp;
import com.example.administrator.ssqy_21.presenter.IloginP;
import com.example.administrator.ssqy_21.presenter.LoginP;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    private EditText mPhoneLogin;
    private EditText mPwdLogin;
    private Button mBtLogin;
    private ImageView mImgQq;
    private ImageView mImgvShowPwd;


    private ProgressDialog mProgressDialog;
    private IloginP mIloginP;

    private final int mTypeNormal= EditorInfo.TYPE_NUMBER_VARIATION_NORMAL;
    private final int mTypePwd=EditorInfo.TYPE_CLASS_TEXT|EditorInfo.TYPE_TEXT_VARIATION_PASSWORD;
    private UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPhoneLogin = (EditText) findViewById(R.id.edt_phone_login);
        mPwdLogin = (EditText) findViewById(R.id.edt_pwd_login);
        mBtLogin = (Button) findViewById(R.id.bt_login);

        mImgvShowPwd = (ImageView) findViewById(R.id.imgv_show_pwd);

        mImgQq = (ImageView) findViewById(R.id.img_qq);

        mIloginP=new LoginP(this);
        //猜的
        mShareAPI=UMShareAPI.get(this);

        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setTitle("登录中");
        mProgressDialog.setMessage("请稍后...");

//三方登录的回调接口
        final UMAuthListener authListener = new UMAuthListener() {
            /**
             * @desc 授权开始的回调
             * @param platform 平台名称
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                Toast.makeText(MyApp.sContext, "授权成功", Toast.LENGTH_LONG).show();

            }

            /**
             * @desc 授权失败的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                Toast.makeText(MyApp.sContext, "授权失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            }

            /**
             * @desc 授权取消的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             */
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {

                Toast.makeText(MyApp.sContext, "授权取消", Toast.LENGTH_LONG).show();
            }
        };

        mBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIloginP.doLogin();
            }
        });
        mImgvShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePasswdVisible();
            }
        });

        mImgQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ,authListener);
            }
        });
    }

    @Override
    public void setUserAcount() {

    }

    @Override
    public void setUserPasswd() {

    }

    @Override
    public String getUserAcount() {
        String userAcount = mPhoneLogin.getText().toString();
        if (TextUtils.isEmpty(userAcount)) {
            Toast.makeText(this, "用户账号不能为空", Toast.LENGTH_SHORT).show();
        }
        return userAcount;
    }

    @Override
    public String getUserPasswd() {
        String userPasswd = mPwdLogin.getText().toString();
        if (TextUtils.isEmpty(userPasswd)) {
            Toast.makeText(this, "用户密码不能为空", Toast.LENGTH_SHORT).show();
        }
        return userPasswd;
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.cancel();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changePasswdVisible() {
        if (mPwdLogin.getInputType()==mTypeNormal) {
            mPwdLogin.setInputType(mTypePwd);
        }else{
            mPwdLogin.setInputType(mTypeNormal);
        }

    }

    @Override
    public void doLogin() {
        mBtLogin.setEnabled(false);
        mIloginP.doLogin();
    }

    @Override
    public void setLoginEnable(boolean enable) {
        mBtLogin.setEnabled(enable);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);

    }
    public void QuanXian(){
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
            return;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "申请成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "申请失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}

package com.example.administrator.ssqy_21.common;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by hongjinde on 2017/9/27.
 */

public class MyApp extends Application {
    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext=this;

//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106453576", "mHSUXB3ahjOKxKSG");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");

        UMShareAPI.get(this);
    }
}

package com.cndll.shapetest.config;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;

import com.cndll.shapetest.tools.AppManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class AppContext extends Application implements UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static AppContext appContext;
    public IWXAPI msgApi;
    public static ContentValues cv = new ContentValues();
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        mContext = this.getApplicationContext();
        Fresco.initialize(this);
        // 获取系统默认的UncaughtException处理
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //友盟配置三方平台的appkey
        PlatformConfig.setWeixin("wx11de741afcebadb2","491fc397d1458b210fac32cc6e7053c2");
        PlatformConfig.setQQZone("1106231461", "drCdLNNgTmwFOqtE");
//        Config.DEBUG = true; //测试----友盟
        Config.isJumptoAppStore = true;
        UMShareAPI.get(this);
        msgApi = WXAPIFactory.createWXAPI(appContext, null);
        // 将该app注册到微信
        msgApi.registerApp("wx11de741afcebadb2");
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(t, e);
//            AppManager.getAppManager().AppExit(mContext);
//            SharedPreferenceUtil.clear();
//            SharedPreferenceUtil.insert("is", true);
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 这里可以自己提示异常
        return true;
    }

    public static AppContext getInstance() {
        if (appContext == null) {
            return new AppContext();
        }
        return appContext;
    }

    public static void setInstance(AppContext instance) {
        AppContext.appContext = instance;
    }

    public void logoutApp() {
        cv.clear();
        AppManager.getAppManager().AppExit(mContext);
    }


}

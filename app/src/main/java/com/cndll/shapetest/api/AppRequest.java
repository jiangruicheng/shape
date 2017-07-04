package com.cndll.shapetest.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by jiang_ruicheng on 16/10/29.
 */
public class AppRequest {
    private AppRequest() {
    }

    //  private static String ACCOUNTURL = "http://dc.idc.zhonxing.com/";

    public static String ACCOUNTURL = "http://zhongxiang.51edn.com/";

    /*private static AppRequest zkTecoRequest;

    public static AppRequest getInstance() {
        if (zkTecoRequest == null) {
            zkTecoRequest = new AppRequest();
        }
        return zkTecoRequest;
    }*/
    public static Api getAPI() {
        return new Retrofit.Builder().
                baseUrl(ACCOUNTURL).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                addConverterFactory(MyGsonConverterFactory.create()).
                build().
                create(Api.class);
    }
}

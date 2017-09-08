package com.example.packagerecyclerview;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient client =
                OkHttpUtils.getInstance().getOkHttpClient();
        client.setConnectTimeout(100000, TimeUnit.MILLISECONDS);
    }
}

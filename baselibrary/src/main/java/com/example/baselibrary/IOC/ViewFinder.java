package com.example.baselibrary.IOC;

import android.app.Activity;
import android.view.View;

/**
 * Created by Administrator on 2017/8/26 0026.
 * 辅助类 功能只是通过viewid返回一个view的实例
 */

public class ViewFinder {
    private Activity activity;
    private View view;


    public ViewFinder(Activity activity) {
        this.activity = activity;
    }

    public ViewFinder(View view) {
        this.view = view;
    }

    public View findviewById(int viewId) {
        return activity != null ? activity.findViewById(viewId) : view.findViewById(viewId);
    }
}

package com.example.packagerecyclerview.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

 class AlertController {
    private MyDialog dialog;
    private Window window;
    public AlertController(MyDialog myDialog, Window window) {
        this.dialog=myDialog;
        this.window=window;
    }

    public MyDialog getDialog() {
        return dialog;
    }

    public Window getWindow() {
        return window;
    }

    //内部类，保存用户输入的参数,
    public static class AlertParams{
        public Context context;
        public int themeResId;
        public boolean cancelable=true;
        public DialogInterface.OnCancelListener onCancelListener;
        public DialogInterface.OnDismissListener onDismissListener;
        public DialogInterface.OnKeyListener onKeyListener;
        public View view;
        public int layoutId;

        public AlertParams(Context context, int themeResId) {
            this.context=context;
            this.themeResId=themeResId;
        }

        public void apply(AlertController mAlert) {

        }
    }
}

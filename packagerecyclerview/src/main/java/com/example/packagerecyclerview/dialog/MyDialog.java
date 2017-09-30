package com.example.packagerecyclerview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.example.packagerecyclerview.R;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class MyDialog extends Dialog {
    private AlertController mAlert;

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert=new AlertController(this,getWindow());
    }



    //组装对象
    public static class Builder{
        private AlertController.AlertParams P;
        public Builder(Context context){
            this(context,R.style.dialog);
        }
        public Builder(Context context,int themeResId){
            P=new AlertController.AlertParams(context,themeResId);
        }
        public Builder setContentView(View view){
            P.view=view;
            P.layoutId=0;
            return this;
        }
        public Builder setContentView(int layoutId){
            P.view=null;
            P.layoutId=layoutId;
            return this;
        }
        public MyDialog create(){
            MyDialog dialog=new MyDialog(P.context,P.themeResId);

            P.apply(dialog.mAlert);
            dialog.setCancelable(P.cancelable);
            dialog.setOnCancelListener(P.onCancelListener);
           dialog.setOnDismissListener(P.onDismissListener);
            if(P.onKeyListener!=null) {
                dialog.setOnKeyListener(P.onKeyListener);
            }
            return dialog;
        }
        public MyDialog show(){
            MyDialog dialog=create();
            dialog.show();
            return dialog;
        }
    }
}

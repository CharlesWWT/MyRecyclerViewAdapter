package com.example.packagerecyclerview.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class ExceptionCrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "ExceptionCrashHandler";
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;
    //单例模式
    private static ExceptionCrashHandler mInstance;

    public static ExceptionCrashHandler getmInstance() {
        if (mInstance == null) {
            synchronized (ExceptionCrashHandler.class) {
                if (mInstance == null) {
                    mInstance = new ExceptionCrashHandler();
                }
            }
        }
        return mInstance;
    }

    private ExceptionCrashHandler() {
    }

    public void init(Context context) {
        this.mContext = context;
        Thread.currentThread().setUncaughtExceptionHandler(this);
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e(TAG, "有闪退信息");
        //获取信息 写入文件 （保存文件等下次启动再上传）


        //让系统默认处理
        mDefaultHandler.uncaughtException(t, e);
    }

    //获取手机信息 软件版本 型号到HashMap中
    private HashMap<String,String> obtainSimpleInfo(Context context){
        HashMap<String,String> map=new HashMap();
        PackageManager mPackageManager = context.getPackageManager();
        PackageInfo mPackageInfo=null;
        try {
            mPackageInfo  = mPackageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        map.put("versionName",mPackageInfo.versionName);
        map.put("versoinCode",mPackageInfo.versionCode+"");
        map.put("MODEL", Build.MODEL);
        map.put("SDK_INT", "" + Build.VERSION.SDK_INT);
        map.put("PRODUCT", "" + Build.PRODUCT);
        map.put("MOBLE_INFO", getMobileInfo());
        return map;
    }

    /**
     * 返回手机信息
     * @return
     */
  public static String getMobileInfo(){
      StringBuffer sb=new StringBuffer();
      Field[] fields = Build.class.getDeclaredFields();
      for(Field field:fields) {
        field.setAccessible(true);
          String name = field.getName();
          try {
              String value = field.get(null).toString();
              sb.append(name+"----------->"+value);
              sb.append("\n");
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      return sb.toString();
  }

    private String obtainExceptionInfo(Throwable throwable){
        StringWriter stringWriter=new StringWriter();
        PrintWriter printWriter=new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    /**
     * 删除目录下的所有文件和子目录下的所有文件夹
     * @param dir
     * @return
     */
    private boolean deleteDir(File dir){
        //如果dir是文件夹
        if(dir.isDirectory()) {
            //children为子目录的list
            String[] children = dir.list();
            for(int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if(!success) {
                    return false;
                }

            }
        }
        //目录为空
        return true;
    }
    private String getAssignTime(String dateFormatStr){
        DateFormat dataFormat = new SimpleDateFormat(dateFormatStr);
        long currentTime = System.currentTimeMillis();
        return dataFormat.format(currentTime);
    }
}

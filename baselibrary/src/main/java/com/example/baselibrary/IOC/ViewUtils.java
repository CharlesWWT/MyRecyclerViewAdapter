package com.example.baselibrary.IOC;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/8/26 0026.
 * 作用为注入view的属性和事件
 */

public class ViewUtils {
    //activity
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    //自定义view
    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    //object -->反射需要执行的类
    private static void inject(ViewFinder finder, Object object) {
        injectFiled(finder, object);
        injectEvent(finder, object);
    }

    //事件注入
    private static void injectEvent(ViewFinder finder, Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
//        @OnClick({R.id.tv1,R.id.image})
//        private void onClick()
        for (Method method:methods){
          OnClick onClick=method.getAnnotation(OnClick.class);
            if(onClick!=null) {
                int[] viewIds = onClick.value();
                if(viewIds.length>0) {
                    for (int viewId:viewIds){
                        View view = finder.findviewById(viewId);
                        if(view!=null) {
                            view.setOnClickListener(new DeclaredOnClickListener(method,object));
                        }
                    }
                }
            }
        }

    }

    private static void injectFiled(ViewFinder finder, Object object) {
        //1 获取类所有的属性
        //2获取viewByid的value值
        //3findviewbyid 找到view
        //4动态注入找到的view
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            ViewByID viewById = field.getAnnotation(ViewByID.class);

            if (viewById != null) {
                //  @ViewByID(R.id.tv1)//
                //private TextView tv1; view：tv1 field:TextView
                int viewId = viewById.value();
                View view = finder.findviewById(viewId);
                if (view != null) {
                    field.setAccessible(true);
                    try {
                        //TextView.set(activity,tv1)
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new RuntimeException("Invalid @ViewInject for" +
                            clazz.getSimpleName() + "." + field.getName()
                    );
                }
            }

        }
    }

    
    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object object;
        public DeclaredOnClickListener(Method method, Object object) {
            this.mMethod=method;
            this.object=object;

        }

        @Override
        public void onClick(View view) {
            mMethod.setAccessible(true);
            try {
                mMethod.invoke(object,view);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                mMethod.invoke(object,null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

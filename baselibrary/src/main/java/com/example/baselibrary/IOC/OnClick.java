package com.example.baselibrary.IOC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/8/26 0026.
 * 元注解的作用就是负责注解其他注解
 */

@Target(ElementType.METHOD)//只能放在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时注解
public @interface OnClick {
    int[] value();
}

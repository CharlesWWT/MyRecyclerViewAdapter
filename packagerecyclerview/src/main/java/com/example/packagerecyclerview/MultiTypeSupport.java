package com.example.packagerecyclerview;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public interface MultiTypeSupport<T> {
    //根据位置或者条目数据返回布局
    public int getLayoutId(T item,int position);
}

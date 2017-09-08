package com.example.packagerecyclerview;

import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class MyAdapter extends CommonRecyclerAdapter<AppBean.ResultsBean> {
    public MyAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);

    }


    @Override
    public void convert(ViewHolder holder, AppBean.ResultsBean item) {
        holder.setText(R.id.tv,item.getDesc()).setText(R.id.tv1,item.getWho());
        holder.setImageByGlide(R.id.image,item.getUrl());
    }


}

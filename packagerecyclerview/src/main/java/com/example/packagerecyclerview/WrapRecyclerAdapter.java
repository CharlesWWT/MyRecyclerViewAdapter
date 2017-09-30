package com.example.packagerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter mAdapter;

    private SparseArray<View> mHeaderViews;
    private SparseArray<View> mFooterViews;
    // 基本的头部类型开始位置  用于viewType
    private static int BASE_ITEM_TYPE_HEADER = 10000000;
    // 基本的底部类型开始位置  用于viewType
    private static int BASE_ITEM_TYPE_FOOTER = 20000000;

    public WrapRecyclerAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        mHeaderViews = new SparseArray<>();
        mFooterViews = new SparseArray<>();
    }

    //添加头部
    public void addHeaderView(View view) {
        //先从Array里面找
        int position = mHeaderViews.indexOfValue(view);//值所在的位置
        if (position < 0) {
            mHeaderViews.put(BASE_ITEM_TYPE_HEADER++, view);
        }
        notifyDataSetChanged();
    }

    //添加底部
    public void addFooterView(View view) {
        //先从Array里面找
        int position = mFooterViews.indexOfValue(view);

        if (position < 0) {
            mFooterViews.put(BASE_ITEM_TYPE_FOOTER++, view);
        }
        notifyDataSetChanged();
    }

    //根据position位置返回数字给onCreateViewHolder的viewType，来创建多类型的viewholder，
    // 先调这个方法返回viewtype
    @Override
    public int getItemViewType(int position) {

        //position从0开始,如果有头布局或尾布局position会增加
        int numHeaders = mHeaderViews.size();
        //位置大于头部数量，返回头部的viewtype
        if (position < numHeaders) {
            //返回的是position对应的key
            return mHeaderViews.keyAt(position);
        }
        //有尾部
        if (position >= mAdapter.getItemCount() + mHeaderViews.size()) {
            int footposition = position - mHeaderViews.size() - mAdapter.getItemCount();
            //返回的是position对应的key
            return mFooterViews.keyAt(footposition);
        }
        //什么都没有。position为原position,
        return mAdapter.getItemViewType(position);
    }
//    private boolean isFooterPosition(int position) {
//
//        return position >= mAdapter.getItemCount() + mHeaderViews.size();
//    }
//
//    //根据position 判断是不是头部
//    private boolean isHeaderPosition(int position) {
//        return mHeaderViews.size() > position;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断viewType的来源
        if (isHeadViewType(viewType)) {
            View headerView = mHeaderViews.get(viewType);
            return createHeaderFooterViewHolder(headerView);
        }

        if (isFooterViewType(viewType)) {
            View footView = mFooterViews.get(viewType);
            return createHeaderFooterViewHolder(footView);
        }

        return mAdapter.createViewHolder(parent, viewType);
    }

    //创建头部和尾部的viewholder
    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View headerView) {
        return new RecyclerView.ViewHolder(headerView) {
        };
    }

    //判断是不是头部类型
    private boolean isHeadViewType(int viewType) {
        //拿key对应的位置
        int i = mHeaderViews.indexOfKey(viewType);
        //负数说明不存在
        return i >= 0;
    }

    //判断是不是底部类型
    private boolean isFooterViewType(int viewType) {
        int i = mFooterViews.indexOfKey(viewType);
        return i >= 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //头部和尾部不绑定数据，直接返回
        if(isHeaderPosition(position)||isFooterPosition(position)){
            return;
        }
        //正常位置的position要重新计算
        int mPosition=0;
        mPosition=position-mHeaderViews.size();
        mAdapter.onBindViewHolder(holder,mPosition);
    }

    private boolean isFooterPosition(int position) {
        return position>=(mHeaderViews.size()+mAdapter.getItemCount());
    }

    private boolean isHeaderPosition(int position) {
        return position<mHeaderViews.size();
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mFooterViews.size() + mHeaderViews.size();
    }

    //移除头部
    public void removeHeaderView(View view) {
        int index = mHeaderViews.indexOfValue(view);
        if (index < 0) {
            return;
        }
        mHeaderViews.removeAt(index);
        notifyDataSetChanged();
    }

    //移除底部
    public void removeFootView(View view) {
        int index = mFooterViews.indexOfValue(view);
        if (index < 0) {
            return;
        }
        mHeaderViews.removeAt(index);
        notifyDataSetChanged();
    }
}

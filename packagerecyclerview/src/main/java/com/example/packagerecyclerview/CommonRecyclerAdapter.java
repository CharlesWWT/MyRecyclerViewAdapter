package com.example.packagerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context context;
    protected LayoutInflater mInflater;
    //数据
    protected List<T> mDatas;
    //布局
    protected int mLayoutId;
    protected MultiTypeSupport mMultiTypeSupport;

    public CommonRecyclerAdapter(Context context, List<T> datas, int layoutId) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    public CommonRecyclerAdapter(Context context, List<T> data, MultiTypeSupport<T> multiTypeSupport) {
        //重载方法  给属性赋值，item布局默认为-1（null）
        this(context, data, -1);
        this.mMultiTypeSupport = multiTypeSupport;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //多布局支持
        if (mMultiTypeSupport != null) {
            mLayoutId = viewType;
        }

        View itemView = mInflater.inflate(mLayoutId, parent, false);

        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(mOnItemClicklistener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClicklistener.onItemClick(position);
                }
            });
        }

        //绑定数据
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(ViewHolder holder, T item);


    public OnItemClicklistener mOnItemClicklistener;

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setOnItemClickListener(OnItemClicklistener itemClicklistener) {
        this.mOnItemClicklistener = itemClicklistener;
    }


}

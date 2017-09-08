package com.example.packagerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    //缓存view
    private SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    //通过id来获取view，单一职责，内部使用缓存减少findviewByid的次数 内部调用
    private  <T extends View> T getView(int viewId) {
        //缓存中找
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //设置TextView的文本
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    //设置View的Visibility
    public ViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    //设置ImageView的本地资源
    public ViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageview = getView(viewId);
        imageview.setImageResource(resourceId);
        return this;
    }

    //默认使用glide加载图片
    public ViewHolder setImageByGlide(int viewId, String url){
        ImageView imageview=getView(viewId);
//      Glide
//        .with(this)
//                .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
//                .into(imageView);
        Glide.with(imageview.getContext()).load(url).into(imageview);
        return this;
    }
    //设置ImageView的网络资源
    public ViewHolder setImageByUrl(int viewId, HolderImageLoader imageLoader) {
        ImageView imageview = getView(viewId);
        if (imageLoader == null) {
            throw new NullPointerException("imageLoader is null");
        }

        imageLoader.displayImage(imageview.getContext(), imageview,
                imageLoader.getImagePath());


        return this;
    }

    public static abstract class HolderImageLoader {
        private String mImagePath;

        public HolderImageLoader(String imagePath) {
            this.mImagePath = imagePath;
        }

        public String getImagePath() {
            return mImagePath;
        }

        public abstract void displayImage(Context context, ImageView imageview, String
                imagePath);
    }
}

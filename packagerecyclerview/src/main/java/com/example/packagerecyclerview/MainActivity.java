package com.example.packagerecyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.baselibrary.IOC.ViewByID;
import com.example.baselibrary.IOC.ViewUtils;
import com.example.packagerecyclerview.yahoo.YahooActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

public class MainActivity extends Activity {
    @ViewByID(R.id.recyclerview)
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i = 2 / 0;
        ViewUtils.inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OkHttpUtils
                .get()
                .url("http://gank.io/api/search/query/listview/category/%E7%A6%8F%E5%88%A9/count/10/page/1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(MainActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        AppBean appBean = new Gson().fromJson(response, AppBean.class);
                        List<AppBean.ResultsBean> results = appBean.getResults();
//                        setData(results);
                        MyAdapter myAdapter = new MyAdapter(MainActivity.this, results, R.layout.item_view);
                        WrapRecyclerAdapter adapter = new WrapRecyclerAdapter(myAdapter);
                        adapter.addHeaderView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_headview, null));
                        adapter.addFooterView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_headview, null));

                        myAdapter.setOnItemClickListener(new OnItemClicklistener() {
                            @Override
                            public void onItemClick(int positon) {
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, YahooActivity.class);
                                startActivity(intent);

                            }
                        });
                        recyclerView.setAdapter(adapter);

                    }
                });
    }

//    private void setData(List<AppBean.ResultsBean> results) {
//        MyAdapter myAdapter = new MyAdapter(MainActivity.this, results, R.layout.item_view);
//        recyclerView.setAdapter(myAdapter);
//    }
}

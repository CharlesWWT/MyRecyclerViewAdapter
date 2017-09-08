package com.example.packagerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.baselibrary.IOC.ViewByID;
import com.example.baselibrary.IOC.ViewUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @ViewByID(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        MyAdapter myAdapter = new MyAdapter(MainActivity.this, results, R.layout.item_view);
                        myAdapter.setOnItemClickListener(new OnItemClicklistener() {
                            @Override
                            public void onItemClick(int positon) {
                                Toast.makeText(MainActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
                            }
                        });
                        recyclerView.setAdapter(myAdapter);
                        
                    }
                });
    }
}

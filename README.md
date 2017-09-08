简化RecyclerViewAdapter的代码，设置Item的点击事件，支持多类型布局。

使用方式

```
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
```
新建一个Adapter继承CommonRecyclerAdapter
在convert方法中设置数据，支持链式调用，

设置图片内部使用Glide实现，也可以setImageResource设置图片本地资源，
setImageByUrl方法
可以设置自己的图片加载库，实现HolderImageLoader类即可



Adapter实例化和设置点击事件，R.layout.item_view为item的局部文件
```
 MyAdapter myAdapter = new MyAdapter(MainActivity.this, results, R.layout.item_view);
        myAdapter.setOnItemClickListener(new OnItemClicklistener() {
            @Override
            public void onItemClick(int positon) {
            Toast.makeText(MainActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
         }
       });
        recyclerView.setAdapter(myAdapter);
        
        
```
这是多类型布局的构造方法，demo过两天加

```

    public CommonRecyclerAdapter(Context context, List<T> data, MultiTypeSupport<T> multiTypeSupport) {
        //重载方法  给属性赋值，item布局默认为-1（null）
        this(context, data, -1);
        this.mMultiTypeSupport = multiTypeSupport;

    }
```
过两天加下载加载和添加头局部尾布局



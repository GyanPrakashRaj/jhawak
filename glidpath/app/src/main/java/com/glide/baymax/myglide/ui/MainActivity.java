package com.glide.baymax.myglide.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.glide.baymax.myglide.R;
import com.glide.baymax.myglide.adapter.GirlRecyclerAdapter;
import com.glide.baymax.myglide.model.Picture;
import com.glide.baymax.myglide.model.Results;
import com.glide.baymax.myglide.net.GankApi;
import com.glide.baymax.myglide.net.RequestManager;
import com.glide.baymax.myglide.net.RequestPicture;
import com.glide.baymax.myglide.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private GirlRecyclerAdapter girlRecyclerAdapter;
    private List<Results> mDatas;
    private int count,page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadResults(count, page);//刚开始加载10张图片
    }

    private void initView() {
        count = 10;
        page = 1;
        mDatas = new ArrayList<Results>();
        recyclerView = (RecyclerView)findViewById(R.id.list_pictures);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        girlRecyclerAdapter = new GirlRecyclerAdapter(MainActivity.this, mDatas);
        recyclerView.setAdapter(girlRecyclerAdapter);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_pictures);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                mDatas.clear();
                loadResults(count, 1);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int[] maxPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
                    int position = Math.max(maxPositions[0], maxPositions[1]);
                    //加载更多
                    if (position + 1 >= girlRecyclerAdapter.getItemCount()) {
                        loadResults(count, ++page);
                    }
                }
            }
        });
    }

    private void loadResults(int count,int page){
        String URL = GankApi.getGankUrl(count, page);
        RequestManager.addRequest(new RequestPicture<Picture>(URL, Picture.class, new Response.Listener<Picture>() {
            @Override
            public void onResponse(Picture picture) {
                if (picture.getError().equals("error")) {
                    Toast.makeText(MainActivity.this, "获取网络内容错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                mDatas.addAll(picture.getResults());
                girlRecyclerAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }), "Picture");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

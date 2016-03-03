package com.zzh.zoom;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
import com.zzh.zoom.adapter.RecyclerAdapter;
import com.zzh.zoom.widget.PullToZoomRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends BaseActivity {

    private PullToZoomRecyclerView pullToZoomRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerAdapter mAdapter;
    private List<String> list;
    private View headerView;
    private View zoomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initView();
        initData();
        initSetListener();
    }

    @Override
    protected void initView() {
        pullToZoomRecyclerView = (PullToZoomRecyclerView)findViewById(R.id.pullToZoomRecyclerView);
        mAdapter = new RecyclerAdapter(mContext);
        linearLayoutManager = new LinearLayoutManager(mContext);
        pullToZoomRecyclerView.setAdapterAndLayoutManager(mAdapter, linearLayoutManager);
        pullToZoomRecyclerView.setParallax(true);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        StaggeredGridLayoutManager.LayoutParams localObject = new StaggeredGridLayoutManager.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        pullToZoomRecyclerView.setHeaderLayoutParams(localObject);


        headerView = pullToZoomRecyclerView.getHeaderView();
        if (headerView != null) {
            ((TextView) headerView.findViewById(R.id.tv_user_name)).setText("小猫喵喵喵喵喵喵喵喵");
        }
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        for (int i=0;i<50;i++) {
            list.add("item --->  "+i);
        }

        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}

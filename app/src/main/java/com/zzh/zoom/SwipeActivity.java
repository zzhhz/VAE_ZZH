package com.zzh.zoom;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
import com.zzh.zoom.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<String> mList;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        init();
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.swipeRecyclerView);
        mManager = new LinearLayoutManager(mContext);
        mAdapter = new RecyclerAdapter(mContext);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        for (int i=0;i<50;i++) {
            mList.add("item --->  "+i);
        }
        mAdapter.addAll(mList);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
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

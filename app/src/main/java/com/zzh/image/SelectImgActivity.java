package com.zzh.image;

import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zzh.image.adapter.SelectImgAdapter;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

/**
 * 图片选择
 */
public class SelectImgActivity extends BaseActivity {
    private RecyclerView recyclerView_img;
    private GridLayoutManager layoutManager;
    private SelectImgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img);
        initView();
        initData();
        initSetListener();
    }

    @Override
    protected void initView() {
        recyclerView_img = (RecyclerView) findViewById(R.id.recyclerView_img);
        adapter = new SelectImgAdapter();
        layoutManager = new GridLayoutManager(mContext,3);
        recyclerView_img.setLayoutManager(layoutManager);
        recyclerView_img.setAdapter(adapter);
    }

    @Override
    protected void initData() {

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

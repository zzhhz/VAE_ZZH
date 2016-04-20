package com.zzh.image;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class CircleActivity extends BaseActivity {


    private ImageView imageView_circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        init();
    }

    @Override
    protected void initView() {
imageView_circle = (ImageView) findViewById(R.id.imageView_circle);

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

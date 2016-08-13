package com.zzh.image;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class CircleActivity extends BaseActivity {


    private ImageView imageView_circle;
    private ImageView imageView_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        init();
    }

    @Override
    protected void initView() {
        imageView_circle = (ImageView) findViewById(R.id.imageView_circle);
        imageView_b = (ImageView) findViewById(R.id.imageView2);

        Glide.with(mContext).load("http://e.hiphotos.baidu.com/image/pic/item/314e251f95cad1c8037ed8c97b3e6709c83d5112.jpg")//
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView_circle);
        Glide.with(mContext).load("http://e.hiphotos.baidu.com/image/pic/item/314e251f95cad1c8037ed8c97b3e6709c83d5112.jpg")//
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView_b);
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

package com.zzh.html5;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zzh.vae.R;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.utils.ZZHttpUtils;

public class HttpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        init();
    }

    @Override
    protected void initView() {
        new Thread(){
            @Override
            public void run() {
                String data = ZZHttpUtils.getUrlConnection(ZZHConstants.WEATHER_XML);
                Message msg = Message.obtain();
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {
        loge((String) msg.obj);
    }

    @Override
    public void onClick(View v) {

    }
}

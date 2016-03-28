package com.zzh.html5;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zzh.vae.R;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.utils.NetUtils;
import com.zzh.vae.utils.ZZHttpUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        init();
    }

    @Override
    protected void initView() {
        /*new Thread(){
            @Override
            public void run() {
                String data = ZZHttpUtils.getUrlConnection(ZZHConstants.WEATHER_XML);
                Message msg = Message.obtain();
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        }.start();*/
    }

    @Override
    protected void initData() {
        loge("----start network -----");
        /*ZZHttpUtils.getUrlConnectInBg("http://192.168.2.22:8080/Test01/TestAndroidServlet", new ZZHttpUtils.CallBack<String>() {
            @Override
            public void success(String s) {
                loge(s);
            }

            @Override
            public void fail(String err) {

            }
        });*/


        Map<String, String> map = new HashMap<>();
        map.put("name","123456");
        map.put("password", "123456");
        NetUtils.getInstance().postUrlConnectInBg("http://192.168.2.22:8080/Test01/TestAndroidServlet", map, new ZZHttpUtils.CallBack<String>() {
            @Override
            public void success(String s) {

            }

            @Override
            public void fail(String err) {

            }
        });

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

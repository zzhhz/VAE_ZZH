package com.zzh.retrofit;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zzh.vae.R;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.base.BaseActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends BaseActivity {
    private TextView sTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        init();
    }

    @Override
    protected void initView() {
        sTextView = (TextView) findViewById(R.id.testView);
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(ZZHConstants.URL_BASE_TEST_JSON).build();

        Call call = client.newCall(request);
        //回调的方法,是在子线程中运行的
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("thread--onFailure----",""+Thread.currentThread().getName());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("thread--success----",""+Thread.currentThread().getName());
                sTextView.setText(""+response.body().string());
            }
        });
        Log.e("thread","main-----"+Thread.currentThread().getName());
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

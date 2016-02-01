package com.zzh.vae.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

/**
 * Created by zzh on 2016/1/29.
 */
public class SurfaceActivity extends BaseActivity{

    private SurfaceView surfaceView;
    private Button add;
    private Button del;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        initView();
        initData();
        initSetListener();
    }

    @Override
    protected void handlerMessage(Message msg) {
    }

    @Override
    protected void initView() {
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        add = (Button) findViewById(R.id.add);
        del = (Button) findViewById(R.id.del);
        stop = (Button) findViewById(R.id.stop);
    }

    @Override
    protected void initData() {
        surfaceView.getHolder().setFixedSize(200, 200);
    }

    @Override
    protected void initSetListener() {
        add.setOnClickListener(this);
        del.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    private final class SurceCallback implements SurfaceHolder.Callback{

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}

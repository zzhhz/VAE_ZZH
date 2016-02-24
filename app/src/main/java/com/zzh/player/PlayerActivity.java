package com.zzh.player;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.VideoView;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.utils.SDCardUtils;

import java.io.File;

/**
 * 视频播放练习
 */
public class PlayerActivity extends BaseActivity {

    private VideoView videoView;
    private String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        initData();
        initSetListener();
    }

    @Override
    protected void initView() {
        videoView = (VideoView) findViewById(R.id.videoView);
    }

    @Override
    protected void initData() {
        path = SDCardUtils.getExternalStorageDownloadPath()+ File.separator+"welcome_video.mp4";
        Uri uri = Uri.parse(path);
        //videoView.setMediaController(new MediaController(this));//控制播放按钮有前进，暂停，后退。
        videoView.setVideoURI(uri);
    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        if (v == null){
            return;
        }
        switch (v.getId())
        {
            case R.id.btn_start: //视频开始播放
                videoView.start();
                break;
            case R.id.btn_stop: //视频停止播放
                videoView.pause();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

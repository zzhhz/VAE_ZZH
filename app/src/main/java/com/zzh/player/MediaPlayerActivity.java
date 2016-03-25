package com.zzh.player;

import android.os.Message;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zzh.vae.R;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.base.BaseActivity;

public class MediaPlayerActivity extends BaseActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        init();
    }

    @Override
    protected void initView() {
        videoView = (VideoView)findViewById(R.id.videoView_net);
    }

    @Override
    protected void initData() {
        videoView.setVideoPath(ZZHConstants.URL_MEDIA_PLAYER);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
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

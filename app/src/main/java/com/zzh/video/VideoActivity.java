package com.zzh.video;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.zzh.vae.R;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.utils.DensityUtils;
import com.zzh.video.view.UniversalMediaController;
import com.zzh.video.view.UniversalVideoView;

public class VideoActivity extends BaseActivity implements UniversalVideoView.VideoViewCallback{
    private FrameLayout video_layout;
    private UniversalVideoView mVideoView;
    private UniversalMediaController mMediaController;
    private boolean isFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    @Override
    protected void initView() {

        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);

        video_layout = (FrameLayout) findViewById(R.id.video_layout);
        caculateLayout(video_layout);
        mVideoView.setVideoPath(ZZHConstants.URL_VIDEO_MP4_YOU_KU);

        //控制按钮,跟视频播放关联在一起
        mVideoView.setMediaController(mMediaController);
        mMediaController.setTitle("网上视频播放");
        mVideoView.requestFocus();
        //监听事件
        mVideoView.setVideoViewCallback(this);
    }

    //设置布局宽高
    private void caculateLayout(FrameLayout layout) {
        int width = DensityUtils.getDisplayWidth(this);
        int height = DensityUtils.getDisplayHeight(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height / 3);
        layout.setLayoutParams(params);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.startOrPause).setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onScaleChange(boolean isFullscreen) {

        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = video_layout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            video_layout.setLayoutParams(layoutParams);
            //mBottomLayout.setVisibility(View.GONE);

        } else {
            ViewGroup.LayoutParams layoutParams = video_layout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            //layoutParams.height = this.cachedHeight;
            video_layout.setLayoutParams(layoutParams);
            //mBottomLayout.setVisibility(View.VISIBLE);
        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {

        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }
}

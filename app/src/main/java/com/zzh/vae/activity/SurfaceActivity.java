package com.zzh.vae.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.widget.DragVideoView;

/**
 * Created by zzh on 2016/1/29.
 * 类似youtube上播放视频,视频可以拖动,放在屏幕的右下角
 */
public class SurfaceActivity extends BaseActivity implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback, DragVideoView.Callback {

    private SurfaceView surfaceView;
    private DragVideoView youtube_effect;
    private Button testButton;
    private MediaPlayer mediaPlayer;
    SurfaceHolder holder;
    ListView listView;

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
        surfaceView = (SurfaceView) findViewById(R.id.player);
        youtube_effect = (DragVideoView) findViewById(R.id.youtube_effect);
        testButton = (Button) findViewById(R.id.test);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = MediaPlayer.create(mContext, R.raw.welcome_video);
        listView = (ListView) findViewById(R.id.list_view);

    }

    @Override
    protected void initData() {
        listView.setAdapter(ArrayAdapter.createFromResource(this,R.array.test_list,android.R.layout.simple_list_item_1));
    }

    @Override
    protected void initSetListener() {
        testButton.setOnClickListener(this);
        youtube_effect.setCallback(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.test:
                playVideo();
                testButton.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.setLooping(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        finish();
    }

    @Override
    public void onDisappear(int direct) {
        mediaPlayer.pause();
        testButton.setVisibility(View.VISIBLE);
        showMessage("destroy-direction-"+(direct == DragVideoView.SLIDE_TO_LEFT ? "left" : "right"));
    }

    private void playVideo(){
        youtube_effect.show();

        if(mediaPlayer.isPlaying())
            return;

        try {
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}

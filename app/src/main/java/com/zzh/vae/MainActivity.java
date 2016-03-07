package com.zzh.vae;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zzh.html5.activity.Html5Activity;
import com.zzh.image.SelectImgActivity;
import com.zzh.player.PlayerActivity;
import com.zzh.qrc.QRCActivity;
import com.zzh.vae.activity.SlidingActivity;
import com.zzh.vae.activity.SurfaceActivity;
import com.zzh.vae.base.BaseActivity;
import com.zzh.zoom.PullToZoomScrollViewActivity;
import com.zzh.zoom.RecyclerActivity;
import com.zzh.zoom.widget.PullToZoomScrollView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initSetListener();
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SurfaceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
                Intent intent = new Intent(mContext, SlidingActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(mContext, Html5Activity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(mContext, PlayerActivity.class);
                startActivity(intent2);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(mContext, SelectImgActivity.class);
                startActivity(intent3);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button5: //生成二维码
                Intent intent4 = new Intent(mContext, QRCActivity.class);
                startActivity(intent4);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button6: //测试PullToZoomRecyclerView
                Intent intent6 = new Intent(mContext, RecyclerActivity.class);
                startActivity(intent6);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button7: //测试PullToZoomRecyclerView
                Intent intent7 = new Intent(mContext, PullToZoomScrollViewActivity.class);
                startActivity(intent7);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button8: //测试选择图片
                Intent intent8 = new Intent(mContext, SelectImgActivity.class);
                startActivity(intent8);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;

        }
    }
}

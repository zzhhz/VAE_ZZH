package com.zzh.vae;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zzh.blur.BlurActivity;
import com.zzh.facedetector.PermissionActivity;
import com.zzh.html5.HttpActivity;
import com.zzh.html5.activity.Html5Activity;
import com.zzh.image.SelectImgActivity;
import com.zzh.image.StagActivity;
import com.zzh.player.MediaPlayerActivity;
import com.zzh.player.PlayerActivity;
import com.zzh.qrc.QRCActivity;
import com.zzh.qrc.ScanQRActivity;
import com.zzh.retrofit.RetrofitActivity;
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
        init();
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
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button13).setOnClickListener(this);
        findViewById(R.id.button15).setOnClickListener(this);
        findViewById(R.id.button16).setOnClickListener(this);
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
            case R.id.button9:
                Intent intent9 = new Intent(mContext, PermissionActivity.class);
                startActivity(intent9);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button10:
                Intent intent10 = new Intent(mContext, ScanQRActivity.class);
                startActivity(intent10);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button11:
                Intent intent11 = new Intent(mContext, HttpActivity.class);
                startActivity(intent11);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button12:
                Intent intent12 = new Intent(mContext, StagActivity.class);
                startActivity(intent12);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button13:
                Intent intent13 = new Intent(mContext, MediaPlayerActivity.class);
                startActivity(intent13);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            /*case R.id.button14:
                Intent intent14 = new Intent(mContext, RetrofitActivity.class);
                startActivity(intent14);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;*/
            case R.id.button15:
                Intent intent15 = new Intent(mContext, RetrofitActivity.class);
                startActivity(intent15);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.button16:
                Intent intent16 = new Intent(mContext, BlurActivity.class);
                startActivity(intent16);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            default:
                showMessage("没有做任何处理");
                break;
        }
    }
}

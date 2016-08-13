package com.zzh.dialog;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
/**
 * Created by ZZH on 16/7/21
 *
 * @Date: 16/7/21 15:16
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 测试封装的dialog, dialog fragment
 */
public class DialogUtilsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_utils);
        init();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.show_dialog).setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        DialogzFragment dialogzFragment = DialogzFragment.newInstance("","");
        dialogzFragment.show(getSupportFragmentManager(),"");

    }
}

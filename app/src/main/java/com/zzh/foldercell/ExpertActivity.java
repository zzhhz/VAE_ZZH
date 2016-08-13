package com.zzh.foldercell;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class ExpertActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        init();
    }

    @Override
    protected void initView() {
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.expert_container, new ExpertV2Fragment()).commitAllowingStateLoss();
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

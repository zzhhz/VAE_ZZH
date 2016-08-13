package com.zzh.fadingbar;

import android.os.Bundle;
import android.os.Message;
import android.view.ContextMenu;
import android.view.View;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class FadingActionBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background)
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.activity_fading_action_bar);
        setContentView(helper.createView(this));
        init();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void initView() {

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

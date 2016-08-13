package com.zzh.foldercell;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class FolderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        init();
    }

    @Override
    protected void initView() {

//        FoldableListLayout foldableListLayout = Views.find(this, R.id.foldable_list);
//        foldableListLayout.setAdapter(new PaintingsAdapter(this));
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

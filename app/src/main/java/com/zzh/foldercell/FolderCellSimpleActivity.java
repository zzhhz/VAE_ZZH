package com.zzh.foldercell;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.ramotion.foldingcell.FoldingCell;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class FolderCellSimpleActivity extends BaseActivity {

    private FoldingCell mFoldingCell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_cell_simple);
        init();
    }

    @Override
    protected void initView() {
        mFoldingCell = (FoldingCell) findViewById(R.id.folding_cell);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {
        mFoldingCell.setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.folding_cell:
                mFoldingCell.toggle(false);
                break;
        }
    }
}

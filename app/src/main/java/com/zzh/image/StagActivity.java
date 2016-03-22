package com.zzh.image;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zzh.image.adapter.StagAdapter;
import com.zzh.image.bean.Img;
import com.zzh.image.bean.Picture;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StagActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private StagAdapter mAdapter;
    private StaggeredGridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stag);
        init();
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mAdapter = new StagAdapter();
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            showMessage("没有SD卡");
            return;
        }
        new Thread(){
            @Override
            public void run() {

                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = mContext.getContentResolver();
                Cursor cursor = cr.query(mImgUri, null, MediaStore.Images.Media.MIME_TYPE + "= ? or  "+ MediaStore.Images.Media.MIME_TYPE + "= ? ",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
                List<Picture> pictures = new ArrayList<Picture>();
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    Picture picture = new Picture();
                    picture.setDir(path);
                    pictures.add(picture);
                }

                cursor.close();
                //扫描完成
                Message msg = Message.obtain();
                msg.obj = pictures;
                mHandler.sendMessage(msg);

            }
        }.start();
    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

        mAdapter.addAll((List<Picture>) msg.obj);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}

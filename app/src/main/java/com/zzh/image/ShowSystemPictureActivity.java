package com.zzh.image;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzh.image.adapter.ShowImageAdapter;
import com.zzh.image.bean.Picture;
import com.zzh.image.dialog.ListPopupWindow;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShowSystemPictureActivity extends BaseActivity {
    private TextView dirName;
    private TextView dirCount;
    private RelativeLayout relativeLayout;
    private GridView gridView;
    private ShowImageAdapter adapter ;
    private List<String> mImgs;
    private List<Picture> mFolderBeans;
    private File mCurrentDir;
    private int mMaxCount;
    private ListPopupWindow mPopupWindow;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_system_picture);
        initView();
        initData();
        initSetListener();
    }

    @Override
    protected void initView() {
        mImgs = new ArrayList<>();
        dirCount = (TextView)findViewById(R.id.dirCount);
        dirName = (TextView)findViewById(R.id.dirName);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        gridView = (GridView)findViewById(R.id.id_gridView);

    }

    @Override
    protected void initData() {
/***
 * 扫描图片
 *
 */
        mFolderBeans = new ArrayList<>();
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            showMessage("没有SD卡");
            return;
        }
        dialog = ProgressDialog.show(mContext, null, "正在加载...");
        new Thread(){
            @Override
            public void run() {

                Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver cr = mContext.getContentResolver();
                Cursor cursor = cr.query(mImgUri, null, MediaStore.Images.Media.MIME_TYPE + "= ? or  "+ MediaStore.Images.Media.MIME_TYPE + "= ? ",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                Set<String> mDirPaths = new HashSet<>();

                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }

                    String dirPath = parentFile.getAbsolutePath();
                    Picture picture = null;


                    if (mDirPaths.contains(dirPath)) {
                       continue;
                    } else {
                        mDirPaths.add(dirPath);
                        picture = new Picture();
                        picture.setDir(dirPath);
                        picture.setFirstImagePath(path);
                    }

                    if (parentFile.list() ==  null) {
                        continue;
                    }

                    mImgs.clear();
                    int picSize = parentFile.list(filter).length;


                    picture.setCount(picSize);

                    mFolderBeans.add(picture);

                    if (picSize > mMaxCount) {
                        mMaxCount = picSize;
                        mCurrentDir = parentFile;
                    }


                }

                cursor.close();
                //扫描完成
                mDirPaths = null;

                mHandler.sendEmptyMessage(0x111);

            }
        }.start();
    }

    @Override
    protected void initSetListener() {
        relativeLayout.setOnClickListener(this);


    }

    @Override
    protected void handlerMessage(Message msg) {
        data2View();

        initDirPopWindow();
    }

    private void initDirPopWindow() {
        mPopupWindow = new ListPopupWindow(mContext, mFolderBeans);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });

        mPopupWindow.setOnDirListener(new ListPopupWindow.OnDirSelectedListener() {
            @Override
            public void onSelected(Picture picture) {
                mCurrentDir = new File(picture.getDir());
                loge(picture.getDir());
                mImgs  = Arrays.asList(mCurrentDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if (filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".jpg")) {
                            return true;
                        }
                        return false;
                    }
                }));
                adapter = new ShowImageAdapter(mContext, mImgs, mCurrentDir.getAbsolutePath());
                gridView.setAdapter(adapter);
            }
        });
    }

    /**
     *
     */
    private void lightOn() {
        WindowManager.LayoutParams wm = getWindow().getAttributes();
        wm.alpha = 1.0f;
        getWindow().setAttributes(wm);
    }

    private void data2View() {
        if (mCurrentDir == null) {
            showMessage("未扫描到图片");
            return;
        }
        mImgs = Arrays.asList(mCurrentDir.list(filter));
        adapter = new ShowImageAdapter(mContext, mImgs, mCurrentDir.getAbsolutePath());
        gridView.setAdapter(adapter);
        dirCount.setText(String.valueOf(mMaxCount));
        dirName.setText(mCurrentDir.getName());
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.relativeLayout:
                showPopWindow();
                break;
        }
    }

    private void showPopWindow() {
        mPopupWindow.showAsDropDown(relativeLayout, 0,0);
        lightOff();
    }

    private void lightOff() {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.alpha = 0.3f;
        getWindow().setAttributes(wl);
    }

    FilenameFilter filter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String filename) {
            if (filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".jpg")) {
                return true;
            }
            return false;
        }
    };
}

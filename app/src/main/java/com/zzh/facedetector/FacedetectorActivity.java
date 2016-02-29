package com.zzh.facedetector;

import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

/**
 *
 */
public class FacedetectorActivity extends BaseActivity {
    private String codeStr = "赵中恒";
    private ImageView imageCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facedetector);
    }

    @Override
    protected void initView() {
        imageCode = (ImageView) findViewById(R.id.imageCode);

        Bitmap bitmap = createBitmap(codeStr);


    }

    private Bitmap createBitmap(String codeStr) {
        return null;
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

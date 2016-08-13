package com.zzh.qrc;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码
 * 赵中恒
 * QQ:1299234582@qq.com
 * email:zzh_hz@126.com
 * 需要的依赖包：com.google.zxing:core:3.2.1
 * 点击图片，显示二维码翻译过来的文字信息
 */
public class QRCActivity extends BaseActivity {

    private static final int QR_WIDTH = 500;
    private static final int QR_HEIGHT = 500;
    private static String MSG = "http://zzhhz.blog.51cto.com/";
    private static String MSGINFO = "http://zzhhz.blog.51cto.com/";
    private TextView textView_msg;
    private ImageView imageView_qrc;
    private ImageView mImageView_c;
    private ImageView mImageView_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrc);
        initView();
        initData();
        initSetListener();
    }

    @Override
    protected void initView() {
        imageView_qrc = (ImageView) findViewById(R.id.imageView_qrc);
        textView_msg = (TextView) findViewById(R.id.textView_msg);
        mImageView_c = (ImageView) findViewById(R.id.imageView_c);
        mImageView_c = (ImageView) findViewById(R.id.imageView_b);
    }

    @Override
    protected void initData() {
        createQRCImage(MSGINFO);
    }

    @Override
    protected void initSetListener() {
        imageView_qrc.setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_qrc:
                readQRImageInfo();
                break;
        }
    }

    private void readQRImageInfo() {
        Map<DecodeHintType, String> map = new Hashtable<>();
        map.put(DecodeHintType.CHARACTER_SET, "UTF-8"); //二维码设置的参数字符集用UTF-8
        /**得到二维码的Bitmap对象*/
        Bitmap bitmap = ((BitmapDrawable)imageView_qrc.getDrawable()).getBitmap(); //得到二维码
        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, QR_WIDTH, 0,0, QR_WIDTH, QR_HEIGHT);
        RGBLuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), pixels);
        BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        try {
            Result result = reader.decode(bb, map);
            textView_msg.setText(result.getText());
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    private void createQRCImage(String url){ // 根据URL生成二维码
        try {
            Map<EncodeHintType, String> map = new Hashtable<>();
            map.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //二维码设置的参数字符集用UTF-8
            //把输入的文本转换成二维码
            BitMatrix encode = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, map);
            int[] px = new int[QR_HEIGHT * QR_WIDTH];
            for (int x = 0; x < QR_HEIGHT; x++) {
                for (int y = 0; y < QR_WIDTH; y++) {
                    if (encode.get(y, x)) {
                        px[x * QR_WIDTH + y] = 0xff000000;
                    } else {
                        px[x * QR_WIDTH + y] = 0xffffffff;
                    }
                }
            }
            /**创建图片**/
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888); //
            bitmap.setPixels(px, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            imageView_qrc.setImageBitmap(bitmap);
            mImageView_c.setImageBitmap(bitmap);
            mImageView_b.setImageBitmap(bitmap);
        } catch (WriterException we) {

            we.printStackTrace();
        }
    }
}

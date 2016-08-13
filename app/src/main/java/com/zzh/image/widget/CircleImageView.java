package com.zzh.image.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.zzh.vae.R;

/**
 * Created by ZZH on 16/4/18.
 */
public class CircleImageView extends ImageView {
    private static final String TAG = "CircleImageView";
    private Context mContext;
    private int mBorderThickness;
    private Bitmap mBitmap;
    private int mDefaultColor = 0xFFFFFF; //圆环默认的颜色:白色
    private int mBorderOutSideColor = 0; //外层颜色
    private int mBorderInSideColor = 0; //内层颜色
    private int mDefaultRadius = 0; //圆形图片的半径

    private int mDefaultWidth = 0; //要显示图片的宽度, 默认值
    private int mDefaultHeight = 0; //要显示图片的高度, 默认值

    private boolean isFirstCaculate = true;

    public CircleImageView(Context context) {
        super(context);
        mContext = context;
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setCommonAttrs(attrs);
    }

    private void setCommonAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mBorderThickness = ta.getDimensionPixelSize(R.styleable.CircleImageView_border_thickness, 0);
        mBorderInSideColor = ta.getColor(R.styleable.CircleImageView_border_inside_color, mDefaultColor);
        mBorderOutSideColor = ta.getColor(R.styleable.CircleImageView_border_outside_color, mDefaultColor);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setCommonAttrs(attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isFirstCaculate){
            isFirstCaculate = false;
            caculateCircle(canvas);
        }
        int radius = getCircleRadius(canvas);
        try {
            Bitmap local = ((BitmapDrawable) getDrawable()).getBitmap();
            Bitmap bitmap = local.copy(Bitmap.Config.ARGB_8888, true);
            Bitmap circle = getCroppedRoundBitmap(bitmap, radius);
            canvas.drawBitmap(circle, mBorderThickness, mBorderThickness, null);
        } catch (Exception ex){
            return;
        }

    }

    private int getCircleRadius(Canvas canvas) {
        int radius;
        if (mBorderInSideColor != mDefaultColor
                && mBorderOutSideColor != mDefaultColor) {// 定义画两个边框，分别为外圆边框和内圆边框
            mDefaultRadius = radius = (mDefaultWidth < mDefaultHeight ? mDefaultWidth
                    : mDefaultHeight) / 2 - 2 * mBorderThickness;
            // 画内圆
            drawCircleBorder(canvas, radius + mBorderThickness / 2,
                    mBorderInSideColor);
            // 画外圆
            drawCircleBorder(canvas, radius + mBorderThickness
                    + mBorderThickness / 2, mBorderOutSideColor);
        } else if (mBorderInSideColor != mDefaultColor
                && mBorderOutSideColor == mDefaultColor) {// 定义画一个边框
            mDefaultRadius = radius = (mDefaultWidth < mDefaultHeight ? mDefaultWidth
                    : mDefaultHeight) / 2 - mBorderThickness;
            drawCircleBorder(canvas, radius + mBorderThickness / 2,
                    mBorderInSideColor);
        } else if (mBorderInSideColor == mDefaultColor
                && mBorderOutSideColor != mDefaultColor) {// 定义画一个边框
            mDefaultRadius = radius = (mDefaultWidth < mDefaultHeight ? mDefaultWidth
                    : mDefaultHeight) / 2 - mBorderThickness;
            drawCircleBorder(canvas, radius + mBorderThickness / 2,
                    mBorderOutSideColor);
        } else {// 没有边框
            mDefaultRadius =  radius = (mDefaultWidth < mDefaultHeight ? mDefaultWidth
                    : mDefaultHeight) / 2;
        }
        return radius;
    }

    private void drawCircleBorder(Canvas canvas, int radius, int color) {
        Paint paint = new Paint();
        /* 去锯齿 */
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
        /* 设置paint的　style　为STROKE：空心 */
        paint.setStyle(Paint.Style.STROKE);
        /* 设置paint的外框宽度 */
        paint.setStrokeWidth(mBorderThickness);

        canvas.drawCircle(mDefaultRadius + mBorderThickness, mDefaultRadius + mBorderThickness, radius, paint);
    }

    /**
     * 将图片裁剪成圆形图片
     * @param bmp 要裁剪成圆形图片的bitmap
     * @param radius 圆形图片的半径
     * @return 返回圆形图片
     */
    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;

        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else {
            squareBitmap = bmp;
        }

        if (squareBitmap.getWidth() != diameter
                || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
                    diameter, true);

        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        // bitmap回收(recycle导致在布局文件XML看不到效果)
        // bmp.recycle();
        // squareBitmap.recycle();
        // scaledSrcBmp.recycle();
        bmp = null;
        squareBitmap = null;
        scaledSrcBmp = null;
        return output;
    }


    private void caculateCircle(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null){
            return;
        }

        if (getWidth() == 0 || getHeight() == 0){
            return;
        }
        this.measure(0,0);

        if (drawable.getClass() == NinePatchDrawable.class){
            return;
        }
        if (mDefaultWidth == 0){
            mDefaultWidth = getWidth();
        }
        if (mDefaultHeight == 0)
        {
            mDefaultHeight = getHeight();
        }

        int tmp = mDefaultHeight > mDefaultWidth ? mDefaultWidth: mDefaultHeight;

        if (tmp > 0){
            mDefaultRadius  = tmp / 2;
        }
        Log.d(TAG, "onDraw: mBorderThickness"+ mBorderThickness);
        Log.d(TAG, "onDraw: width"+mDefaultWidth);
        Log.d(TAG, "onDraw: height"+mDefaultHeight);
        Log.d(TAG, "onDraw: -----------------------");
    }

}

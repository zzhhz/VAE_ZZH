package com.zzh.image.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ZZH on 16/4/18.
 */
public class TestCircle extends ImageView {
    public TestCircle(Context context) {
        super(context);
    }

    public TestCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = ((BitmapDrawable)getDrawable()).getBitmap();
        Bitmap circle = cropRect2Circle(bitmap);
        canvas.drawBitmap(circle, 0, 0, null);
    }

    private Bitmap cropRect2Circle(Bitmap bitmap) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap circle = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(circle);
        Bitmap.createScaledBitmap(copy, 300, 300, true);
        canvas.drawCircle(0,0, 150, paint);
        Rect rect = new Rect(0,0, 300,300);
        canvas.drawBitmap(copy, rect, rect,paint);
        return circle;
    }
}

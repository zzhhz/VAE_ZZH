package com.zzh.image.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by ZZH on 16/4/18.
 */
public abstract class MaskedImage extends ImageView{
    public static final String TAG = "MaskedImage";
    private static Xfermode MASK_XFERMODE;
    private Bitmap mask;
    private Paint mPaint;

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }
    public MaskedImage(Context context) {
        super(context);
    }

    public MaskedImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskedImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable localDrawable = getDrawable();
        if (localDrawable == null){
            return;
        }

        try {
            if (this.mPaint == null){
                this.mPaint = new Paint();
                this.mPaint.setFilterBitmap(false);
            }
            float f1 = getWidth();
            float f2 = getHeight();
            int i = canvas.saveLayer(0.0F, 0.0F, f1, f2, null,31);
            int j = getWidth();
            int k = getHeight();
            localDrawable.setBounds(0,0, j, k);
            localDrawable.draw(canvas);
            if (this.mask ==  null || this.mask.isRecycled()){
                Bitmap localBitmap = createMask();
                this.mask = localBitmap;
            }
            canvas.drawBitmap(this.mask,0.0F, 0.0F, this.mPaint);
            canvas.restoreToCount(i);
        }catch (Exception ex)
        {
            Log.e(TAG, "onDraw: 抽象方法里面出现错误", ex);
        }
    }

    protected abstract Bitmap createMask();
}

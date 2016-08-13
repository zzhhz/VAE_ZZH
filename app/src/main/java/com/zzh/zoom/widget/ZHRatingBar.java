package com.zzh.zoom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RatingBar;

/**
 * Created by ZZH on 16/7/20.
 *
 * @Date: 16/7/20
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 自定义星星的颜色
 */
public class ZHRatingBar extends RatingBar{
    public ZHRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ZHRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZHRatingBar(Context context) {
        super(context);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}

package com.zzh.zoom.widget;

/*
 * Copyright (c)
 * @Date: 2016. /5/3
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: 赵中恒
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
public class SwipeListView extends ListView{
    public SwipeListView(Context context) {
        super(context);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);
    }
}

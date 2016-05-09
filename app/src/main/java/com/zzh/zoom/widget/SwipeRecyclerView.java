package com.zzh.zoom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ZZH on 16/4/20.
 *
 */
public class SwipeRecyclerView extends RecyclerView {

    /////左右滑动
    private static final int MOVE_RIGHT=10000;
    private static final int MOVE_LEFT=10000;
    private static boolean swipeMenuFlag = false;
    private float x = 0;
    private float y = 0;

    public SwipeRecyclerView(Context context) {
        super(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        x = e.getX();
        y = e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_MOVE:

//                smoothScrollToPosition();
                break;
        }

        return super.onTouchEvent(e);
    }




}

package com.zzh.zoom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.zzh.zoom.interfaces.IPullToZoom;

/**
 * Created by zzh on 2016/3/2.
 */
public abstract class PullZoomBase<T extends View> extends LinearLayout implements IPullToZoom {

    public PullZoomBase(Context context) {
        super(context);
    }

    public PullZoomBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullZoomBase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

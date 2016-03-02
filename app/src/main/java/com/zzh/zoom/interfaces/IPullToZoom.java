package com.zzh.zoom.interfaces;

import android.content.res.TypedArray;
import android.view.View;

/**
 * Created by zzh on 2016/3/2.
 * 定义公共接口
 */
public interface IPullToZoom<T extends View> {

    View getHeaderView();
    View getZoomView();

    T getPullRootView();

    boolean isPullToZoomEnabled();

    boolean isZooming();

    boolean isParallax();

    boolean isHideHeader();

    void handleStyledAttributes(TypedArray ta);

}

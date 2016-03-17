package com.zzh.player.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zzh on 2016/3/17.
 */
public class SurfacesView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder mHolder;
    /**绘制线程，更新SurfacesView**/
    private RenderThread mThead;
    public SurfacesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SurfacesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SurfacesView(Context context) {
        super(context);
    }

    /***SurfaceHolder.Callback***/
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }
    /***SurfaceHolder.Callback***/
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    /***SurfaceHolder.Callback***/
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     *
     */
    private class RenderThread extends Thread{
        @Override
        public void run() {
        }
    }

}

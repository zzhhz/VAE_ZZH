package com.zzh.zoom.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zzh.vae.R;
import com.zzh.zoom.interfaces.IPullToZoom;

/**
 * Created by zzh on 2016/3/2.
 */
public abstract class PullToZoomBase<T extends View> extends LinearLayout implements IPullToZoom<T> {


    private int mTouchSlop;
    protected int mScreenHeight;
    protected int mScreenWidth;
    protected T mRootView;
    protected View mZoomView; //头部View
    protected View mHeaderView;// 缩放拉伸View
    private boolean isParallax = true;
    private boolean isZoomEnabled = true;
    private boolean isZooming = false;
    private boolean isHideHeader = false;
    private boolean mIsBeingDragged = false;
    private float mLastMotionY;
    private float mLastMotionX;
    private float mInitialMotionY;
    private float mInitialMotionX;
    private static final float FRICTION = 2.0f;
    private OnPullZoomListener onPullZoomListener;

    public PullToZoomBase(Context context) {
        this(context, null);
    }

    public PullToZoomBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化方法
     **/
    private void init(Context context, AttributeSet attrs) {
        setGravity(Gravity.CENTER);
        ViewConfiguration config = ViewConfiguration.get(context);
        mTouchSlop = config.getScaledTouchSlop();
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        mScreenHeight = localDisplayMetrics.heightPixels;
        mScreenWidth = localDisplayMetrics.widthPixels;
        mRootView = createRootView(context, attrs);

        /**属性*/
        if (attrs != null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            /**初始化状态view**/
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullToZoomRecyclerView);
            int zoomViewResId = a.getResourceId(R.styleable.PullToZoomRecyclerView_zoomView, 0);
            if (zoomViewResId > 0) {
                mZoomView = inflater.inflate(zoomViewResId, null);
            }

            int headerViewResId = a.getResourceId(R.styleable.PullToZoomRecyclerView_headerView, 0);
            if (headerViewResId > 0) {
                mHeaderView = inflater.inflate(headerViewResId, null);
            }

            isParallax = a.getBoolean(R.styleable.PullToZoomRecyclerView_isHeaderParallax, true);

            handleStyledAttributes(a);
            a.recycle();
        }

        addView(mRootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**设置监听**/
    public void setOnPullZoomListener(OnPullZoomListener onPullZoomListener){
        this.onPullZoomListener = onPullZoomListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isPullToZoomEnabled() || isHideHeader()) {
            return false;
        }
        int action = ev.getAction();

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsBeingDragged = false;
            return false;
        }

        if (action != MotionEvent.ACTION_DOWN && mIsBeingDragged) {
            return true;
        }

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                if (isReadyForPullStart()) {
                    float y = ev.getY(), x = ev.getX();
                    float diff, oppositeDiff, absDiff;
                    diff = y - mLastMotionY;
                    oppositeDiff = x - mLastMotionX;
                    absDiff = Math.abs(diff);

                    if (absDiff > mTouchSlop && absDiff > Math.abs(oppositeDiff)) {
                        if (diff >= 1f && isReadyForPullStart()) {
                            mLastMotionX = x;
                            mLastMotionY = y;
                            mIsBeingDragged = true;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (isReadyForPullStart()) {
                    mLastMotionY = mInitialMotionY = ev.getY();
                    mLastMotionX = mInitialMotionX = ev.getX();
                    mIsBeingDragged = false;
                }
                break;
        }
        return mIsBeingDragged;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isPullToZoomEnabled() || isHideHeader()) {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mIsBeingDragged) {
                    mLastMotionX = event.getX();
                    mLastMotionY = event.getY();
                    pullEvent();
                    isZooming = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (isReadyForPullStart()) {
                    mLastMotionY = mInitialMotionY = event.getY();
                    mLastMotionX = mInitialMotionX = event.getX();
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mIsBeingDragged) {
                    mIsBeingDragged = false;
                    if (isZooming()) {
                        smoothScrollToTop();
                        if (onPullZoomListener != null) {
                            onPullZoomListener.onPullZoomEnd();
                        }
                        isZooming = false;
                        return true;
                    }
                    return true;
                }
                break;
        }
        return false;
    }



    private void pullEvent() {
        int newScrollValue;
        float initialMotionValue, lastMotionValue;
        initialMotionValue = mInitialMotionY;
        lastMotionValue = mLastMotionY;
        newScrollValue = Math.round(Math.min(initialMotionValue - lastMotionValue, 0) / FRICTION);

        pullHeaderToZoom(newScrollValue);
        if (onPullZoomListener != null) {
            onPullZoomListener.onPullZooming(newScrollValue);
        }
    }

    protected abstract void pullHeaderToZoom(int newScrollValue);
    protected abstract boolean isReadyForPullStart();
    protected abstract void smoothScrollToTop();
    protected abstract T createRootView(Context context, AttributeSet attrs);
    public abstract void setHeaderView(View headerView);
    public abstract void setZoomView(View zoomView);
    /***
     * IPullToZoom<T>
     **/
    @Override
    public View getHeaderView() {
        return mHeaderView;
    }

    /***
     * IPullToZoom<T>
     **/
    @Override
    public View getZoomView() {
        return mZoomView;
    }

    /***
     * IPullToZoom<T>
     **/
    @Override
    public T getPullRootView() {
        return mRootView;
    }

    /***
     * IPullToZoom<T>
     **/
    @Override
    public boolean isPullToZoomEnabled() {
        return isZoomEnabled;
    }

    /***
     * IPullToZoom<T>
     **/
    @Override
    public boolean isZooming() {
        return isZooming;
    }

    /***
     * IPullToZoom<T>
     **/
    @Override
    public boolean isParallax() {
        return isParallax;
    }

    /***
     * IPullToZoom<T>
     **/
    @Override
    public boolean isHideHeader() {
        return isHideHeader;
    }

    public void setZoomEnabled(boolean zoomEnabled) {
        isZoomEnabled = zoomEnabled;
    }

    public void setZooming(boolean zooming) {
        isZooming = zooming;
    }

    public void setParallax(boolean isParallax){
        this.isParallax = isParallax;
    }

    public void setHideHeader(boolean hideHeader) {
        isHideHeader = hideHeader;
    }

    //接口回调，监测滑动的距离，和滑动结束
    public interface OnPullZoomListener {
        void onPullZooming(int newScrollValue);
        void onPullZoomEnd();
    }
}

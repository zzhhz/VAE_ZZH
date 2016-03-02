package com.zzh.zoom.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.zzh.zoom.base.PullToZoomBase;

/**
 * Created by zzh on 2016/3/2.
 * 自定义的recyclerview，实现room效果
 */
public class PullToZoomRecyclerView extends PullToZoomBase<RecyclerView> implements AbsListView.OnScrollListener {
    private int mHeaderHeight;
    private FrameLayout mHeaderContainer;

    public PullToZoomRecyclerView(Context context) {
        this(context, null);
    }

    public PullToZoomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * mRootView是RecyclerView 的时候
         */
        mRootView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //有可拉伸的头布局，而且头布局没有隐藏，并且头布局是设置支持拉伸的
                if (mZoomView != null && !isHideHeader() && isPullToZoomEnabled()) {
                    float f = mHeaderHeight - mHeaderContainer.getBottom();
                    if (isParallax()) {
                        if ((f> 0.0f) && (f < mHeaderHeight)) {
                            int i = (int) (0.6D * f);
                            mHeaderContainer.scrollTo(0, -i);
                        } else if (mHeaderContainer.getScaleY() != 0) {
                            mHeaderContainer.scrollTo(0, 0);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public PullToZoomRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setHideHeader(boolean hideHeader) {
        if (hideHeader != isHideHeader()) {
            super.setHideHeader(hideHeader);
            if (hideHeader) {
                removeHeaderView();
            } else {
                updateHeaderView();
            }
        }
    }

    private void updateHeaderView() {
        //TODO 如果是显示头布局，则更新头布局
    }

    private void removeHeaderView() {
        //TODO 如果隐藏头布局，则删除头布局
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    protected void pullHeaderToZoom(int newScrollValue) {

    }

    @Override
    protected boolean isReadyForPullStart() {
        return false;
    }

    @Override
    protected void smoothScrollToTop() {

    }

    @Override
    protected RecyclerView createRootView(Context context, AttributeSet attrs) {
        return null;
    }
}

package com.zzh.zoom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.zzh.zoom.adapter.RecyclerViewHeaderAdapter;
import com.zzh.zoom.base.PullToZoomBase;

/**
 * Created by zzh on 2016/3/2.
 * 自定义的recyclerview，实现room效果
 */
public class PullToZoomRecyclerView extends PullToZoomBase<RecyclerView> implements AbsListView.OnScrollListener {
    private int mHeaderHeight;
    private FrameLayout mHeaderContainer;
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float paramAnonymousFloat) {
            float f = paramAnonymousFloat - 1.0F;
            return 1.0F + f * (f * (f * (f * f)));
        }
    };

    private ScalingRunnable mScalingRunnable;

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
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //有可拉伸的头布局，而且头布局没有隐藏，并且头布局是设置支持拉伸的
                if (mZoomView != null && !isHideHeader() && isPullToZoomEnabled()) {
                    float f = mHeaderHeight - mHeaderContainer.getBottom();
                    if (isParallax()) {
                        if ((f > 0.0F) && (f < mHeaderHeight)) {
                            int i = (int) (0.65D * f);
                            mHeaderContainer.scrollTo(0, -i);
                        } else if (mHeaderContainer.getScrollY() != 0) {
                            mHeaderContainer.scrollTo(0, 0);
                        }
                    }
                }
            }
        });
        mScalingRunnable = new ScalingRunnable();
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

    public void setAdapterAndLayoutManager(RecyclerView.Adapter adapter, RecyclerView.LayoutManager manager) {
        mRootView.setLayoutManager(manager);
        mRootView.setAdapter(adapter);
        updateHeaderView();
    }


    /**
     * 更新HeaderView  先移除-->再添加zoomView、HeaderView -->然后添加到listView的head
     */
    private void updateHeaderView() {
        // 如果是显示头布局，则更新头布局, 不考虑API9以下的版本
        if (mHeaderContainer != null) {
            if (mRootView != null && mRootView.getAdapter() != null) {
                RecyclerViewHeaderAdapter<RecyclerView.ViewHolder> mAdapter = (RecyclerViewHeaderAdapter<RecyclerView.ViewHolder>) mRootView.getAdapter();
                if (mAdapter != null) {
                    if (mAdapter.getHeader(0) != null) {
                        mAdapter.removeHeaderView(mAdapter.getHeader(0));
                    }

                    mHeaderContainer.removeAllViews();

                    if (mZoomView != null) {
                        mHeaderContainer.addView(mZoomView);
                    }
                    if (mHeaderView != null) {
                        mHeaderContainer.addView(mHeaderView);
                    }

                    mHeaderHeight = mHeaderContainer.getHeight();
                    RecyclerViewHeaderAdapter.ExtraItem mExtraItem = new RecyclerViewHeaderAdapter.ExtraItem(RecyclerViewHeaderAdapter.INT_TYPE_HEADER, new RecyclerView.ViewHolder(mHeaderContainer) {
                        @Override
                        public String toString() {
                            return super.toString();
                        }
                    });

                    mAdapter.addHeaderView(mExtraItem);

                }
            }
        }
    }

    private void removeHeaderView() {
        // 如果隐藏头布局，则删除头布局
        if (mHeaderContainer != null) {
            if (mRootView != null && mRootView.getAdapter() != null) {
                RecyclerViewHeaderAdapter<RecyclerView.ViewHolder> mAdapter = (RecyclerViewHeaderAdapter<RecyclerView.ViewHolder>) mRootView.getAdapter();
                if (mAdapter != null) {
                    if (mAdapter.getHeader(0) != null) {
                        mAdapter.removeHeaderView(mAdapter.getHeader(0));
                    }
                }
            }
        }
    }

    /**
     * 设置HeaderView高度
     *
     * @param width  宽
     * @param height 高
     */
    public void setHeaderViewSize(int width, int height) {
        if (mHeaderContainer != null) {
            Object localObject = mHeaderContainer.getLayoutParams();
            if (localObject == null) {
                localObject = new AbsListView.LayoutParams(width, height);
            }
            ((ViewGroup.LayoutParams) localObject).width = width;
            ((ViewGroup.LayoutParams) localObject).height = height;
            mHeaderContainer.setLayoutParams((ViewGroup.LayoutParams) localObject);
            mHeaderHeight = height;
        }
    }

    public void setHeaderLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (mHeaderContainer != null) {
            mHeaderContainer.setLayoutParams(layoutParams);
            mHeaderHeight = layoutParams.height;
        }
    }

    @Override
    public void handleStyledAttributes(TypedArray ta) {
        mHeaderContainer = new FrameLayout(getContext());
        if (mZoomView != null) {
            mHeaderContainer.addView(mZoomView);
        }
        if (mHeaderView != null) {
            mHeaderContainer.addView(mHeaderView);
        }
        RecyclerViewHeaderAdapter<RecyclerView.ViewHolder> adapter = (RecyclerViewHeaderAdapter<RecyclerView.ViewHolder>) mRootView.getAdapter();
        if (adapter != null) {
            RecyclerViewHeaderAdapter.ExtraItem mExtraItem = new RecyclerViewHeaderAdapter.ExtraItem(RecyclerViewHeaderAdapter.INT_TYPE_HEADER, new RecyclerView.ViewHolder(mHeaderContainer) {
                @Override
                public String toString() {
                    return super.toString();
                }
            });

            adapter.addHeaderView(mExtraItem);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mHeaderHeight == 0 && mHeaderContainer != null) {
            mHeaderHeight = mHeaderContainer.getHeight();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mZoomView != null && !isHideHeader() && isPullToZoomEnabled()) {
            float f = mHeaderHeight - mHeaderContainer.getBottom();
            if (isParallax()) {
                if ((f > 0.0F) && (f < mHeaderHeight)) {
                    int i = (int) (0.65D * f);
                    mHeaderContainer.scrollTo(0, -i);
                } else if (mHeaderContainer.getScrollY() != 0) {
                    mHeaderContainer.scrollTo(0, 0);
                }
            }
        }
    }

    /**
     * zoomView的逻辑动画
     */
    @Override
    protected void pullHeaderToZoom(int newScrollValue) {
        if (mScalingRunnable != null && !mScalingRunnable.isFinished()) {
            mScalingRunnable.aboutAnimation();
        }
        ViewGroup.LayoutParams localLayoutParams = mHeaderContainer.getLayoutParams();
        localLayoutParams.height = Math.abs(newScrollValue) + mHeaderHeight;
        mHeaderContainer.setLayoutParams(localLayoutParams);
    }

    @Override
    protected boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    @Override
    public void setHeaderView(View headerView) {
        if (headerView != null) {
            this.mHeaderView = headerView;
            updateHeaderView();
        }
    }

    @Override
    public void setZoomView(View zoomView) {
        if (zoomView != null) {
            this.mZoomView = zoomView;
            updateHeaderView();
        }
    }


    private boolean isFirstItemVisible() {
        if (mRootView != null) {
            RecyclerView.Adapter adapter = mRootView.getAdapter();
            RecyclerView.LayoutManager manager = mRootView.getLayoutManager();
            if (adapter == null || adapter.getItemCount() == 0) {
                return true;
            } else {
                int[] into = {0, 0};
                if (manager != null) {
                    if (manager instanceof LinearLayoutManager) {
                        into[0] = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                    } else if (manager instanceof GridLayoutManager) {
                        into[0] = ((GridLayoutManager) manager).findFirstVisibleItemPosition();
                    } else if (manager instanceof StaggeredGridLayoutManager) {
                    }
                }
                if (into.length > 0 && into[0] <= 1) {
                    View firstVisibleChild = mRootView.getChildAt(0);
                    if (firstVisibleChild != null) {
                        return firstVisibleChild.getTop() >= mRootView.getTop();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 滚动动画
     **/
    @Override
    protected void smoothScrollToTop() {
        mScalingRunnable.startAnimation(200L);
    }

    @Override
    protected RecyclerView createRootView(Context context, AttributeSet attrs) {
        RecyclerView rv = new RecyclerView(context, attrs);
        return rv;
    }

    /**
     * Zoom回原状的动画
     */
    class ScalingRunnable implements Runnable {

        protected long mDuration;
        protected boolean mIsFinished = true;
        protected float mScale;
        protected long mStartTime;

        ScalingRunnable() {
        }

        public void aboutAnimation() {
            mIsFinished = true;
        }

        public boolean isFinished() {
            return mIsFinished;
        }

        public void startAnimation(long paramLong) {
            if (mZoomView != null) {
                mStartTime = SystemClock.currentThreadTimeMillis();
                mDuration = paramLong;
                mScale = ((float) mHeaderContainer.getBottom()) / mHeaderHeight;
                mIsFinished = false;
                post(this);
            }
        }


        @Override
        public void run() {
            if (mZoomView != null) {
                float f2;
                ViewGroup.LayoutParams localLayoutParams;
                if (!mIsFinished && mScale > 1) {
                    float f1 = ((float) SystemClock.currentThreadTimeMillis() - (float) mStartTime) / (float) mDuration;
                    f2 = mScale - (mScale - 1.0F) * PullToZoomRecyclerView.sInterpolator.getInterpolation(f1);
                    localLayoutParams = mHeaderContainer.getLayoutParams();
                    if (f2 > 1.0F) {
                        localLayoutParams.height = ((int) (f2 * mHeaderHeight));
                        mHeaderContainer.setLayoutParams(localLayoutParams);
                        post(this);
                        return;
                    }
                    mIsFinished = true;
                }
            }
        }
    }

}

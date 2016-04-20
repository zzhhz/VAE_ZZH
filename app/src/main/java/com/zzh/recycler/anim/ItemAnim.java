package com.zzh.recycler.anim;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by ZZH on 16/4/19.
 */
public class ItemAnim extends DefaultItemAnimator{

    private static final String TAG = "ItemAnim";

    @Override
    public void endAnimations() {
        super.endAnimations();
        Log.d(TAG, "endAnimations: -------------");
    }

    public ItemAnim() {
        super();
        Log.d(TAG, "ItemAnim: ---------------");
    }

    @Override
    public void runPendingAnimations() {
        super.runPendingAnimations();
        Log.d(TAG, "runPendingAnimations: ---------------");
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "animateRemove: ----------------");
        return super.animateRemove(holder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "animateAdd: -----------------");
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        Log.d(TAG, "animateMove: -----------------");
        return super.animateMove(holder, fromX, fromY, toX, toY);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        Log.d(TAG, "animateChange: -----------------");
        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        super.endAnimation(item);
        Log.d(TAG, "endAnimation: -----------");
    }

    @Override
    public boolean isRunning() {
        Log.d(TAG, "isRunning: --------------------");
        return super.isRunning();
    }
}

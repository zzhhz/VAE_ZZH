package com.zzh.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

/**
 * ZZH
 * 布局动画
 */
public class LayoutAnimActivity extends BaseActivity {
    private int i = 0;
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_anim);
        init();
    }

    @Override
    protected void initView() {

        mContainer = (LinearLayout) findViewById(R.id.linear_container);
    }

    @Override
    protected void initData() {

        LayoutTransition transition = new LayoutTransition();
        mContainer.setLayoutTransition(transition);
        /**
         * view 出现时的动画
         */
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(null, "rotationY", 0F, 180F, 0F);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        animator1.setRepeatCount(0);
        transition.setAnimator(LayoutTransition.APPEARING, animator1);
        transition.setDuration(1000);

        /**
         * view 消失时，view自身的动画效果
         */
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(null, "rotationX", 0F, 90F, 0F).
                setDuration(1000);
        transition.setAnimator(LayoutTransition.DISAPPEARING, animator2);

        /**
         * view 动画改变时，布局中的每个子view动画的时间间隔
         */
        transition.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
        transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
        /**
         * 为什么这里要这么写？具体我也不清楚，ViewGroup源码里面是这么写的，我只是模仿而已
         * 不这么写貌似就没有动画效果了，所以你懂的！
         */
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom =PropertyValuesHolder.ofInt("bottom", 0, 1);

        /**
         * view出现时，导致整个布局改变的动画
         */
        //PropertyValuesHolder animator3 = PropertyValuesHolder.ofFloat("scaleX", 1F, 2F, 1F);
        PropertyValuesHolder animator3 = PropertyValuesHolder.ofFloat("rotation", 0, 10, -10, 6, -6, 3, -3, 0);
        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, animator3).
                setDuration(1000);
        changeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setScaleX(1.0f);
            }
        });
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);


        /**
         * view消失，导致整个布局改变时的动画
         */
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, 2f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe("scaleX", kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
                setDuration(1000);
        changeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setScaleX(1.0f);
            }
        });
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);


    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_add:
                addContainerView();
                break;
            case R.id.btn_del:
                delContainerView();
                break;
        }
    }

    private void delContainerView() {
        if (mContainer.getChildCount()>=0){
            mContainer.removeViewAt(0);
            i--;
        }
    }

    private void addContainerView() {
        i++;
        Button button = new Button(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setText("button "+i);
        button.setLayoutParams(params);
        mContainer.addView(button, 0);
    }
}

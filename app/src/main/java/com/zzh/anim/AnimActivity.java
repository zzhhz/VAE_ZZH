package com.zzh.anim;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.utils.DensityUtils;

/**
 * 属性动画
 */
public class AnimActivity extends BaseActivity {

    private ImageView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        init();
    }

    @Override
    protected void initView() {
        mView = (ImageView) findViewById(R.id.animImage);
    }

    @Override
    protected void initData() {

        //设置属性动画
    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.startAnim).setOnClickListener(this);
        findViewById(R.id.btn_shake).setOnClickListener(this);
        findViewById(R.id.btn_swing).setOnClickListener(this);
        findViewById(R.id.drop_down).setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startAnim:
                //performAnimate(mView, mView.getWidth(), DensityUtils.getDisplayWidth(this));
                scaleImageView();
                break;
            case R.id.btn_shake:
                ShakeAnimator shakeAnimator = new ShakeAnimator();
                shakeAnimator.prepare(mView);
                shakeAnimator.setDuration(1000);
                shakeAnimator.start();
                break;
            case R.id.btn_swing:
                SwingAnimator swingAnimator = new SwingAnimator();
                swingAnimator.prepare(mView);
                swingAnimator.start();
                break;
            case R.id.drop_down:
                startDropDownAnimation();
                break;
        }
    }

    private void scaleImageView() {
        int width = DensityUtils.getDisplayWidth(mContext);
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX", 1, 2);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY", 1, 2);

        ObjectAnimator start = ObjectAnimator.ofPropertyValuesHolder(mView, holderX, holderY);
        start.setRepeatCount(1);
        start.setDuration(5000).setRepeatMode(ValueAnimator.REVERSE);
        start.start();
    }

    private void performAnimate(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                //计算当前进度占整个动画过程的比例，浮点型，0-1之间
                float fraction = currentValue / 100f;

                //这里我偷懒了，不过有现成的干吗不用呢
                //直接调用整型估值器通过比例计算出宽度，然后再设给Button
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });

        valueAnimator.setDuration(5000).start();
    }

    /**
     * 模拟物理降落, 降落的控件有一个向上弹起的过程
     */
    private void startDropDownAnimation() {
        PropertyValuesHolder holderY  = PropertyValuesHolder.ofFloat("Y", 0, DensityUtils.getDisplayHeight(mContext) - mView.getHeight() - DensityUtils.dp2px(mContext, 16));
        ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(mView, holderY);
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(3000);
        anim.start();
    }
}

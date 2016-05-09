package com.zzh.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.widget.Button;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class PropertyAnimationActivity extends BaseActivity {
    private Button mScaleXButton;
    private Button mScaleXYButton;
    private Button mTranslateXButton;
    private Button mAlphaButton;
    private Button mAnimatorSetByCodesButton;
    private Button mPropertyValuesHolderButton;
    private Button mViewPropertyAnimatorButton;
    private ObjectAnimator mObjectAnimator1;
    private ObjectAnimator mObjectAnimator2;
    private ObjectAnimator mObjectAnimator3;
    private AnimatorSet mAnimatorSet1;
    private AnimatorSet mAnimatorSet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        init();
    }

    @Override
    protected void initView() {

        //--------->以下为在X方向上进行缩放的属性动画
        mScaleXButton=(Button) findViewById(R.id.scaleXButton);
        mScaleXButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mObjectAnimator1.start();

            }
        });
        mObjectAnimator1=(ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.scalexanimator);
        mObjectAnimator1.setTarget(mScaleXButton);


        //--------->以下为在X和Y方向上进行缩放的属性动画
        mScaleXYButton=(Button) findViewById(R.id.scaleXYButton);
        mScaleXYButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimatorSet1.start();

            }
        });
        mAnimatorSet1=(AnimatorSet)AnimatorInflater.loadAnimator(this, R.animator.setanimator);
        mAnimatorSet1.setTarget(mScaleXYButton);


        //--------->以下为在X方向上进行位移的属性动画
        mTranslateXButton=(Button) findViewById(R.id.translateXButton);
        mTranslateXButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mObjectAnimator2.start();

            }
        });
        mObjectAnimator2=(ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.translateanimator);
        mObjectAnimator2.setTarget(mTranslateXButton);


        //--------->以下为透明度变化的属性动画
        mAlphaButton=(Button) findViewById(R.id.alphaButton);
        mAlphaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mObjectAnimator3.start();

            }
        });
        mObjectAnimator3=(ObjectAnimator)AnimatorInflater.loadAnimator(this, R.animator.alphaanimator);
        mObjectAnimator3.setTarget(mAlphaButton);


        //--------->以下为在代码中实现AnimatorSet
        mAnimatorSetByCodesButton=(Button) findViewById(R.id.animatorSetByCodesButton);
        mAnimatorSetByCodesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimatorSet2.start();

            }
        });
        mAnimatorSet2=new AnimatorSet();
        //设置动画
        mAnimatorSet2.playTogether(
                ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "alpha", 1,0,1),
                ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "translationX", 0f,400f,0f),
                ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "translationY", 0f,400f,0f),
                ObjectAnimator.ofFloat(mAnimatorSetByCodesButton, "rotation", 0,180,360)
        );
        //设置动画时间
        mAnimatorSet2.setDuration(2000);
        //设置动画监听
        mAnimatorSet2.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {
                System.out.println("---> onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                System.out.println("---> onAnimationRepeat");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                System.out.println("---> onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                System.out.println("---> onAnimationCancel");
            }
        });


        //在代码中要实现一个对象不同属性的动画效果,除了AnimatorSet外
        //还可利用PropertyValuesHolder和ViewPropertyAnimator对象来实现
        //--------->以下为在代码中利用PropertyValuesHolder实现类似AnimatorSet的效果
        mPropertyValuesHolderButton=(Button) findViewById(R.id.propertyValuesHolderButton);
        PropertyValuesHolder propertyValuesHolderX=PropertyValuesHolder.ofFloat("translationX", 0f,200f);
        PropertyValuesHolder propertyValuesHolderY=PropertyValuesHolder.ofFloat("translationY", 0f,200f);
        final ObjectAnimator objectAnimator
                =ObjectAnimator.ofPropertyValuesHolder(mPropertyValuesHolderButton, propertyValuesHolderX, propertyValuesHolderY);
        objectAnimator.setDuration(2000);
        mPropertyValuesHolderButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.start();
            }
        });

        //在代码中要实现一个对象不同属性的动画效果,除了AnimatorSet外
        //还可利用PropertyValuesHolder和ViewPropertyAnimator对象来实现
        //--------->以下为在代码中利用ViewPropertyAnimator实现类似AnimatorSet的效果
        mViewPropertyAnimatorButton=(Button) findViewById(R.id.viewPropertyAnimatorButton);
        mViewPropertyAnimatorButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                ViewPropertyAnimator viewPropertyAnimator=mViewPropertyAnimatorButton.animate();
                //位移
                viewPropertyAnimator.translationX(100f);
                //透明度
                viewPropertyAnimator.alpha(0.5f);
                //监听
                viewPropertyAnimator.setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                    }
                    @Override
                    public void onAnimationEnd(Animator animator){
                        //动画结束后将其透明度和位置还原
                        v.animate().alpha(1).translationX(0);
                    }
                });
                viewPropertyAnimator.start();

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}

package com.zzh.recycler.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.TranslateAnimation;

/**
 * Created by ZZH on 16/4/19.
 */
public class MoveToUpAnim extends TranslateAnimation {

    public MoveToUpAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveToUpAnim(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        super(fromXDelta, toXDelta, fromYDelta, toYDelta);
    }
}

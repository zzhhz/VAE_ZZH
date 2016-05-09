package com.zzh.anim;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by ZZH on 16/5/9.
 *
 * @Date: 16/5/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: 赵中恒
 */
public class ShakeAnimator extends BaseViewAnimator{
    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(target, "translationX",
                0, 25, -25, 25, -25,15, -15, 6, -6, 0));
    }
}

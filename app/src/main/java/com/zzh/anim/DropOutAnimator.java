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
public class DropOutAnimator extends BaseViewAnimator{
    @Override
    protected void prepare(View target) {
        int distance = target.getTop() + target.getHeight();
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "translationY", -distance, 0);
        DropOutAnimator animator1 = new DropOutAnimator();

    }
}

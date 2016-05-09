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
public class SwingAnimator extends BaseViewAnimator{

    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "rotation", 0, 10, -10, 6, -6, 3, -3, 0)
        );
    }
}

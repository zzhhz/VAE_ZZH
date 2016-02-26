package com.zzh.html5.interfaces;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.JavascriptInterface;

import java.lang.annotation.Annotation;

/**
 * Created by zzh on 2016/2/26.
 * js 和安卓的交互的接口
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class ComJavaScriptInterface implements JavascriptInterface {
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}

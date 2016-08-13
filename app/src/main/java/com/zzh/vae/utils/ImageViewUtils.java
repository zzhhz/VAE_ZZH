package com.zzh.vae.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzh.vae.ZZHConstants;

/**
 * Created by zzh on 2015/11/9.
 */
public class ImageViewUtils {

    public static int DEFAULT_NULL = -1;

    public static void setImagefromUrl(Context context, String img_url,
                                       ImageView imageView) {
        setImagefromUrl(context, img_url, DEFAULT_NULL, DEFAULT_NULL, imageView);
    }

    public static void setImagefromUrl(Context context, String img_url,
                                       int width, int height, ImageView imageView) {
        if (null == img_url || img_url.equals("")) {
            imageView.setImageResource(ZZHConstants.DEFAULT_IMG);
            return;
        }
        if (width <= 0 || height <= 0) {
            if (DEFAULT_NULL != width || DEFAULT_NULL != height) {
                return;
            }
        }
        try {
            DrawableRequestBuilder<String> builder = Glide.with(context)
                    .load(img_url).placeholder(ZZHConstants.DEFAULT_IMG)
                    .error(ZZHConstants.DEFAULT_ERROR_IMG).centerCrop().crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            if (DEFAULT_NULL != width && DEFAULT_NULL != height) {
                builder.override(width, height);
            }
            builder.into(imageView);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}

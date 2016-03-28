package com.zzh.vae;

import android.app.Application;

import com.zzh.vae.utils.DeviceUtils;

/**
 * Created by zzh on 2016/3/28.
 */
public class ZZHApplication extends Application {
    /**设备号*/
    public static String DEVICE_ID;
    @Override
    public void onCreate() {
        super.onCreate();
        DEVICE_ID = DeviceUtils.getDeviceId(this);
    }
}

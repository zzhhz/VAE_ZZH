package com.zzh.vae.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by zzh on 2016/3/28.
 * 手机设备的工具
 * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 */
public class DeviceUtils {
    //<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    public static String getDeviceId(Context context){
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }
}

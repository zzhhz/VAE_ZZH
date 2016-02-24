package com.zzh.vae.utils;

import android.os.Environment;
import android.support.annotation.NonNull;

/**
 * Created by zzh on 2016/2/24.
 * 手机内置存储的辅助工具
 */
public class SDCardUtils {
    /**
     * @return 获得手机内置存储的路径。
     */
    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * @return 获取手机内置存储的下载路径
     */
    public static String getExternalStorageDownloadPath() {
        return getExtStgDirPathByKey(Environment.DIRECTORY_DOWNLOADS);//vironment.
    }

    /**
     * @param key 文件目录名称
     * @return 目录的绝对路径
     */
    private static String getExtStgDirPathByKey(@NonNull String key) {
        return Environment.getExternalStoragePublicDirectory(key).getAbsolutePath();
    }

}

package com.zzh.retrofit;

import com.zzh.vae.ZZHConstants;

import retrofit2.Retrofit;

/**
 * Created by zzh on 2016/4/6.
 */
public class RetroFitUtils {
    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ZZHConstants.LOCAL_HOST_IP).build();
        return retrofit;
    }

    public static <T> T getService(Class<T> cls) {
        Retrofit retrofit = getRetrofit();
        return retrofit.create(cls);
    }

}

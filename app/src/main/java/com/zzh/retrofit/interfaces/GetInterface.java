package com.zzh.retrofit.interfaces;

import com.zzh.retrofit.bean.HospitalAndDepartment;
import com.zzh.vae.ZZHConstants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zzh on 2016/4/6.
 */
public interface GetInterface {
    @GET(ZZHConstants.URL_TEST_JSON) //使用GET方式访问网络
    Call<HospitalAndDepartment> getStringFromNet(@Query("type") String type);

    @FormUrlEncoded
    @POST("/Test01/PostServlet")
    Call<ResponseBody> postString2Net(@Field("name") String params);

}

package com.zzh.retrofit.interfaces;

import com.zzh.retrofit.bean.HospitalAndDepartment;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.model.DoctorMsg;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zzh on 2016/4/6.
 */
public interface GetInterface {
    @GET(ZZHConstants.URL_TEST_JSON) //使用GET方式访问网络
    Call<HospitalAndDepartment> getStringFromNet(@Query("type") String type);
    @GET(ZZHConstants.URL_TEST_JSON) //使用GET方式访问网络
    Call<List<DoctorMsg>> getListDoctorFromNet(@QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/Test01/PostServlet")
    Call<ResponseBody> postString2Net(@Field("name") String params);

}

package com.zzh.retrofit;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.zzh.retrofit.bean.HospitalAndDepartment;
import com.zzh.retrofit.interfaces.GetInterface;
import com.zzh.vae.R;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.base.BaseActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit网络访问的使用<br />
 * 网络访问：<br />
 * 常用的注解：<br/>
 *
 * @GET get网络请求方式 <br/>
 * @POST POST网络请求方式 <br/>
 * @Headers 头信息参数 <br/>
 * @Path 路径参数，替换url地址中｛｝所括的部分<br/>
 * @Query 查询参数。将url地址中追加类似pageNum = 1的字符串，形成提交给服务器端的请求参数 <br/>
 * @QueryMap 查询参数集合。在url地址中追加类似type=1&count=30&page=0; <br/>
 * @FormUrlEncoded 对表单域中填写进行编码处理，避免乱码 <br/>
 * @Field 指定form表单域中每个控件的name及相应的值 <br/>
 * @FieldMap <br/>
 * @Multipat POST提交分块请求。如上传文件 <br/>
 * @Part POST提交分块请求 <br/>
 * @BODY POST提交分块请求 <br/>
 */
public class RetrofitActivity extends BaseActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        init();
    }

    @Override
    protected void initView() {
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void initData() {
//        GetInterface getInterface =
        //创建retrofit对象，使用retrofit对象创建网络访问接口
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ZZHConstants.URL_BASE_TEST_JSON)//
                .addConverterFactory(GsonConverterFactory.create()) //
                .build();
        GetInterface getInterface = retrofit.create(GetInterface.class);
        //创建出Call对象，执行网络访问接口，这一步不是真正的联网，必须使用call.execute();或者call.enqueue(回调接口);
        Call<HospitalAndDepartment> call = getInterface.getStringFromNet(ZZHConstants.URL_TEST_JSON_PARAMS);
        //执行，回调接口，
        call.enqueue(new Callback<HospitalAndDepartment>() {
            @Override
            public void onResponse(Call<HospitalAndDepartment> call, Response<HospitalAndDepartment> response) {
                HospitalAndDepartment body = response.body();
                textView.setText("" + body.toString());
            }

            @Override
            public void onFailure(Call<HospitalAndDepartment> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.retrofit1).setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrofit1:
                load();
                break;
            default:
                break;
        }
    }

    private void load() {
        GetInterface service = RetroFitUtils.getService(GetInterface.class);
        Call<ResponseBody> call = service.postString2Net("zhaozhongheng");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    loge(response.body().string()+"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}

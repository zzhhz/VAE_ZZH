package com.zzh.retrofit;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

/**
 * retrofit网络访问的使用<br />
 * 网络访问：<br />
 * 常用的注解：<br/>
 *
 * @get get网络请求方式 <br/>
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
 * @BODY POST提交分块请求<br/>
 */
public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        init();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}

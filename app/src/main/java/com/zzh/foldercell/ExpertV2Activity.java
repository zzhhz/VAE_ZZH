package com.zzh.foldercell;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.model.DoctorMsg;

/**
 * Created by ZZH on
 *
 * @Date:
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class ExpertV2Activity extends BaseActivity {
    private ImageView mImageView_head;
    private TextView mTextView_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert_v2);
        init();
    }

    @Override
    protected void initView() {
        mImageView_head = (ImageView) findViewById(R.id.expert_head_detail);
        mTextView_name = (TextView) findViewById(R.id.expert_name_detail);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null){
            DoctorMsg msg = (DoctorMsg) intent.getSerializableExtra("msg");
            if (msg != null){
                //ImageViewUtils.setImagefromUrl(mContext, msg.getIconUrl(), mImageView_head);
                Glide.with(mContext).load(msg.getIconUrl()).centerCrop().override(100, 100).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView_head);
                mTextView_name.setText(msg.getSpecialistName());
            }
        }

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

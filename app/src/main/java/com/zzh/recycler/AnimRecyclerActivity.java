package com.zzh.recycler;


import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zzh.recycler.adapter.UserAdapter;
import com.zzh.recycler.anim.ItemAnim;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;
import com.zzh.vae.model.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerview item动画效果
 * //TODO 先看MATERIAL DESIGN 动画效果,再来完善这一部分
 */
public class AnimRecyclerActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_recycler);
        init();
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_anim);
        mAdapter = new UserAdapter();
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new ItemAnim());
    }

    @Override
    protected void initData() {

        List<Users> usersList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Users users = new Users("username :    " + i);
            usersList.add(users);
        }
        mAdapter.addAll(usersList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Users users = new Users("aad + ====" );
                mAdapter.add2Position(users, 0);
//                mAdapter.notifyItemChanged(0);
                mAdapter.notifyItemInserted(0);
                loge("-----add-----");
                //mAdapter.notifyDataSetChanged();
                break;
            case R.id.del:
                mAdapter.remove2Position(0);
                loge("-----del-----");
                mAdapter.notifyItemRemoved(0);
                break;
        }
        mRecyclerView.scrollToPosition(0);
    }
}

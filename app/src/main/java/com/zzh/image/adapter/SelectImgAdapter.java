package com.zzh.image.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zzh.image.holder.ImgViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 2016/2/24.
 * 显示图片适配器
 */
public class SelectImgAdapter extends RecyclerView.Adapter<ImgViewHolder>{
    private static final int INT_NOMAL_IMAGE=10000; //普通的图片
    private static final int INT_ADD_IMAGE = 10001; //最后的一张添加的图片
    private Context mContext;

    private List dataList = null;

    public SelectImgAdapter(Context mContext) {
        this.mContext = mContext;
        dataList = new ArrayList<>();
    }

    @Override
    public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == INT_ADD_IMAGE){

        }
        return null;
    }

    @Override
    public void onBindViewHolder(ImgViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (dataList.size()-1)){
            return INT_ADD_IMAGE;
        } else {
            return INT_NOMAL_IMAGE;
        }
    }
}

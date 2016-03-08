package com.zzh.image.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzh.image.ShowSystemPictureActivity;
import com.zzh.image.bean.Picture;
import com.zzh.image.holder.ImgViewHolder;
import com.zzh.image.loader.ImageLoader;
import com.zzh.vae.R;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Stack;

/**
 * Created by zzh on 2016/2/24.
 * 显示图片适配器
 */
public class SelectImgAdapter extends RecyclerView.Adapter<ImgViewHolder>{
    private static final int INT_NOMAL_IMAGE=10000; //普通的图片
    private static final int INT_ADD_IMAGE = 10001; //最后的一张添加的图片
    private Context mContext;

    private List<Picture> dataList = null;

    public SelectImgAdapter(Context mContext) {
        this.mContext = mContext;
        dataList = new ArrayList<>();
    }

    @Override
    public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /*if (viewType == INT_ADD_IMAGE){

        } else {

        }*/

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new ImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImgViewHolder holder, int position) {
        if ((position+1) == getItemCount()){
            holder.imageView.setImageResource(R.mipmap.icon_add);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShowSystemPictureActivity.class);
                    mContext.startActivity(intent);
                }
            });
        } else {
            Picture picture = dataList.get(position);
        }
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

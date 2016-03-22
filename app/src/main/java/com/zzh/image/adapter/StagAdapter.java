package com.zzh.image.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.image.bean.Img;
import com.zzh.image.bean.Picture;
import com.zzh.image.holder.ImgViewHolder;
import com.zzh.image.loader.ImageLoader;
import com.zzh.vae.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 2016/3/22.
 */
public class StagAdapter extends RecyclerView.Adapter<ImgViewHolder>{
    private List<Picture> dataList;

    public StagAdapter() {
        dataList = new ArrayList<>();
    }

    public void addAll(List<Picture> list){
        dataList.addAll(list);
    }

    @Override
    public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null);
        return new ImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImgViewHolder holder, int position) {
        holder.item_select.setVisibility(View.GONE);
        Picture picture = dataList.get(position);
        ImageLoader.getInstance().loadImage(picture.getDir(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

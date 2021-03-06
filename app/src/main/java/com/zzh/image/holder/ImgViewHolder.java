package com.zzh.image.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zzh.vae.R;

/**
 * Created by zzh on 2016/2/24.
 */
public class ImgViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public ImageButton item_select;
    public ImgViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        item_select = (ImageButton) itemView.findViewById(R.id.item_select);
    }
}

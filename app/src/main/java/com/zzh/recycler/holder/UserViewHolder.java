package com.zzh.recycler.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzh.vae.R;

/**
 * Created by ZZH on 16/4/19.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageViewHead;
    public TextView userName;

    public UserViewHolder(View itemView) {
        super(itemView);
        imageViewHead = (ImageView) itemView.findViewById(R.id.imageViewHead);
        userName = (TextView) itemView.findViewById(R.id.userName);
    }
}

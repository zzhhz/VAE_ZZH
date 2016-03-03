package com.zzh.zoom.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zzh.vae.R;

/**
 * Created by zzh on 2016/3/3.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder{
    public TextView textView;
    public ItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }
}

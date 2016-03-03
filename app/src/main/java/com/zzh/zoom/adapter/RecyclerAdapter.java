package com.zzh.zoom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.vae.R;
import com.zzh.zoom.holder.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 2016/3/3.
 */
public class RecyclerAdapter extends RecyclerViewHeaderAdapter<ItemViewHolder> {
    private List<String> list;
    private Context mContext;
    public RecyclerAdapter(Context context) {
        super(context);
        mContext = context;
        list = new ArrayList<>();
    }

    public void addAll(List<String> list){
        this.list.addAll(list);
    }


    @Override
    protected int getCount() {
        return list.size();
    }

    @Override
    protected void onBindContentViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    protected ItemViewHolder onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_single, null);
        return new ItemViewHolder(view);
    }
}

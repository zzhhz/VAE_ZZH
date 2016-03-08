package com.zzh.image.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zzh.image.loader.ImageLoader;
import com.zzh.vae.R;

import java.util.List;

/**
 * Created by zzh on 2016/3/8.
 */
public class ShowImageAdapter extends BaseAdapter{

    private Context mContext;
    private List<String> mDatas;
    private String dirPath;

    public ShowImageAdapter(Context mContext, List<String> mDatas, String dirPath) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.dirPath = dirPath;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(R.mipmap.icon_add);
        viewHolder.imageButton.setImageResource(R.mipmap.icon_unselect);
        ImageLoader.getInstance().loadImage(dirPath+"/"+mDatas.get(position), viewHolder.imageView);
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        ImageButton imageButton;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageButton = (ImageButton) itemView.findViewById(R.id.item_select);
        }
    }
}

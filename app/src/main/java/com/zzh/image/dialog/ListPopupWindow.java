package com.zzh.image.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zzh.image.bean.Picture;
import com.zzh.image.loader.ImageLoader;
import com.zzh.vae.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 2016/3/10.
 */
public class ListPopupWindow extends PopupWindow {
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private View mContentView;
    private ListView mListView;
    private List<Picture> mDatas;

    public ListPopupWindow(Context mContext, List<Picture> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        calWidthAndHeight(mContext);
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.item_image_show_popupwindow, null);

        setContentView(mContentView);
        setWidth(mWidth);
        setHeight(mHeight);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        initView();
        initEvent();
    }

    private void initView() {
        mListView = (ListView) mContentView.findViewById(R.id.id_list_dir);
        mListView.setAdapter(new ListDirAdapter(mContext, mDatas));
    }

    private void initEvent() {

    }

    /**
     * 计算宽高
     *
     * @param mContext
     */
    private void calWidthAndHeight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mWidth = outMetrics.widthPixels;
        mHeight = (int) (outMetrics.heightPixels * 0.7);
    }

    private class ListDirAdapter extends ArrayAdapter<Picture> {

        private LayoutInflater mInflater;
        private List<Picture> mDatas;

        public ListDirAdapter(Context context, List<Picture> objects) {
            super(context, 0, objects);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {

                convertView = mInflater.inflate(R.layout.item_image_show_popup_item, null);
                holder = new ViewHolder(convertView);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Picture picture = getItem(position);
            holder.mDirName.setText(picture.getName());
            holder.mDirCount.setText(String.valueOf(picture.getCount()));
            ImageLoader.getInstance().loadImage(picture.getFirstImagePath(), holder.mImg);
            return convertView;
        }

        class ViewHolder {
            ImageView mImg;
            TextView mDirName;
            TextView mDirCount;

            public ViewHolder(View itemView) {
                mImg = (ImageView) itemView.findViewById(R.id.id_dir_img);
                mDirCount = (TextView) itemView.findViewById(R.id.img_dir_count);
                mDirName = (TextView) itemView.findViewById(R.id.img_dir_name);
            }

        }
    }
}

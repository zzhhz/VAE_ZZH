package com.zzh.zoom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 2016/3/3.
 * PullToZoomRecyclerView的适配器
 */
public abstract class RecyclerViewHeaderAdapter<V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    public static final int INT_TYPE_HEADER = 0;
    public static final int INT_TYPE_FOOTER = 1;
    private final Context context;
    private View emptyView;

    public static class ExtraItem<V extends RecyclerView.ViewHolder> {
        public final int type;
        public final V view;

        public ExtraItem(int type, V view) {
            this.type = type;
            this.view = view;
        }
    }

    private final List<ExtraItem> headers;
    private final List<ExtraItem> footers;

    public RecyclerViewHeaderAdapter(Context context) {
        this.context = context;
        this.headers = new ArrayList<>();
        this.footers = new ArrayList<>();
    }

    public Context getContext() {
        return context;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        emptyView.setVisibility(getCount() == 0 ? View.VISIBLE : View.GONE);
    }

    protected abstract int getCount();

    @Override
    public int getItemCount() {
        int count = headers.size();
        count += getCount();
        count += footers.size();
        if (emptyView != null) {
            emptyView.setVisibility(getCount() == 0 ? View.VISIBLE : View.GONE);
        }
        return count;
    }

    public void addHeaderView(ExtraItem headerView) {
        headers.add(headerView);
        notifyItemInserted(headers.size());
    }

    public void removeHeaderView(int type) {
        List<ExtraItem> indexesToRemove = new ArrayList<>();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            ExtraItem item = headers.get(i);
            if (item.type == type) {
                indexesToRemove.add(item);
            }
        }

        for (ExtraItem item : indexesToRemove) {
            int index = headers.indexOf(item);
            headers.remove(item);
            notifyItemRemoved(index);
        }
    }

    public void removeHeaderView(ExtraItem headerView) {
        int indexToRemove = headers.indexOf(headerView);
        if (indexToRemove >= 0) {
            headers.remove(indexToRemove);
            notifyItemRemoved(indexToRemove);
        }
    }

    public void removeFooterView(int type) {
        List<ExtraItem> indexsToRemove = new ArrayList<>();
        int size = footers.size();
        for (int i = 0; i < size; i++) {
            ExtraItem item = footers.get(i);
            if (item.type == type) {
                indexsToRemove.add(item);
            }
        }

        for (ExtraItem item :
                indexsToRemove) {
            int index = footers.indexOf(item);
            footers.remove(item);
            notifyItemRemoved(index);
        }
    }

    public void removeFooterView(ExtraItem footerView) {
        int indexToRemove = footers.indexOf(footerView);

        if (indexToRemove >= 0) {
            footers.remove(indexToRemove);
            notifyItemRemoved(headers.size() + getCount() + indexToRemove);
        }
    }

    public ExtraItem getHeader(int mIntArgHeaderPos){
        if (headers != null && headers.size() > mIntArgHeaderPos)
        {
            return headers.get(mIntArgHeaderPos);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headers.size()) {
            return INT_TYPE_HEADER;
        } else {
            return INT_TYPE_FOOTER;
        }
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        for (ExtraItem<V> item : headers){
            if (viewType == item.type) {
                return item.view;
            }
        }

        for (ExtraItem<V> item :
                footers) {
            if (viewType == item.type) {
                return item.view;
            }
        }
        return onCreateContentView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        if (position >= headers.size() && (position - headers.size()) < getCount()) {
            onBindContentViewHolder((V)holder, position-headers.size());
        } else {
            try {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                lp.setFullSpan(true);
                holder.itemView.setLayoutParams(lp);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    protected abstract void onBindContentViewHolder(V holder, int position);

    protected abstract V onCreateContentView(ViewGroup parent, int viewType);

}

package com.zzh.foldercell;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.vae.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ZZH on 16/5/10.
 *
 * @Date: 16/5/10
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class FolderCellAdapter extends RecyclerView.Adapter<FolderCellViewHolder> {
    private final Context mContext;
    private List<Object> dataList;
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    public FolderCellAdapter(Context ctx) {
        mContext = ctx;
        dataList = new ArrayList<>();
    }

    public void clear() {
        dataList.clear();
    }

    public void add(Object item) {
        dataList.add(item);
    }

    public void addAll(List<Object> list) {
        dataList.addAll(list);
    }

    @Override
    public FolderCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder_cell, parent, false);
        return new FolderCellViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FolderCellViewHolder holder, final int position) {
        if (unfoldedIndexes.contains(position)) {
            holder.mFoldingCell.unfold(true);
        } else {
            holder.mFoldingCell.fold(true);
        }

        holder.mFoldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mFoldingCell.toggle(false);
                registerToggle(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }
}
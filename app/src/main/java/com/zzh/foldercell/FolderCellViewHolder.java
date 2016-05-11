package com.zzh.foldercell;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.zzh.vae.R;

/**
 * Created by ZZH on 16/5/10.
 *
 * @Date: 16/5/10
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class FolderCellViewHolder extends RecyclerView.ViewHolder {

    public TextView showMsg;
    public View cellContent;
    public View cellContentSimple;
    public FoldingCell mFoldingCell;

    public FolderCellViewHolder(View itemView) {
        super(itemView);
        showMsg = (TextView) itemView.findViewById(R.id.showMsg);
        cellContent = itemView.findViewById(R.id.cell_content);
        cellContentSimple = itemView.findViewById(R.id.cell_content_simple);
        mFoldingCell = (FoldingCell) itemView.findViewById(R.id.folding_cell);
    }
}
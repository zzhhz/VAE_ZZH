package com.zzh.foldercell;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ramotion.foldingcell.FoldingCell;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class FolderCellListViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_cell_list_view);
        init();
    }

    @Override
    protected void initView() {
        ListView listView = (ListView) findViewById(R.id.list_view);
//        final FolderAdapter adapter = new FolderAdapter(mContext, new ArrayList<>(50));
        final FolderAdapter adapter = new FolderAdapter(mContext, null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((FoldingCell) view).toggle(false);
                adapter.registerToggle(position);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {

    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {

    }
}

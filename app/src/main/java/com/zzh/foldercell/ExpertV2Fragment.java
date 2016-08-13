package com.zzh.foldercell;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.alibaba.fastjson.JSON;
import com.ramotion.foldingcell.FoldingCell;
import com.zzh.foldercell.adapter.ExpertV2Adapter;
import com.zzh.vae.R;
import com.zzh.vae.ZZHConstants;
import com.zzh.vae.model.DoctorMsg;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 */
public class ExpertV2Fragment extends BaseFragment implements View.OnClickListener, PopupWindow.OnDismissListener {


    private List<DoctorMsg> list;
    private View view;
    private int page = 0;
    private ProgressDialog dialog;
    private PtrClassicFrameLayout frameLayout;
    private ExpertV2Adapter mV2Adapter;
    private ListView mListView;
    public ExpertV2Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dialog == null) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage("正在加载数据，请稍候···");
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_expert_v2, container, false);
        initView(view);
        return view;
    }

    private String TDPageTag = "专家列表页";

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.data_layout);
        frameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.ptrFrameLayout);
        list = new ArrayList<>();
        mV2Adapter = new ExpertV2Adapter(mContext, list);
        mListView.setAdapter(mV2Adapter);
        MaterialHeader header = new MaterialHeader(mContext);
        frameLayout.setHeaderView(header);
        frameLayout.addPtrUIHandler(header);
        frameLayout.setLastUpdateTimeRelateObject(this);

        frameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,
                        content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page = 0;
                reloadNetData(0, null, null);
            }
        });
        frameLayout.autoRefresh(true);
        frameLayout.setResistance(1.7f);
        frameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        frameLayout.setDurationToClose(200);
        frameLayout.setDurationToCloseHeader(1000);
        frameLayout.setKeepHeaderWhenRefresh(true);
        frameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 100);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((FoldingCell) view).toggle(false);
                mV2Adapter.registerToggle(position);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    private void reloadNetData(final int page, String departmentId, String hospitalId) {
        String json = null;
        try {
            JSONObject obj = new JSONObject(ZZHConstants.JSON_DOCTOR);
            json = obj.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<DoctorMsg> msgs = JSON.parseArray(json, DoctorMsg.class);
        mV2Adapter.addAll(msgs);
        mV2Adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onBroadCastReceiver(Context context, Intent intent) {
        super.onBroadCastReceiver(context, intent);
    }

    @Override
    public void onDismiss() {
    }

}

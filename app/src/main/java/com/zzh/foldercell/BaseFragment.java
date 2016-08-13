package com.zzh.foldercell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by zzh on 2015/10/30.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;

    protected MyFragmentReciver reciver = null;
    private IntentFilter filter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if ( filter == null) {
            filter = new IntentFilter();
        }
        initFilter();
        if (reciver == null) {
            reciver = new MyFragmentReciver();
        }
        mContext.registerReceiver(reciver, filter);
    }

    private void initFilter() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void onBroadCastReceiver(Context context, Intent intent) {

    }


    class MyFragmentReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            onBroadCastReceiver(context, intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (reciver != null){
            mContext.unregisterReceiver(reciver);
        }
    }
}

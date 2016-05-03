/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: 赵中恒
 */

package com.zzh.dialog.widget;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzh.vae.R;

/**
 * Created by ZZH on 16/5/3.
 */
public class ZZHDialogs extends DialogFragment {

    public ZZHDialogs(){
    }

    private static ZZHDialogs mDialogs;

    public static ZZHDialogs getInstance() {
        if (mDialogs == null){
            synchronized (ZZHDialogs.class){
                if (mDialogs == null){
                    mDialogs = new ZZHDialogs();
                }
            }
        }
        return mDialogs;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialogs, null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

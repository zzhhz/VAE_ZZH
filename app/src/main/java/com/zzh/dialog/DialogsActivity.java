package com.zzh.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zzh.dialog.interfaces.OnDismissListener;
import com.zzh.dialog.interfaces.OnItemClickListener;
import com.zzh.dialog.widget.AlertView;
import com.zzh.dialog.widget.ZZHDialogs;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

public class DialogsActivity extends BaseActivity implements OnDismissListener, OnItemClickListener {

    private AlertView mAlertView;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private AlertView mAlertViewExt;//窗口拓展例子
    private EditText etName;//拓展View内容
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
        init();
    }

    @Override
    protected void initView() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //mAlertView = new AlertView(this, "标题", "内容", "取消", new String[]{"确定"}, null, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
        mAlertView = new AlertView(mContext, "标题", "内容", "取消", new String[]{"确定"}, null, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
        //拓展窗口
        mAlertViewExt = new AlertView(this, "提示", "请完善你的个人资料！", "取消", null, new String[]{"完成"}, AlertView.Style.Alert, this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form, null);
        etName = (EditText) extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //输入框出来则往上移动
                boolean isOpen = imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen && focus ? 120 : 0);
                System.out.println(isOpen);
            }
        });
        mAlertViewExt.addExtView(extView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initSetListener() {
        findViewById(R.id.btn_dialog1).setOnClickListener(this);
    }

    @Override
    protected void handlerMessage(Message msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog1:
                ZZHDialogs dialogs = ZZHDialogs.getInstance();
                dialogs.show(getFragmentManager(),"dialogs");
                dialogs.setCancelable(true);
                break;
        }
    }

    public void alertShow1(View view) {
        mAlertView.show();
    }

    public void alertShow2(View view) {
        new AlertView(mContext, "标题", "内容", null, new String[]{"确定"}, null, AlertView.Style.Alert, this).show();

    }

    public void alertShow3(View view) {
        new AlertView(mContext, null, null, null, new String[]{"高亮按钮1", "高亮按钮2", "高亮按钮3"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3", "其他按钮4", "其他按钮5", "其他按钮6",
                        "其他按钮7", "其他按钮8", "其他按钮9", "其他按钮10", "其他按钮11", "其他按钮12"},
                AlertView.Style.Alert, this).show();
    }

    public void alertShow4(View view) {
        new AlertView(mContext, "标题", null, "取消", new String[]{"高亮按钮1"}, new String[]{"其他按钮1", "其他按钮2", "其他按钮3"}, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShow5(View view) {
        new AlertView(mContext, "标题", "内容", "去西校", (String[]) null, null, AlertView.Style.ActionSheet, this).setCancelable(true).show();
    }

    public void alertShow6(View view) {
//        new AlertView("上传头像", null, "取消", null,
//                new String[]{"拍照", "从相册中选择"},
//                this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShowExt(View view) {
        mAlertViewExt.show();
    }

    private void closeKeyboard() {
        //关闭软键盘
        imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);
        //恢复位置
        mAlertViewExt.setMarginBottom(0);
    }

    @Override
    public void onItemClick(Object o, int position) {
        closeKeyboard();
        //判断是否是拓展窗口View，而且点击的是非取消按钮
        if (o == mAlertViewExt && position != AlertView.CANCELPOSITION) {
            String name = etName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "hello," + name, Toast.LENGTH_SHORT).show();
            }

            return;
        }

        Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(Object o) {
        closeKeyboard();
        Toast.makeText(this, "消失了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mAlertView != null && mAlertView.isShowing()) {
                mAlertView.dismiss();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }
}

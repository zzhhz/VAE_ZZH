package com.zzh.blur;

import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zzh.blur.all.GridMenu;
import com.zzh.blur.all.GridMenuFragment;
import com.zzh.vae.R;
import com.zzh.vae.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BlurActivity extends BaseActivity {


    private GridMenuFragment mGridMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        init();
    }

    @Override
    protected void initView() {
        mGridMenuFragment = GridMenuFragment.newInstance(R.mipmap.back);

        findViewById(R.id.show_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.main_frame, mGridMenuFragment);
                tx.addToBackStack(null);
                tx.commit();
            }
        });

        setupGridMenu();

        mGridMenuFragment.setOnClickMenuListener(new GridMenuFragment.OnClickMenuListener() {
            @Override
            public void onClickMenu(GridMenu gridMenu, int position) {
                Toast.makeText(mContext, "Title:" + gridMenu.getTitle() + ", Position:" + position,
                        Toast.LENGTH_SHORT).show();
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

    private void setupGridMenu() {
        List<GridMenu> menus = new ArrayList<>();
        menus.add(new GridMenu("Home", R.mipmap.home));
        menus.add(new GridMenu("Calendar", R.mipmap.calendar));
        menus.add(new GridMenu("Overview", R.mipmap.overview));
        menus.add(new GridMenu("Groups", R.mipmap.groups));
        menus.add(new GridMenu("Lists", R.mipmap.lists));
        menus.add(new GridMenu("Profile", R.mipmap.profile));
        menus.add(new GridMenu("Timeline", R.mipmap.timeline));
        menus.add(new GridMenu("Setting", R.mipmap.settings));

        mGridMenuFragment.setupMenu(menus);
    }
    @Override
    public void onBackPressed() {
        if (0 == getSupportFragmentManager().getBackStackEntryCount()) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}

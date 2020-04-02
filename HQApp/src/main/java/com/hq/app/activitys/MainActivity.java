package com.hq.app.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.hq.app.R;
import com.hq.app.mylibrary.activitys.BaseActivity;
import com.hq.app.mylibrary.utils.DialogUtil;
import com.hq.app.mylibrary.utils.PermissionUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private NavigationView mNavActivityMain;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mNavActivityMain = (NavigationView) findViewById(R.id.nav_activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mBottomAppBar = (BottomNavigationView) findViewById(R.id.bottom_app_bar);
    }

    private void init() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(mDrawerLayout, "请先登录", Snackbar.LENGTH_LONG)
                        .setAction("登录", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = LoginActivity.startLoginActivity(MainActivity.this);
                                baseStartIntent(v, intent);
                            }
                        }).show();
            }
        });

        //设置默认选中的菜单
        mNavActivityMain.setCheckedItem(R.id.nav_item_01);
        //给菜单设置监听
        mNavActivityMain.setNavigationItemSelectedListener(this);

        View mNavHead = mNavActivityMain.getHeaderView(0);
        CircleImageView mNavHeadPersonPic = (CircleImageView) mNavHead.findViewById(R.id.nav_head_person_pic);
        mNavHeadPersonPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.startLoginActivity(MainActivity.this);
                baseStartIntent(v, intent);
            }
        });

        mBottomAppBar.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_01:
                showMessage("111");
                break;
            case R.id.nav_item_02:
                showMessage("222");
                break;
            case R.id.nav_item_03:
                showMessage("333");
                break;
            case R.id.nav_item_04://主题设置
                Intent intent = ThemeSettingActivity.startThemeSettingActivity(MainActivity.this, getThemeStyle());
                baseStartIntentForResult(mNavActivityMain, intent, REQUESTCODE);
                break;
            case R.id.nav_item_positon://主题设置
                intent = MapActivity.startMapActivity(MainActivity.this);
                baseStartIntent(intent);
                break;
            case R.id.nav_item2_01:
                showMessage("555");
                break;
            case R.id.nav_item2_02:
                showMessage("666");
                break;
            case R.id.tab_item_01:
                showMessage("测试权限状态");
                DialogUtil.notifyDialog(this, "读取权限=" + PermissionUtil.BL_READ_EXTERNAL_STORAGE
                        + "\n写入权限=" + PermissionUtil.BL_WRITE_EXTERNAL_STORAGE
                        + "\n开启相机权限=" + PermissionUtil.BL_CAMERA);
                break;
            case R.id.tab_item_02:
                showMessage("跳转到应用权限系统设置页面");
                PermissionUtil.gotoPermissionSettings(this);
                break;
        }
        //关闭弹出菜单
        mDrawerLayout.closeDrawers();
        return true;
    }

    //配置导航栏功能按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logo, menu);
        return true;
    }

    //导航栏功能按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showMessage("action_settings");
            return true;
        } else if (id == R.id.calendar) {
            showMessage("calendar");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //启动页面
    public static Intent startMainActivity(Context c) {
        Intent intent = new Intent(c, MainActivity.class);
        return intent;
    }
}

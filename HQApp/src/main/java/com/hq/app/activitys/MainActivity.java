package com.hq.app.activitys;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hq.app.mobile.R;
import com.hq.app.mylibrary.activitys.BaseActivity;

public class MainActivity extends BaseActivity {

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
        initAnim();
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
                                Snackbar.make(mDrawerLayout, "暂无此功能！", Snackbar.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });

        //设置默认选中的菜单
        mNavActivityMain.setCheckedItem(R.id.nav_item_01);
        //给菜单设置监听
        mNavActivityMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
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
                }
                //关闭弹出菜单
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        mBottomAppBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_item_01:
                        showMessage("首页");
                        break;
                    case R.id.tab_item_02:
                        showMessage("消息");
                        break;
                }
                return true;
            }
        });
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
}

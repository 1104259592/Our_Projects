package com.hq.app.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hq.app.R;
import com.hq.app.adapters.EventAdapter;
import com.hq.app.entities.EventEntity;
import com.hq.app.fragments.FragMainPage;
import com.hq.app.mylibrary.activitys.BaseActivity;
import com.hq.app.mylibrary.utils.DialogUtil;
import com.hq.app.mylibrary.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 主页
 */
public class MainActivity extends BaseLocalActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

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
        setTitle("首页");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLoging()) {
                    showMessage("发布");
                }
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
                baseStartIntent(intent);
            }
        });

        mBottomAppBar.setOnNavigationItemSelectedListener(this);

        cutPage(new FragMainPage());
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
                baseStartIntentForResult(ThemeSettingActivity.startThemeSettingActivity(MainActivity.this, getThemeStyle()), REQUESTCODE);
                break;
            case R.id.nav_item_positon://主题设置
                baseStartIntent(MapActivity.startMapActivity(MainActivity.this));
                break;
            case R.id.nav_item2_01:
                showMessage("555");
                break;
            case R.id.nav_item2_02:
                showMessage("666");
                break;
            case R.id.tab_item_01:
                FragMainPage fragMainPage = (FragMainPage)getFrag();
                if (!fragMainPage.isVisible()) {
                    cutPage(new FragMainPage());
                }
                break;
            case R.id.tab_item_02:
                showMessage("暂无功能");
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
        if (id == R.id.menu_release) {
            if (isLoging()) {
                showMessage("发布");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);//什么意思？
    }

    //启动页面
    public static Intent startMainActivity(Context c) {
        Intent intent = new Intent(c, MainActivity.class);
        return intent;
    }

    //获取当前显示的fragment
    private Fragment getFrag() {
        return getSupportFragmentManager().findFragmentById(R.id.main_fl);
    }

    //切换页面
    private void cutPage(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fl, fragment).commit();
    }
}

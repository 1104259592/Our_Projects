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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.hq.app.R;
import com.hq.app.adapters.EventAdapter;
import com.hq.app.model.entities.EventEntity;
import com.hq.app.mylibrary.activitys.BaseActivity;
import com.hq.app.mylibrary.utils.DialogUtil;
import com.hq.app.mylibrary.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private NavigationView mNavActivityMain;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomAppBar;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private EventEntity[] eventEntities = {
            new EventEntity("2手苹果","https://images.unsplash.com/photo-1558981001-1995369a39cd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60","降价大甩卖"),
            new EventEntity("2手苹果","https://images.unsplash.com/photo-1587467440782-154ba8654ac0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60","降价大甩卖"),
            new EventEntity("2手苹果","https://images.unsplash.com/photo-1587443836182-345f84c18961?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60","降价大甩卖"),
            new EventEntity("2手苹果","https://images.unsplash.com/photo-1587461244603-2f5b56351de2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60","降价大甩卖"),
            new EventEntity("2手苹果","https://unsplash.com/photos/HjR4bD5Kq7I","降价大甩卖"),
            new EventEntity("2手苹果","https://images.unsplash.com/photo-1587432816476-d8df44f42bb8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60","降价大甩卖"),
    };

    private List<EventEntity> entityList = new ArrayList<>();

    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEvents();
        initView();
        init();
    }

    private void initEvents() {
        entityList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(eventEntities.length);
            entityList.add(eventEntities[index]);

        }
    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mNavActivityMain = (NavigationView) findViewById(R.id.nav_activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mBottomAppBar = (BottomNavigationView) findViewById(R.id.bottom_app_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rlv_main_display);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swip_refresh);
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
                                baseStartIntent(intent);
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
                baseStartIntent(intent);
            }
        });

        mBottomAppBar.setOnNavigationItemSelectedListener(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        eventAdapter = new EventAdapter(entityList);
        mRecyclerView.setAdapter(eventAdapter);


        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshEvents();
            }
        });
    }

    private void refreshEvents() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initEvents();
                        eventAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
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
                baseStartIntentForResult(intent, REQUESTCODE);
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

        return super.onOptionsItemSelected(item);//什么意思？
    }

    //启动页面
    public static Intent startMainActivity(Context c) {
        Intent intent = new Intent(c, MainActivity.class);
        return intent;
    }
}

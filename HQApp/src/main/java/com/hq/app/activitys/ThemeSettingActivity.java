package com.hq.app.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hq.app.R;
import com.hq.app.adapters.ThemeSettingAdapter;
import com.hq.app.model.entities.ThemeEntity;
import com.hq.app.presenter.ThemeSettingPresenter;
import com.hq.app.presenter.impl.ThemeSettingPresenterImpl;
import com.hq.app.view.ThemeSettingView;
import com.hq.app.mylibrary.activitys.BaseActivity;
import com.hq.app.mylibrary.utils.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

//主题设置类
public class ThemeSettingActivity extends BaseActivity implements ThemeSettingView {

    private Toolbar mThemeSettingToolbar;
    private RecyclerView mThemeSettingRv;
    private ThemeSettingAdapter themeSettingAdapter;
    private ThemeSettingPresenter presenter;
    private int themeStyle;//当前主题风格

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_setting);
        initView();
        init();
    }

    private void initView() {
        mThemeSettingToolbar = (Toolbar) findViewById(R.id.theme_setting_toolbar);
        mThemeSettingRv = (RecyclerView) findViewById(R.id.theme_setting_rv);
    }

    private void init() {
        themeStyle = getIntent().getIntExtra("themeStyle", ThemeUtil.ThemeStyle.DEFAULT);
        setTitle("主题设置");
        setSupportActionBar(mThemeSettingToolbar);
        mThemeSettingToolbar.setNavigationIcon(R.mipmap.back);
        mThemeSettingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("change", themeStyle == getThemeStyle() ? false : true);
                setResult(RESULTCODE, intent);
                baseFinish();
            }
        });

        List<ThemeEntity> list = new ArrayList<>();
        themeSettingAdapter = new ThemeSettingAdapter(list);
        mThemeSettingRv.setAdapter(themeSettingAdapter);
        mThemeSettingRv.setLayoutManager(new GridLayoutManager(this, 2));

        themeSettingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.themeSelect(position);
            }
        });
        presenter = new ThemeSettingPresenterImpl(this, this);
        presenter.themeSelectData();
    }

    //所有主题数据集合回调
    @Override
    public void themeSelectData(List<ThemeEntity> list, int position) {
        themeSettingAdapter.addData(list);
        themeSettingAdapter.setSelectIndex(position);
        themeSettingAdapter.openLoadAnimation();
    }

    //选中的主题回调
    @Override
    public void themeSelectResult(int position) {
        themeSettingAdapter.setSelectIndex(position);
        setThemeStyle(themeSettingAdapter.getItem(position).getThemeStyle());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("change", themeStyle == getThemeStyle() ? false : true);
        setResult(RESULTCODE, intent);
        baseFinish();
    }

    //启动主题设置页
    public static Intent startThemeSettingActivity(Context c, int themeStyle) {
        Intent intent = new Intent(c, ThemeSettingActivity.class);
        intent.putExtra("themeStyle", themeStyle);
        return intent;
    }
}

package com.hq.app.presenter.impl;

import android.content.Context;

import com.hq.app.model.ThemeSettingModel;
import com.hq.app.model.entities.ThemeEntity;
import com.hq.app.model.impl.ThemeSettingModelImpl;
import com.hq.app.presenter.OnThemeSelectListener;
import com.hq.app.presenter.ThemeSettingPresenter;
import com.hq.app.view.ThemeSettingView;

import java.util.List;

/**
 * Presenter实现类
 * 协调 view 和 model 的数据交互
 */
public class ThemeSettingPresenterImpl implements ThemeSettingPresenter, OnThemeSelectListener {
    //界面显示回调
    private ThemeSettingView themeSettingView;
    //数据处理回调
    private ThemeSettingModel themeSettingModel;

    public ThemeSettingPresenterImpl(Context c, ThemeSettingView themeSettingView) {
        this.themeSettingView = themeSettingView;
        this.themeSettingModel = new ThemeSettingModelImpl(c, this);
    }

    @Override
    public void themeSelect(int position) {
        themeSettingModel.select(position);
    }

    @Override
    public void themeSelectData() {
        themeSettingModel.getThemeData();
    }

    //主题集合回调
    @Override
    public void onThemeSelectData(List<ThemeEntity> list, int position) {
        themeSettingView.themeSelectData(list, position);
    }

    //主题选择回调
    @Override
    public void onThemeSelectResult(int position) {
        themeSettingView.themeSelectResult(position);
    }
}

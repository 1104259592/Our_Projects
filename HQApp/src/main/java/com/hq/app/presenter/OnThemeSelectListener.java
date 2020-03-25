package com.hq.app.presenter;

import com.hq.app.model.entities.ThemeEntity;

import java.util.List;

/**
 * 主题选择监听
 * 应用于view，获取数据及选择回调
 */
public interface OnThemeSelectListener {

    //主题选择回调
    void onThemeSelectResult(int position);

    //所有主题集合回调
    void onThemeSelectData(List<ThemeEntity> list, int position);
}

package com.hq.app.view;

import com.hq.app.model.entities.ThemeEntity;

import java.util.List;

/**
 * 主题设置view层接口
 */
public interface ThemeSettingView {
    //主题集合回调
    void themeSelectData(List<ThemeEntity> list, int position);

    //主题选择回调
    void themeSelectResult(int position);
}

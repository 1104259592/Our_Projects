package com.hq.app.model;

/**
 * 主题设置model接口类
 */
public interface ThemeSettingModel {
    //获取主题数据集合
    void getThemeData();

    //选择主题并回调
    void select(int position);
}

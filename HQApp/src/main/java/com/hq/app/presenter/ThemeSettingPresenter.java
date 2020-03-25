package com.hq.app.presenter;

/**
 * Presenter接口类
 * 应用于model，获取数据及选择回调
 */
public interface ThemeSettingPresenter {

    //主题集合请求
    void themeSelectData();

    //主题选择
    void themeSelect(int position);
}

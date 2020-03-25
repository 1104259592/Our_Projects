package com.hq.app.model.entities;

import com.hq.app.mylibrary.utils.ThemeUtil;

//主题实体
public class ThemeEntity {

    private String themeStyleName;//名称
    private int themeStyleColor;//颜色
    private @ThemeUtil.ThemeStyle int themeStyle;//类型

    public ThemeEntity(String themeStyleNamw, int themeStyleColor, @ThemeUtil.ThemeStyle int themeStyle) {
        this.themeStyleName = themeStyleNamw;
        this.themeStyleColor = themeStyleColor;
        this.themeStyle = themeStyle;
    }

    public String getThemeStyleName() {
        return themeStyleName;
    }

    public void setThemeStyleName(String themeStyleName) {
        this.themeStyleName = themeStyleName;
    }

    public int getThemeStyleColor() {
        return themeStyleColor;
    }

    public void setThemeStyleColor(int themeStyleColor) {
        this.themeStyleColor = themeStyleColor;
    }

    public int getThemeStyle() {
        return themeStyle;
    }

    public void setThemeStyle(int themeStyle) {
        this.themeStyle = themeStyle;
    }

}

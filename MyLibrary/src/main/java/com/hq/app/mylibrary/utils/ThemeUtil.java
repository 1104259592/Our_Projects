package com.hq.app.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntDef;

import com.hq.app.mylibrary.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 主题工具
 */
public class ThemeUtil {

    public static final String KEY_NAME = "ThemeUtil";
    public static final String KEY_THEME = "themeStyle";

    //获取主题
    public static int getTheme(Context c) {
        int style = getThemeStyle(c);
        switch (style) {
            case ThemeStyle.DEFAULT:
                style = R.style.AppTheme;
                break;
            case ThemeStyle.BLUE:
                style = R.style.AppTheme_Blue;
                break;
            case ThemeStyle.BLACK:
                style = R.style.AppTheme_Black;
                break;
        }
        return style;
    }

    //获取主题类型
    public static int getThemeStyle(Context c) {
        SharedPreferences preferences = c.getSharedPreferences(
                KEY_NAME, Context.MODE_PRIVATE);
        int style = preferences.getInt(KEY_THEME, 0);
        return style;
    }

    //设置主题
    public static void setThemeStyle(Context c, @ThemeStyle int s) {
        SharedPreferences preferences = c.getSharedPreferences(
                KEY_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_THEME, s);
        editor.commit();
    }


    /**
     *枚举注解 主题类型
     * 限制只能用这三种类型
     * DEFAULT 默认
     * BLUE 蓝色
     * BLACK 黑色
     */
    @IntDef({
            ThemeStyle.DEFAULT,
            ThemeStyle.BLUE,
            ThemeStyle.BLACK
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ThemeStyle {
        int DEFAULT = 0;
        int BLUE = 1;
        int BLACK = 2;
    }
}

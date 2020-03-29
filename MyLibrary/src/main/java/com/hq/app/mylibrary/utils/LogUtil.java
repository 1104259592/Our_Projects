package com.hq.app.mylibrary.utils;

import android.util.Log;

/**
 * @Class: LogUtil
 * @Description:
 * @author: zhangqi
 * @Date: 2020/3/29
 */
public class LogUtil {
    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;

    public static final int LEVEL = VERBOSE;

    public static void v(String tag,String msg){
        if (LEVEL <= VERBOSE)
            Log.v(tag,msg);
    }

    public static void d(String tag,String msg){
        if (LEVEL <= DEBUG)
            Log.d(tag,msg);
    }
    public static void i(String tag,String msg){
        if (LEVEL <= INFO)
            Log.i(tag,msg);
    }
    public static void w(String tag,String msg){
        if (LEVEL <= WARN)
            Log.w(tag,msg);
    }
    public static void e(String tag,String msg){
        if (LEVEL <= ERROR)
            Log.e(tag,msg);
    }


}

package com.hq.app.mylibrary.utils;

import android.app.AlertDialog;
import android.content.Context;

public class DialogUtil {

    /**
     * 提示弹出框
     */
    public static void notifyDialog(Context context, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(content);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
        alertDialog.show();
    }
}

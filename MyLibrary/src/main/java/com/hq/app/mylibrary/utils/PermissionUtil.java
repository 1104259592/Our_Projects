package com.hq.app.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限申请工具类
 *
 * 使用时直接调用定义boolean变量来判断是否有此权限
 * 获取权限可直接调用gotoPermissionSettings方法跳转到应用权限系统设置页面
 *         也可调用getPermissions方法，通过弹框方式获取权限
 */
public class PermissionUtil {

    public static boolean BL_READ_EXTERNAL_STORAGE = false;//读取文件
    public static boolean BL_WRITE_EXTERNAL_STORAGE = false;//写入文件
    public static boolean BL_CAMERA = false;//打开摄像机

    //申请的权限
    private static List<String> permissions() {
        List<String> list = new ArrayList<>();
        list.add(Permission.READ_EXTERNAL_STORAGE);
        list.add(Permission.WRITE_EXTERNAL_STORAGE);
        list.add(Permission.CAMERA);
        return list;
    }

    //设置所有权限的boolean
    private static void setBlValue(List<String> list, boolean isper) {
        for (int i = 0; i < list.size(); i++) {
            if (Permission.READ_EXTERNAL_STORAGE.equals(list.get(i))) {
                BL_READ_EXTERNAL_STORAGE = isper;
            } else if (Permission.WRITE_EXTERNAL_STORAGE.equals(list.get(i))) {
                BL_WRITE_EXTERNAL_STORAGE = isper;
            } else if (Permission.CAMERA.equals(list.get(i))) {
                BL_CAMERA = isper;
            }
        }
    }

    //设置各个权限的boolean
    private static void setBlValue(String permission, boolean isper) {
        if (Permission.READ_EXTERNAL_STORAGE.equals(permission)) {
            BL_READ_EXTERNAL_STORAGE = isper;
        } else if (Permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
            BL_WRITE_EXTERNAL_STORAGE = isper;
        } else if (Permission.CAMERA.equals(permission)) {
            BL_CAMERA = isper;
        }
    }

    //获取各个权限boolean
    public static void getPermissionsBl(final Context c) {
        List<String> list = permissions();
        for (int i = 0; i < list.size(); i++) {
            if (XXPermissions.isHasPermission(c, list.get(i))) {// Permission.Group.STORAGE
                setBlValue(list.get(i), true);
            } else {
                setBlValue(list.get(i), false);
            }
        }
    }

    //申请所有权限
    public static void getPermissions(final Context c) {
        List<String> list = permissions();
        getPermissions(list, c);
    }

    //按条件申请权限
    public static void getPermissions(List<String> list, final Context c) {
        if (XXPermissions.isHasPermission(c, list)) {// Permission.Group.STORAGE
            setBlValue(list, true);
        } else {
            XXPermissions.with((Activity) c)
                    // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
//                    .constantRequest()
                    // 不指定权限则自动获取清单中的危险权限
                    .permission(list)// Permission.Group.STORAGE
                    .request(new OnPermission() {
                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {
                            setBlValue(granted, true);
                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            setBlValue(denied, false);
                        }
                    });
        }
    }

    //跳转到应用权限系统设置页面
    public static void gotoPermissionSettings(Context c) {
        XXPermissions.gotoPermissionSettings(c);
    }
}

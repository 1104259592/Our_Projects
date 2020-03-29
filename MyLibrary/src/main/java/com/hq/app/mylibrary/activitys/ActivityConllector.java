package com.hq.app.mylibrary.activitys;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class: ActivityConllector
 * @Description: 随时随地的退出程序
 * @author: zhangqi
 * @Date: 2020/3/29
 */
public class ActivityConllector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    //用于将List中存储的活动全部都销毁掉
    public static void finishAll(){
        for (Activity activity: activities) {
            if (!activity.isFinishing()){
                activity.finish();;
            }

        }
    }

}


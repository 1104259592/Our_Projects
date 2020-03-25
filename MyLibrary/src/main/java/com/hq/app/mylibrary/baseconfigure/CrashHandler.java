package com.hq.app.mylibrary.baseconfigure;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CrashHandler.class.getSimpleName();
    private static final CrashHandler sMyCrashHandler = new CrashHandler();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒", Locale.CHINA);
    private Thread.UncaughtExceptionHandler mOldHandler;
    private Context mContext;

    public static CrashHandler getInstance() {
        return sMyCrashHandler;
    }

    public void register(Context context) {
        if (context != null) {
            mOldHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (mOldHandler != this) {
                Thread.setDefaultUncaughtExceptionHandler(this);
            }
            mContext = context;
        }
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.v(TAG, "uncaughtException", ex);
        PrintWriter writer = null;
        try {
            Date date = new Date();
            String dateStr = SIMPLE_DATE_FORMAT1.format(date);

            File file = new File(Environment.getExternalStorageDirectory(), String.format("EjkjCrashLog/CrashLog_%s_%s.log", dateStr, android.os.Process.myPid()));
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            writer = new PrintWriter(file);
            writer.println("Date:" + SIMPLE_DATE_FORMAT.format(date));
            writer.println("----------------------------------------System Infomation-----------------------------------");
            String packageName = mContext.getPackageName();
            writer.println("AppPkgName:" + packageName);
            try {
                PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(packageName, 0);
                writer.println("VersionCode:" + packageInfo.versionCode);
                writer.println("VersionName:" + packageInfo.versionName);
                writer.println("Debug:" + (0 != (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE)));

                writer.println("\n\n\n----------------------------------Exception---------------------------------------\n\n");
                writer.println("----------------------------Exception message:" + ex.getLocalizedMessage() + "\n");
                writer.println("----------------------------Exception StackTrace:");
                ex.printStackTrace(writer);
            } catch (Exception e) {
                writer.println("VersionCode:-1");
                writer.println("VersionName:null");
                writer.println("Debug:Unkown");
            }
        } catch (Throwable e) {
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (Exception e) {
            }
//            if (mOldHandler != null) {
//                mOldHandler.uncaughtException(thread, ex);
//            }
            Toast.makeText(mContext, "异常信息已保存到EjkjCrashLog文件夹中", Toast.LENGTH_SHORT).show();
        }


    }

}

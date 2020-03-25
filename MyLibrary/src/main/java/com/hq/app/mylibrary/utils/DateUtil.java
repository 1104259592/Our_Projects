package com.hq.app.mylibrary.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 格式化日期
     */
    public static String getFormatDate(String pattern, long time) {
        return new SimpleDateFormat(pattern).format(new Date(time));
    }

    /**
     * 时间格式转换
     */
    public static String formatDate(String data, String oldTime, String newTime) {
        String resultDate = "";
        if (data != null && !"".equals(data)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(oldTime);
                Date dt = sdf.parse(data);
                long time = dt.getTime();
                sdf = new SimpleDateFormat(newTime);
                resultDate = sdf.format(new Date(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return resultDate;
    }

    //取得当月天数
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 时间选择框
     */
    public static void showTimePickerDialog(final View v, Context context) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                ((TextView) v).setText(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
            }
        }, year, monthOfYear, dayOfMonth).show();
    }
}

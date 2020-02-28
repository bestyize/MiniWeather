package com.yize.miniweather.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public static String convertDateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        try {
            Date date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (Exception e) {
            Log.i("日期解析错误",e.toString());
        }
        int w=cal.get(Calendar.DAY_OF_WEEK)-1;
        if (w<0){
            w=0;
        }
        return weekDays[w];
    }

}

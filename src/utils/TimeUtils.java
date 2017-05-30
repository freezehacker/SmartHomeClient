package utils;

import java.util.Calendar;

/**
 * Created by JK.
 */
public class TimeUtils {

    /*时间、日期*/
    public static String getCurrentDateTimeString() {
        Calendar calendar = Calendar.getInstance();

        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        if (hour.length() == 1) hour = "0" + hour;
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if (minute.length() == 1) minute = "0" + minute;
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        if (second.length() == 1) second = "0" + second;

        String showTime = String.format("%s月%s日 %s:%s:%s", month, day, hour, minute, second);

        return showTime;
    }

    /*日期*/
    public static String getCurrentDateString() {
        Calendar calendar = Calendar.getInstance();

        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (month.length() == 1) month = "0" + month;
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) day = "0" + day;

        String showTime = String.format("%s-%s-%s", year, month, day);

        return showTime;
    }

    /*时间*/
    public static String getCurrentTimeString() {
        Calendar calendar = Calendar.getInstance();

        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        if (hour.length() == 1) hour = "0" + hour;
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if (minute.length() == 1) minute = "0" + minute;
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        if (second.length() == 1) second = "0" + second;

        String showTime = String.format("%s：%s：%s", hour, minute, second);

        return showTime;
    }
}

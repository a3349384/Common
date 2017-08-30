package cn.zmy.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class DateUtil
{
    public static String long2Short(String longDateString)
    {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = format.parse(longDateString);

            return toShortString(date);
        }
        catch (Exception ex)
        {
            return "";
        }
    }

    public static String toShortString(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    public static String getHourMinuteString(Calendar calendar)
    {
        return String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }
}

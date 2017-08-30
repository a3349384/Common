package cn.zmy.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zmy on 2016/6/7 0007.
 */
public class ToastUtil
{
    private ToastUtil()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showShort(Context context,CharSequence message)
    {
        show(context,message, Toast.LENGTH_SHORT);
    }


    public static void showShort(Context context,int message)
    {
        show(context,message, Toast.LENGTH_SHORT);
    }

    public static void showLong(Context context,CharSequence message)
    {
        show(context,message, Toast.LENGTH_LONG);
    }

    public static void showLong(Context context,int message)
    {
        show(context,message, Toast.LENGTH_LONG);
    }

    public static void show(Context context,final CharSequence message, final int duration)
    {
        Toast.makeText(context, message, duration).show();
    }

    public static void show(Context context,final int message, final int duration)
    {
        Toast.makeText(context, message, duration).show();
    }
}

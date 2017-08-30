package cn.zmy.common.utils;

import android.text.TextUtils;

import java.util.Collection;

/**
 * Created by zmy on 2016/5/27 0027.
 */
public class Util
{
    public static boolean isNullOrEmpty(String s)
    {
        return TextUtils.isEmpty(s);
    }

    public static String nullToDefault(String s)
    {
        if (s == null)
        {
            return "";
        }

        return s;
    }

    public static int getLength(String s)
    {
        if (s == null)
        {
            return 0;
        }

        return s.length();
    }

    public static int getCollectSize(Collection collection)
    {
        if (collection == null)
        {
            return 0;
        }

        return collection.size();
    }
}

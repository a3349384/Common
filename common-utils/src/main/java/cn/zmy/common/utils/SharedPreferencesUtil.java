package cn.zmy.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by zmy on 2016/9/5 0005.
 */
public class SharedPreferencesUtil
{
    public static String getString(Context context, String key, final String defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        return settings.getString(key, defaultValue);
    }

    public static String getString(Context context, String preferenceName, String key, final String defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        return settings.getString(key, defaultValue);
    }

    public static void setString(Context context, final String key, final String value)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        settings.edit().putString(key, value).apply();
    }

    public static void setString(Context context, String preferenceName, final String key, final String value)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        settings.edit().putString(key, value).apply();
    }

    public static boolean getBoolean(Context context, final String key, final boolean defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String preferenceName, final String key, final boolean defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key)
    {
        return getSharedPreference(context, null).contains(key);
    }

    public static boolean hasKey(Context context, String preferenceName, final String key)
    {
        return getSharedPreference(context, preferenceName).contains(key);
    }

    public static void setBoolean(Context context, final String key, final boolean value)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        settings.edit().putBoolean(key, value).apply();
    }

    public static void setBoolean(Context context, String preferenceName, final String key, final boolean value)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        settings.edit().putBoolean(key, value).apply();
    }

    public static void setInt(Context context, final String key, final int value)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        settings.edit().putInt(key, value).apply();
    }

    public static void setInt(Context context, String preferenceName, final String key, final int value)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        settings.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, final String key, final int defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        return settings.getInt(key, defaultValue);
    }

    public static int getInt(Context context, String preferenceName, final String key, final int defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        return settings.getInt(key, defaultValue);
    }

    public static void setFloat(Context context, final String key, final float value)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        settings.edit().putFloat(key, value).apply();
    }

    public static void setFloat(Context context, String preferenceName, final String key, final float value)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        settings.edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, final String key, final float defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        return settings.getFloat(key, defaultValue);
    }

    public static float getFloat(Context context, String preferenceName, final String key, final float defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        return settings.getFloat(key, defaultValue);
    }

    public static void setLong(Context context, final String key, final long value)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        settings.edit().putLong(key, value).apply();
    }

    public static void setLong(Context context, String preferenceName, final String key, final long value)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        settings.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, final String key, final long defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        return settings.getLong(key, defaultValue);
    }

    public static long getLong(Context context, String preferenceName, final String key, final long defaultValue)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        return settings.getLong(key, defaultValue);
    }

    public static void remove(Context context,String key)
    {
        final SharedPreferences settings = getSharedPreference(context, null);
        settings.edit().remove(key).apply();
    }

    public static void remove(Context context, String preferenceName, String key)
    {
        final SharedPreferences settings = getSharedPreference(context, preferenceName);
        settings.edit().remove(key).apply();
    }

    public static void clear(Context context, String preferenceName)
    {
        final SharedPreferences.Editor editor = getSharedPreference(context, preferenceName).edit();
        editor.clear().apply();
    }

    private static SharedPreferences getSharedPreference(Context context, String preferenceName)
    {
        SharedPreferences sharedPreferences = null;
        if (TextUtils.isEmpty(preferenceName))
        {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        else
        {
            sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
}

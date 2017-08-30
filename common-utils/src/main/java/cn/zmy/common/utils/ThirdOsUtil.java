package cn.zmy.common.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by zmy on 2016/7/26 0026.
 */
public class ThirdOsUtil
{
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    public static boolean isMIUI()
    {
        try
        {
            final BuildProperties prop = new BuildProperties();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        }
        catch (final IOException e)
        {
            return false;
        }
    }

    public static boolean isFlyme()
    {
        try
        {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        }
        catch (final Exception e)
        {
            return false;
        }
    }

    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean dark)
    {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try
        {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), dark ? darkModeFlag : 0, darkModeFlag);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark)
    {
        boolean result = false;
        if (activity != null)
        {
            try
            {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark)
                {
                    value |= bit;
                }
                else
                {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static class BuildProperties
    {
        private final Properties properties;

        public BuildProperties() throws IOException
        {
            properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        }

        public boolean containsKey(final Object key)
        {
            return properties.containsKey(key);
        }

        public boolean containsValue(final Object value)
        {
            return properties.containsValue(value);
        }

        public Set<Map.Entry<Object, Object>> entrySet()
        {
            return properties.entrySet();
        }

        public String getProperty(final String name)
        {
            return properties.getProperty(name);
        }

        public String getProperty(final String name, final String defaultValue)
        {
            return properties.getProperty(name, defaultValue);
        }

        public boolean isEmpty()
        {
            return properties.isEmpty();
        }

        public Enumeration<Object> keys()
        {
            return properties.keys();
        }

        public Set<Object> keySet()
        {
            return properties.keySet();
        }

        public int size()
        {
            return properties.size();
        }

        public Collection<Object> values()
        {
            return properties.values();
        }
    }
}

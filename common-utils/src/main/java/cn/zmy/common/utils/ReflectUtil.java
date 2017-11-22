package cn.zmy.common.utils;

import java.lang.reflect.Field;

/**
 * Created by zmy on 2017/11/16.
 */

public class ReflectUtil
{
    public static  <T> T getFieldValue(Object obj, String fieldName)
    {
        try
        {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static void setFieldValue(Object obj, String fieldName, Object value)
    {
        try
        {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        }
        catch (Exception e) {}
    }
}

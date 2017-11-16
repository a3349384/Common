package cn.zmy.common.utils;

import java.lang.reflect.Field;

/**
 * Created by zmy on 2017/11/16.
 */

public class ReflectUtil
{
    public static  <T> T getFieldValue(Object obj, String fieldName)
    {
        Field field = null;
        try
        {
            field = obj.getClass().getDeclaredField("mParentWindow");
        }
        catch (NoSuchFieldException e)
        {
            return null;
        }
        field.setAccessible(true);
        try
        {
            return (T) field.get(obj);
        }
        catch (IllegalAccessException e)
        {
            return null;
        }
    }
}

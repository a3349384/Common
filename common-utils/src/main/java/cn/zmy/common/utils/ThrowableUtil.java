package cn.zmy.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zmy on 2016/9/9 0009.
 */
public class ThrowableUtil
{
    public static String toString(Throwable throwable)
    {
        if (throwable == null)
        {
            return null;
        }

        try
        {
            StringWriter stringWriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringWriter));
            String string = stringWriter.toString();
            stringWriter.close();
            return string;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}

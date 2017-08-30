package cn.zmy.common.utils;

/**
 * Created by zmy on 2016/6/13 0013.
 */
public class TryUtil
{
    public static void runSafe(SafeRunnable runnable)
    {
        try
        {
            runnable.run();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public interface SafeRunnable
    {
        void run() throws Exception;
    }
}

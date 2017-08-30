package cn.zmy.common.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by zmy on 2016/6/8 0008.
 */
public class ThreadUtil
{
    public static void runOnUIThread(Runnable runnable)
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }
}

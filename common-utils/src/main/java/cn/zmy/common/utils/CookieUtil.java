package cn.zmy.common.utils;

import android.webkit.CookieManager;

/**
 * Created by zmy on 2016/8/16 0016.
 */
public class CookieUtil
{
    public static String getCookieString(String url)
    {
        return CookieManager.getInstance().getCookie(url);
    }
}

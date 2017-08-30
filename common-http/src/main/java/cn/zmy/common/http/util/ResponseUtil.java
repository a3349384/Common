package cn.zmy.common.http.util;

import okhttp3.Response;

/**
 * Created by zmy on 2016/4/27 0027.
 * 请求回复帮助类
 */
public class ResponseUtil
{
    public static boolean isSuccessful(Response response)
    {
        return response != null && isSuccessful(response.code());
    }

    public static boolean isSuccessful(int resultCode)
    {
        return resultCode >= 200 && resultCode < 300;
    }
}

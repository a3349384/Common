package cn.zmy.common.http.requests;

import java.lang.reflect.Type;

/**
 * Created by zmy on 2016/4/20 0020.
 * Http请求模型
 */
public class HttpRequest
{
    public static HttpRequest newInstance(String url, Type responseType)
    {
        HttpRequest request = new HttpRequest();
        request.url = url;
        request.responseType = responseType;

        return request;
    }

    public String url;
    public Type responseType;
}

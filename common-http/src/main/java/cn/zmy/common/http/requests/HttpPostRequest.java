package cn.zmy.common.http.requests;

import java.lang.reflect.Type;

/**
 * Created by zmy on 2016/4/20 0020.
 * Post类型的Http请求模型
 */
public class HttpPostRequest extends HttpRequest
{
    public static HttpPostRequest newInstance(String url, Type responseType, String bodyString)
    {
        HttpPostRequest request = new HttpPostRequest();
        request.url = url;
        request.responseType = responseType;
        request.bodyString = bodyString;
        return request;
    }

    public String bodyString;//post body
}

package cn.zmy.common.http.requests;

import java.lang.reflect.Type;

import okhttp3.RequestBody;

/**
 * Created by zmy on 2016/9/6 0006.
 */
public class HttpPostBodyRequest extends HttpRequest
{
    public RequestBody body;

    public static HttpPostBodyRequest newInstance(String url, Type responseType, RequestBody body)
    {
        HttpPostBodyRequest request = new HttpPostBodyRequest();
        request.url = url;
        request.responseType = responseType;
        request.body = body;
        return request;
    }
}

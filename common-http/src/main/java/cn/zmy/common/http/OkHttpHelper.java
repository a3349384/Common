package cn.zmy.common.http;

import java.lang.reflect.Type;

import cn.zmy.common.http.models.HttpResult;
import cn.zmy.common.interfaces.json.JsonLoader;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by zmy on 2017/5/17 0017.
 */

public class OkHttpHelper
{
    protected OkHttpExecutor okHttpExecutor;

    public OkHttpHelper(OkHttpClient client)
    {
        this.okHttpExecutor = new OkHttpExecutor(client);
    }

    public HttpResult get(String url) throws Exception
    {
        Response response = okHttpExecutor.get(url);
        return HttpResult.from(response);
    }

    public <T> T get(String url, Type type) throws Exception
    {
        HttpResult result = get(url);
        String jsonString = new String(result.body);
        return JsonLoader.instance.fromString(jsonString,type);
    }

    public HttpResult post(String url,String jsonString) throws Exception
    {
        Response response = okHttpExecutor.post(url,jsonString,"application/json");
        return HttpResult.from(response);
    }

    public void getAsync(String url, Callback callback)
    {
        okHttpExecutor.getAsync(url, callback);
    }
}

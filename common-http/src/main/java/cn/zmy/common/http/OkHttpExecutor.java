package cn.zmy.common.http;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zmy on 2016/4/21 0021.
 */
public class OkHttpExecutor
{
    protected OkHttpClient client;

    public OkHttpExecutor(OkHttpClient client)
    {
        this.client = client;
    }

    public Response get(String url) throws Exception
    {
        Request request = new Request.Builder().url(url).get().build();
        return client.newCall(request).execute();
    }

    public Response post(String url, String bodyString, String contentType) throws Exception
    {
        RequestBody body = RequestBody.create(MediaType.parse(contentType), bodyString);
        Request request = new Request.Builder().url(url).post(body).build();
        return client.newCall(request).execute();
    }

    public Response post(String url, byte[] bodyBytes, String contentType) throws Exception
    {
        RequestBody body = RequestBody.create(MediaType.parse(contentType), bodyBytes);
        Request request = new Request.Builder().url(url).post(body).build();
        return client.newCall(request).execute();
    }

    public Response post(String url,RequestBody body) throws Exception
    {
        Request request = new Request.Builder().url(url).post(body).build();
        return client.newCall(request).execute();
    }

    public void getAsync(String url,Callback callback)
    {
        Request request = new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(callback);
    }
}

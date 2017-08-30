package cn.zmy.common.http.models;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Response;

public class HttpResult
{
    private static final int CODE_START = -100;
    private static final int CODE_ERROR = -200;

    public int code;
    public Headers headers;
    public byte[] body;

    public boolean isStartResult()
    {
        return this.code == CODE_START;
    }

    public boolean isErrorResult()
    {
        return this.code == CODE_ERROR;
    }

    public static HttpResult from(Response response) throws IOException
    {
        HttpResult httpResult = new HttpResult();
        httpResult.code = response.code();
        httpResult.headers = response.headers();
        httpResult.body = response.body().bytes();
        return httpResult;
    }
}

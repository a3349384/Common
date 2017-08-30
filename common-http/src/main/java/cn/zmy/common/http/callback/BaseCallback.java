package cn.zmy.common.http.callback;

import java.io.IOException;

import cn.zmy.common.http.models.HttpResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zmy on 2017/5/17 0017.
 */

public class BaseCallback implements Callback
{
    @Override
    public void onFailure(Call call, IOException e)
    {
        onRetry(call, e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException
    {
        onSuccess(call,HttpResult.from(response));
    }

    protected void onRetry(Call call,IOException e)
    {

    }

    protected void onSuccess(Call call, HttpResult result)
    {

    }
}

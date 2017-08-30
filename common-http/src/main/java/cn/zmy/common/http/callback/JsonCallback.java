package cn.zmy.common.http.callback;

import cn.zmy.common.http.models.HttpResult;
import cn.zmy.common.json.JsonUtil;
import cn.zmy.common.json.Typer;
import okhttp3.Call;

/**
 * Created by zmy on 2017/5/17 0017.
 */

public abstract class JsonCallback<T> extends BaseCallback
{
    @Override
    protected void onSuccess(Call call, HttpResult result)
    {
        super.onSuccess(call, result);
        String jsonString = new String(result.body);
        onJsonSuccess((T) JsonUtil.fromString(jsonString,new Typer<T>(){}.type));
    }

    protected abstract void onJsonSuccess(T t);
}

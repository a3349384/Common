package cn.zmy.common.http.callback;

import cn.zmy.common.http.models.HttpResult;
import cn.zmy.common.interfaces.json.JsonLoader;
import cn.zmy.common.interfaces.json.TypeHelper;
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
        onJsonSuccess(JsonLoader.instance.fromString(jsonString,new TypeHelper<T>(){}.type));
    }

    protected abstract void onJsonSuccess(T t);
}

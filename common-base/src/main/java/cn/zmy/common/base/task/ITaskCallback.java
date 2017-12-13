package cn.zmy.common.base.task;

/**
 * Created by zmy on 2017/12/11.
 */

public interface ITaskCallback<T>
{
    void onSuccess(T t);

    void onError(Throwable ex);
}

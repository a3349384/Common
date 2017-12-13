package cn.zmy.common.base.task;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/12/11.
 */

public class CallableExecutor
{
    public <T> void run(Callable<T> callable, ITaskCallback<T> callback)
    {
        run(callable, callback, Schedulers.newThread(), observable -> observable);
    }

    public <T> void run(Callable<T> callable, ITaskCallback<T> callback, Scheduler scheduler, Observable.Transformer<T, T> unsubscribeWhen)
    {
        Observable.create((Observable.OnSubscribe<T>) subscriber ->
        {
            try
            {
                subscriber.onNext(callable.call());
                subscriber.onCompleted();
            }
            catch (Exception e)
            {
                subscriber.onError(e);
            }
        }).compose(unsubscribeWhen).subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<T>()
        {
            @Override
            public void onCompleted()
            {

            }

            @Override
            public void onError(Throwable e)
            {
                callback.onError(e);
            }

            @Override
            public void onNext(T t)
            {
                callback.onSuccess(t);
            }
        });
    }
}

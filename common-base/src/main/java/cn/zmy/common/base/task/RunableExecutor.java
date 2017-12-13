package cn.zmy.common.base.task;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/12/11.
 */

public class RunableExecutor
{
    private UselessSubscriber mUselessSubscriber;

    public RunableExecutor()
    {
        mUselessSubscriber = new UselessSubscriber();
    }

    public void run(Runnable runnable)
    {
        run(runnable, Schedulers.newThread());
    }

    public void run(Runnable runnable, Scheduler scheduler)
    {
        Observable.create((Observable.OnSubscribe<Void>) subscriber ->
        {
            runnable.run();
            subscriber.onCompleted();
        }).subscribeOn(scheduler).subscribe(mUselessSubscriber);
    }

    public void run(Runnable runnable, Scheduler scheduler, long delay)
    {
        Observable.create((Observable.OnSubscribe<Void>) subscriber ->
        {
            runnable.run();
            subscriber.onCompleted();
        }).delaySubscription(delay, TimeUnit.MILLISECONDS).subscribeOn(scheduler).subscribe(mUselessSubscriber);
    }

    private static class UselessSubscriber extends Subscriber<Void>
    {
        @Override
        public void onCompleted()
        {

        }

        @Override
        public void onError(Throwable e)
        {

        }

        @Override
        public void onNext(Void aVoid)
        {

        }
    }
}

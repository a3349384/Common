package cn.zmy.common.base;

import java.util.concurrent.Callable;

import cn.zmy.common.base.task.ExecutorManager;
import cn.zmy.common.base.task.ITaskCallback;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/2/27 0027.
 */

public class BaseFragment extends LifeCycleFragment
{
    private boolean mResumed;

    @Override
    public void onResume()
    {
        super.onResume();
        if (!mResumed)
        {
            mResumed = true;
            onReady();
        }
    }

    /**
     * 此方法在Fragment第一次可见且可交互时调用
     * */
    protected void onReady()
    {

    }

    protected void runTask(Runnable runnable)
    {
        runTask(runnable, Schedulers.newThread());
    }

    protected void runTask(Runnable runnable, Scheduler scheduler)
    {
        if (runnable == null)
        {
            return;
        }
        ExecutorManager.Instance.getRunableExecutor().run(runnable, scheduler);
    }

    protected <T> void runTask(Callable<T> callable, ITaskCallback<T> callback, Scheduler scheduler, Observable.Transformer<T, T> unsubscribeWhen)
    {
        ExecutorManager.Instance.getCallableExecutor().run(callable, callback, scheduler, unsubscribeWhen);
    }
}

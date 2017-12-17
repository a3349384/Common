package cn.zmy.common.base.fragment;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by zmy on 2017/11/28.
 * 通常Fragment中都会发起线程执行相关任务（比如发起网络请求）。目前主要使用RxJava做线程切换。
 * 此类提供在指定生命周期时对相关任务取消订阅的功能，避免造成内存泄漏。
 */

public class LifeCycleFragment extends Fragment
{
    protected static final int LIFECYCLE_CREATE = 1;
    protected static final int LIFECYCLE_PAUSE = 2;
    protected static final int LIFECYCLE_STOP = 3;
    protected static final int LIFECYCLE_DESTROY = 4;
    protected static final int LIFECYCLE_VISIBLE = 5;
    protected static final int LIFECYCLE_INVISIBLE = 6;

    @IntDef({LIFECYCLE_CREATE, LIFECYCLE_PAUSE, LIFECYCLE_STOP, LIFECYCLE_DESTROY, LIFECYCLE_VISIBLE, LIFECYCLE_INVISIBLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LifeCycleEvent {}

    protected final PublishSubject<Integer> mLifecycleSubject;

    public LifeCycleFragment()
    {
        mLifecycleSubject = PublishSubject.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(LIFECYCLE_CREATE);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mLifecycleSubject.onNext(LIFECYCLE_PAUSE);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mLifecycleSubject.onNext(LIFECYCLE_STOP);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mLifecycleSubject.onNext(LIFECYCLE_DESTROY);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            mLifecycleSubject.onNext(LIFECYCLE_VISIBLE);
        }
        else
        {
            mLifecycleSubject.onNext(LIFECYCLE_INVISIBLE);
        }
    }

    /**
     * 在指定生命周期取消订阅
     * Observable.compose(unsubscribeWhen())
     * */
    protected <T> Observable.Transformer<T, T> unsubscribeWhen(final @LifeCycleEvent int event)
    {
        return observable -> observable.takeUntil(mLifecycleSubject.takeFirst(lifeCycleEvent -> lifeCycleEvent.equals(event)));
    }
}

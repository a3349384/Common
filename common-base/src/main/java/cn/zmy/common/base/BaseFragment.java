package cn.zmy.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by zmy on 2017/2/27 0027.
 */

public class BaseFragment extends Fragment
{
    protected final PublishSubject<LifeCycleEvent> lifecycleSubject;

    protected boolean isViewCreated;

    public BaseFragment()
    {
        lifecycleSubject = PublishSubject.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(LifeCycleEvent.CREATE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.isViewCreated = true;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        lifecycleSubject.onNext(LifeCycleEvent.PAUSE);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        lifecycleSubject.onNext(LifeCycleEvent.STOP);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        lifecycleSubject.onNext(LifeCycleEvent.DESTROY);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            lifecycleSubject.onNext(LifeCycleEvent.VISIBLE);
        }
        else
        {
            lifecycleSubject.onNext(LifeCycleEvent.INVISIBLE);
        }
    }

    protected  <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final LifeCycleEvent event)
    {
        return new Observable.Transformer<T, T>()
        {
            @Override
            public Observable<T> call(Observable<T> observable)
            {
                Observable<LifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<LifeCycleEvent, Boolean>()
                        {
                            @Override
                            public Boolean call(LifeCycleEvent lifeCycleEvent)
                            {
                                return lifeCycleEvent.equals(event);
                            }
                        });
                return observable.takeUntil(compareLifecycleObservable);
            }
        };
    }
}

package cn.zmy.common.binding.fragment;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.List;

import cn.zmy.common.base.BaseListFragment;
import cn.zmy.common.base.LifeCycleEvent;
import cn.zmy.common.binding.adapter.BaseBindingAdapter;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/4/21 0021.
 */

public abstract class BaseBindListFragment<M> extends BaseListFragment
{
    protected BaseBindingAdapter<M,? extends ViewDataBinding> bindingAdapter;

    @Override
    public void onRefresh()
    {
        super.onRefresh();
        getData(getStartPageIndex())
                .compose(bindUntilEvent(whenToCancel()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>()
                {
                    boolean success;

                    @Override
                    public void onCompleted()
                    {
                        notifyRefreshState(this.success);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        notifyRefreshState(false);
                    }

                    @Override
                    public void onNext(Object dataList)
                    {
                        bindingAdapter.getItems().clear();
                        bindingAdapter.getItems().addAll((Collection<? extends M>) dataList);
                        this.success = true;
                    }
                });
    }

    @Override
    public void onLoadMore()
    {
        super.onLoadMore();
        getData(currentPageIndex + 1)
                .compose(bindUntilEvent(whenToCancel()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>()
                {
                    boolean success;

                    @Override
                    public void onCompleted()
                    {
                        notifyLoadMoreSuccessful(this.success);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        notifyLoadMoreSuccessful(false);
                    }

                    @Override
                    public void onNext(Object o)
                    {
                        bindingAdapter.getItems().addAll((Collection<? extends M>) o);
                        this.success = true;
                    }
                });
    }

    protected LifeCycleEvent whenToCancel()
    {
        return LifeCycleEvent.STOP;
    }

    @Override
    protected final RecyclerView.Adapter onCreateAdapter()
    {
        this.bindingAdapter = onCreateBindingAdapter();
        return this.bindingAdapter;
    }

    protected abstract BaseBindingAdapter<M,? extends ViewDataBinding> onCreateBindingAdapter();

    protected abstract Observable<List<M>> getData(int pageIndex);
}

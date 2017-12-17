package cn.zmy.common.base;

import java.util.List;

import cn.zmy.common.base.task.ITaskCallback;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/12/17.
 */

public abstract class RefreshListFragment<M> extends BaseListFragment<M>
{
    /**
     * 第一页的索引
     * */
    protected int mStartPageIndex;
    /**
     * 记录当前分页的索引
     * */
    protected int mCurrentPageIndex;

    protected IRefreshProvider mRefreshProvider;

    @Override
    protected void onReady()
    {
        super.onReady();
        setStartPageIndex(1);
        if (mAutoLoadDataWhenReady)
        {
            startRefresh();
        }
    }

    /**
     * 设置第一页的索引。下次刷新后生效
     * */
    protected void setStartPageIndex(int pageIndex)
    {
        mStartPageIndex = pageIndex;
    }

    protected void setRefreshProvider(IRefreshProvider provider)
    {
        mRefreshProvider = provider;
        mRefreshProvider.setOnLoadMoreListener(this::startRefresh);
        mRefreshProvider.setOnLoadMoreListener(this::startLoadMore);
    }

    /**
     * 开始执行刷新操作
     * */
    public void startRefresh()
    {
        mRefreshProvider.startRefresh();

        mAdapter.getItems().clear();
        mCurrentPageIndex = mStartPageIndex;
        runTask(() -> getItems(mCurrentPageIndex), new ITaskCallback<List<M>>()
        {
            @Override
            public void onSuccess(List<M> items)
            {
                mAdapter.getItems().addAll(items);
                onRefreshCompleted(true);
            }

            @Override
            public void onError(Throwable ex)
            {
                onRefreshCompleted(false);
            }
        }, Schedulers.io(), unsubscribeWhen(LIFECYCLE_STOP));
    }

    /**
     * 开始执行LoadMore操作
     * */
    public void startLoadMore()
    {
        mRefreshProvider.startLoadMore();

        runTask(() -> getItems(mCurrentPageIndex + 1), new ITaskCallback<List<M>>()
        {
            @Override
            public void onSuccess(List<M> items)
            {
                mAdapter.getItems().addAll(items);
                mCurrentPageIndex++;
                onLoadMoreCompleted(true, items.size() > 0);
            }

            @Override
            public void onError(Throwable ex)
            {
                onLoadMoreCompleted(false, true);
            }
        }, Schedulers.io(), unsubscribeWhen(LIFECYCLE_STOP));
    }

    /**
     * 当Refresh完成时调用
     * */
    protected void onRefreshCompleted(boolean success)
    {
        mRefreshProvider.finishRefresh();
    }

    /**
     * 当LoadMore完成时调用
     * */
    protected void onLoadMoreCompleted(boolean success, boolean hasMoreData)
    {
        mRefreshProvider.finishLoadMore();
        if (!hasMoreData)
        {
            mRefreshProvider.setEnableLoadMore(false);
        }
    }

    /**
     * @deprecated use {{@link #startRefresh()}} instead
     * */
    @Override
    @Deprecated
    protected final void startLoadData()
    {

    }

    /**
     * @deprecated use {@link #getItems(int)} instead
     * */
    @Override
    @Deprecated
    protected final List<M> getItems()
    {
        return null;
    }

    protected abstract List<M> getItems(int pageIndex);
}

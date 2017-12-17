package cn.zmy.common.base.provider;

import android.support.v4.widget.SwipeRefreshLayout;

import cn.zmy.common.base.IRefreshProvider;

/**
 * Created by zmy on 2017/4/30.
 * 使用{@link SwipeRefreshLayout}实现。不支持loadMore
 */

public class SwipeRefreshProvider implements IRefreshProvider
{
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void startRefresh()
    {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void startLoadMore()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEnableRefresh(boolean enable)
    {
        swipeRefreshLayout.setEnabled(enable);
    }

    @Override
    public void setEnableLoadMore(boolean enable)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void finishRefresh()
    {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void finishLoadMore()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRefreshing()
    {
        return this.swipeRefreshLayout.isRefreshing();
    }

    @Override
    public boolean isLoadingMore()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOnRefreshListener(final Runnable listener)
    {
        this.swipeRefreshLayout.setOnRefreshListener(listener::run);
    }

    @Override
    public void setOnLoadMoreListener(Runnable listener)
    {
        throw new UnsupportedOperationException();
    }
}

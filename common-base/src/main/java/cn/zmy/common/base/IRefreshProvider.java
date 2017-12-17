package cn.zmy.common.base;

/**
 * Created by zmy on 2017/12/17.
 */

public interface IRefreshProvider
{
    void startRefresh();

    void startLoadMore();

    void finishRefresh();

    void finishLoadMore();

    boolean isRefreshing();

    boolean isLoadingMore();

    void setEnableRefresh(boolean enable);

    void setEnableLoadMore(boolean enable);

    void setOnRefreshListener(Runnable listener);

    void setOnLoadMoreListener(Runnable listener);
}

package cn.zmy.common.base.provider;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by zmy on 2017/4/30.
 */

public interface RefreshProvider
{
    ViewGroup onCreateView(Context context);

    void startRefresh();

    void stopRefresh();

    void startLoadMore();

    void stopLoadMore();

    boolean isRefreshing();

    boolean isLoadingMore();

    void setOnRefreshListener(Runnable listener);

    void setOnLoadMoreListener(Runnable listener);
}

package cn.zmy.common.base.provider;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewGroup;

/**
 * Created by zmy on 2017/4/30.
 */

public class SwipeRefreshProvider implements RefreshProvider
{
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public ViewGroup onCreateView(Context context)
    {
        this.swipeRefreshLayout = new SwipeRefreshLayout(context);
        this.swipeRefreshLayout.setColorSchemeResources(android.R.color.black);
        return this.swipeRefreshLayout;
    }

    @Override
    public void startRefresh()
    {
        this.swipeRefreshLayout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeRefreshLayout.setRefreshing(true);
            }
        }, 100);
    }

    @Override
    public void stopRefresh()
    {
        this.swipeRefreshLayout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 100);
    }

    @Override
    public void startLoadMore()
    {
    }

    @Override
    public void stopLoadMore()
    {

    }

    @Override
    public boolean isRefreshing()
    {
        return this.swipeRefreshLayout.isRefreshing();
    }

    @Override
    public boolean isLoadingMore()
    {
        return false;
    }

    @Override
    public void setOnRefreshListener(final Runnable listener)
    {
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                listener.run();
            }
        });
    }

    @Override
    public void setOnLoadMoreListener(Runnable listener)
    {

    }
}

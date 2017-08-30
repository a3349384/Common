package cn.zmy.common.base.provider;

import android.content.Context;
import android.view.ViewGroup;

import cn.zmy.common.base.widget.SwipeTopBottomLayout;


/**
 * Created by zmy on 2017/4/30.
 */

public class SwipeRefreshLoadMoreProvider implements RefreshProvider
{
    protected SwipeTopBottomLayout swipeTopBottomLayout;
    protected boolean canRefresh;
    protected boolean canLoadMore;

    public SwipeRefreshLoadMoreProvider(boolean canRefresh,boolean canLoadMore)
    {
        this.canRefresh = canRefresh;
        this.canLoadMore = canLoadMore;
    }

    @Override
    public ViewGroup onCreateView(Context context)
    {
        this.swipeTopBottomLayout = new SwipeTopBottomLayout(context);
        this.swipeTopBottomLayout.setColorSchemeResources(android.R.color.black);
        this.swipeTopBottomLayout.setRefreshEnabled(this.canRefresh);
        this.swipeTopBottomLayout.setLoadMoreEnabled(this.canLoadMore);
        return this.swipeTopBottomLayout;
    }

    @Override
    public void startRefresh()
    {
        this.swipeTopBottomLayout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeTopBottomLayout.setRefreshing(true);
            }
        }, 100);
    }

    @Override
    public void stopRefresh()
    {
        this.swipeTopBottomLayout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeTopBottomLayout.setRefreshing(false);
            }
        }, 100);
    }

    @Override
    public void startLoadMore()
    {
        this.swipeTopBottomLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                swipeTopBottomLayout.setLoadingMore(true);
            }
        });
    }

    @Override
    public void stopLoadMore()
    {
        this.swipeTopBottomLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                swipeTopBottomLayout.setLoadingMore(false);
            }
        });
    }

    @Override
    public boolean isRefreshing()
    {
        return this.swipeTopBottomLayout.isRefreshing();
    }

    @Override
    public boolean isLoadingMore()
    {
        return this.swipeTopBottomLayout.isLoadingMore();
    }

    @Override
    public void setOnRefreshListener(final Runnable listener)
    {
        this.swipeTopBottomLayout.setOnRefreshListener(new SwipeTopBottomLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                listener.run();
            }
        });
    }

    @Override
    public void setOnLoadMoreListener(final Runnable listener)
    {
        this.swipeTopBottomLayout.setOnLoadMoreListener(new SwipeTopBottomLayout.OnLoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                listener.run();
            }
        });
    }
}

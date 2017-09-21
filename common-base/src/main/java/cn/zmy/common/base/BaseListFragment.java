package cn.zmy.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import cn.zmy.common.base.provider.CommonRecyclerProvider;
import cn.zmy.common.base.provider.EmptyViewProvider;
import cn.zmy.common.base.provider.RecyclerViewProvider;
import cn.zmy.common.base.provider.RefreshProvider;
import cn.zmy.common.base.provider.SwipeRefreshLoadMoreProvider;

/**
 * Created by zmy on 2017/4/21 0021.
 */

public abstract class BaseListFragment extends BaseFragment
{
    protected final byte STATE_NONE = 0;
    protected final byte STATE_REFRESHING = 1;
    protected final byte STATE_LOADINGMORE = 2;
    protected final byte STATE_REFRESH_COMPLETED = 3;
    protected final byte STATE_LOADMORE_COMPLETED = 4;

    protected RefreshProvider refreshProvider;
    protected RecyclerViewProvider recyclerViewProvider;
    protected EmptyViewProvider emptyViewProvider;
    protected int currentPageIndex;
    protected byte currentState;

    protected RecyclerView.Adapter adapter;

    private RecyclerView.AdapterDataObserver adapterDataObserver;

    public BaseListFragment()
    {
        this.currentPageIndex = getStartPageIndex();
        this.refreshProvider = onCreateRefreshProvider();
        this.recyclerViewProvider = onCreateRecyclerViewProvider();
        this.currentState = STATE_NONE;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FrameLayout rootLayout = new FrameLayout(getContext());
        rootLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        if (this.refreshProvider == null)
        {
            RecyclerView recyclerView = this.recyclerViewProvider.onCreate(getContext());
            rootLayout.addView(recyclerView);
        }
        else
        {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.addView(this.recyclerViewProvider.onCreate(getContext()));

            ViewGroup refreshLayout = this.refreshProvider.onCreateView(getContext());
            refreshLayout.addView(frameLayout);
            rootLayout.addView(refreshLayout);

            this.refreshProvider.setOnRefreshListener(new Runnable()
            {
                @Override
                public void run()
                {
                    onRefresh();
                }
            });
            this.refreshProvider.setOnLoadMoreListener(new Runnable()
            {
                @Override
                public void run()
                {
                    onLoadMore();
                }
            });
        }

        return rootLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.adapter = onCreateAdapter();
        this.recyclerViewProvider.setAdapter(this.adapter);
        this.emptyViewProvider = onCreateEmptyViewProvider();
        if (this.emptyViewProvider != null)
        {
            this.adapterDataObserver = new RecyclerView.AdapterDataObserver()
            {
                @Override
                public void onChanged()
                {
                    super.onChanged();
                    notifyEmptyViewState();
                }

                @Override
                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount)
                {
                    super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                    notifyEmptyViewState();
                }

                @Override
                public void onItemRangeChanged(int positionStart, int itemCount)
                {
                    super.onItemRangeChanged(positionStart, itemCount);
                    notifyEmptyViewState();
                }

                @Override
                public void onItemRangeChanged(int positionStart, int itemCount, Object payload)
                {
                    super.onItemRangeChanged(positionStart, itemCount, payload);
                    notifyEmptyViewState();
                }

                @Override
                public void onItemRangeInserted(int positionStart, int itemCount)
                {
                    super.onItemRangeInserted(positionStart, itemCount);
                    notifyEmptyViewState();
                }

                @Override
                public void onItemRangeRemoved(int positionStart, int itemCount)
                {
                    super.onItemRangeRemoved(positionStart, itemCount);
                    notifyEmptyViewState();
                }
            };
            this.adapter.registerAdapterDataObserver(this.adapterDataObserver);
        }
        this.onRefresh();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        if (this.adapterDataObserver != null)
        {
            this.adapter.unregisterAdapterDataObserver(this.adapterDataObserver);
        }
    }

    protected abstract RecyclerView.Adapter onCreateAdapter();

    protected RefreshProvider onCreateRefreshProvider()
    {
        return new SwipeRefreshLoadMoreProvider(true,true);
    }

    protected RecyclerViewProvider onCreateRecyclerViewProvider()
    {
        return new CommonRecyclerProvider();
    }

    protected EmptyViewProvider onCreateEmptyViewProvider()
    {
        return null;
    }

    protected void onRefresh()
    {
        this.startRefresh();
    }

    protected void onLoadMore()
    {
        this.startLoadMore();
    }

    protected void startRefresh()
    {
        if (this.emptyViewProvider != null)
        {
            this.emptyViewProvider.hide();
        }

        if (this.refreshProvider != null && !this.refreshProvider.isRefreshing())
        {
            this.refreshProvider.startRefresh();
        }

        this.currentState = STATE_REFRESHING;
    }

    protected void startLoadMore()
    {
        if (this.refreshProvider != null && !this.refreshProvider.isLoadingMore())
        {
            this.refreshProvider.startLoadMore();
        }

        this.currentState = STATE_LOADINGMORE;
    }

    protected void notifyRefreshState(boolean success)
    {
        if (this.refreshProvider != null)
        {
            this.refreshProvider.stopRefresh();
        }

        if (success)
        {
            this.currentPageIndex = getStartPageIndex();
            this.currentState = STATE_REFRESH_COMPLETED;
        }
        else
        {
            this.currentState = STATE_NONE;
        }
        this.notifyEmptyViewState();
    }
    
    protected void notifyLoadMoreSuccessful(boolean success)
    {
        if (this.refreshProvider != null)
        {
            this.refreshProvider.stopLoadMore();
        }

        if (success)
        {
            this.currentPageIndex ++;
            this.currentState = STATE_LOADMORE_COMPLETED;
        }
        else
        {
            this.currentState = STATE_NONE;
        }
    }

    protected void notifyEmptyViewState()
    {
        if (this.emptyViewProvider != null && this.recyclerViewProvider != null)
        {
            int itemsCount = this.recyclerViewProvider.getAdapter().getItemCount();
            if (itemsCount == 0)
            {
                this.emptyViewProvider.show();
            }
            else
            {
                this.emptyViewProvider.hide();
            }
        }
    }

    protected int getStartPageIndex()
    {
        return 1;
    }
}

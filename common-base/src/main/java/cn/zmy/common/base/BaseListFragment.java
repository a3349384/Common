package cn.zmy.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.zmy.common.base.adapter.BaseListAdapter;
import cn.zmy.common.base.provider.ILayoutProvider;
import cn.zmy.common.base.provider.IRecyclerViewConfigurator;
import cn.zmy.common.base.provider.impl.RecyclerViewConfigurator;
import cn.zmy.common.base.provider.impl.SingleRecyclerViewProvider;
import cn.zmy.common.base.task.ITaskCallback;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/4/21 0021.
 */

public abstract class BaseListFragment<M> extends BaseFragment
{
    protected BaseListAdapter<M> mAdapter;

    /**
     * 记录当前分页的索引
     * */
    protected int mCurrentPageIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ILayoutProvider layoutProvider = onCreateLayoutProvider();
        if (layoutProvider == null)
        {
            return null;
        }
        ViewGroup root = layoutProvider.getRootLayout(getActivity());
        RecyclerView recyclerView = layoutProvider.getRecyclerView(getActivity());
        if (root == null || recyclerView == null)
        {
            return null;
        }
        IRecyclerViewConfigurator recyclerViewConfigurator = onCreateRecyclerViewConfigurator();
        if (recyclerViewConfigurator != null)
        {
            recyclerViewConfigurator.config(recyclerView);
        }
        mAdapter = onCreateAdapter();
        recyclerView.setAdapter(mAdapter);

        this.onViewCreated(root, recyclerView);
        return root;
    }

    /**
     * 创建一个{@link ILayoutProvider}并返回。当需要自定义布局时可以重写此方法。
     * */
    protected ILayoutProvider onCreateLayoutProvider()
    {
        return new SingleRecyclerViewProvider();
    }

    /**
     * 创建一个{@link IRecyclerViewConfigurator}并返回。当需要对RecyclerView进行特殊配置时可以重写此方法。
     * */
    protected IRecyclerViewConfigurator onCreateRecyclerViewConfigurator()
    {
        return new RecyclerViewConfigurator();
    }

    /**
     * 当View创建完成时回调。可以在此对View进行一些特殊配置.
     * <p>
     * 此处对RecyclerView做的配置会覆盖在{@link IRecyclerViewConfigurator}中做的配置。
     * */
    protected void onViewCreated(ViewGroup root, RecyclerView recyclerView)
    {

    }

    /**
     * 创建RecyclerView的适配器
     * */
    protected abstract BaseListAdapter<M> onCreateAdapter();

    @Override
    protected void onReady()
    {
        super.onReady();
        this.startRefresh();
    }

    /**
     * 开始执行刷新操作
     * */
    public void startRefresh()
    {
        mAdapter.getItems().clear();
        mCurrentPageIndex = getStartPageIndex();
        runTask(() -> getItems(getStartPageIndex()), new ITaskCallback<List<M>>()
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
     * 返回第一页的索引，默认值为1。
     * */
    protected int getStartPageIndex()
    {
        return 1;
    }

    /**
     * 当Refresh完成时调用
     * */
    protected void onRefreshCompleted(boolean success)
    {

    }

    /**
     * 当LoadMore完成时调用
     * */
    protected void onLoadMoreCompleted(boolean success, boolean hasMoreData)
    {

    }

    /**
     * 获取指定页的Items。
     * <p>
     * 此方法总是在IO线程中执行。
     * */
    protected abstract List<M> getItems(int pageIndex);
}

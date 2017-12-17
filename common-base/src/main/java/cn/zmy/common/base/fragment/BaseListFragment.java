package cn.zmy.common.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.zmy.common.base.adapter.BaseListAdapter;
import cn.zmy.common.base.provider.IListLayoutProvider;
import cn.zmy.common.base.task.ITaskCallback;
import rx.schedulers.Schedulers;

/**
 * Created by zmy on 2017/4/21 0021.
 */

public abstract class BaseListFragment<M> extends BaseFragment
{
    /**
     * 标志当Fragment准备完成之后是否自动开始刷新
     * */
    protected boolean mAutoLoadDataWhenReady;

    protected BaseListAdapter<M> mAdapter;
    protected IListLayoutProvider mListLayoutProvider;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        this.onSetting();
        if (mListLayoutProvider == null)
        {
            return null;
        }
        View root = mListLayoutProvider.getRootLayout(getActivity());
        RecyclerView recyclerView = mListLayoutProvider.getRecyclerView(getActivity());
        if (root == null || recyclerView == null)
        {
            return null;
        }
        this.onViewCreated(root, recyclerView);
        recyclerView.setAdapter(mAdapter);
        return root;
    }

    protected void onSetting()
    {
        setAutoRefreshWhenReady(true);
    }

    protected void onViewCreated(View rootView, RecyclerView recyclerView)
    {

    }

    protected void setListLayoutProvider(IListLayoutProvider provider)
    {
        mListLayoutProvider = provider;
    }

    protected void setAdapter(BaseListAdapter<M> adapter)
    {
        mAdapter = adapter;
    }

    protected void setAutoRefreshWhenReady(boolean autoRefresh)
    {
        mAutoLoadDataWhenReady = autoRefresh;
    }

    @Override
    protected void onReady()
    {
        super.onReady();
        if (mAutoLoadDataWhenReady)
        {
            this.startLoadData();
        }
    }

    protected void startLoadData()
    {
        mAdapter.getItems().clear();
        runTask(this::getItems, new ITaskCallback<List<M>>()
        {
            @Override
            public void onSuccess(List<M> items)
            {
                mAdapter.getItems().addAll(items);
            }

            @Override
            public void onError(Throwable ex)
            {
                ex.printStackTrace();
            }
        }, Schedulers.io(), unsubscribeWhen(LIFECYCLE_STOP));
    }

    /**
     * 获取指定页的Items。
     * <p>
     * 此方法总是在IO线程中执行。
     * */
    protected abstract List<M> getItems();
}

package cn.zmy.common.base.provider.impl;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.zmy.common.base.provider.IRecyclerViewConfigurator;

/**
 * Created by zmy on 2017/11/28.
 */

public class RecyclerViewConfigurator implements IRecyclerViewConfigurator
{
    protected Context mContext;

    @Override
    public void config(RecyclerView recyclerView)
    {
        mContext = recyclerView.getContext();

        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setHasFixedSize(hasFixedSize());
    }

    protected RecyclerView.LayoutManager getLayoutManager()
    {
        return new LinearLayoutManager(mContext);
    }

    protected boolean hasFixedSize()
    {
        return false;
    }
}

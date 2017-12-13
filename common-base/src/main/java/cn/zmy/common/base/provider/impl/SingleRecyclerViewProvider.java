package cn.zmy.common.base.provider.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import cn.zmy.common.base.provider.ILayoutProvider;

/**
 * Created by zmy on 2017/11/28.
 * 整个Layout仅由一个RecyclerView构成
 */

public class SingleRecyclerViewProvider implements ILayoutProvider
{
    private RecyclerView mRecyclerView;

    @Override
    public ViewGroup getRootLayout(Context context)
    {
        return getRecyclerView(context);
    }

    @Override
    public RecyclerView getRecyclerView(Context context)
    {
        //此方法应该仅会被UI线程调用，所以没必要对多线程情况进行处理
        if (mRecyclerView == null)
        {
            mRecyclerView = new RecyclerView(context);
        }
        return mRecyclerView;
    }
}

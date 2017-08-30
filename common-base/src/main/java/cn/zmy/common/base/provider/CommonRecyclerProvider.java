package cn.zmy.common.base.provider;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import cn.zmy.common.base.R;
import cn.zmy.common.utils.ResUtil;

/**
 * Created by zmy on 2017/4/30.
 */

public class CommonRecyclerProvider implements RecyclerViewProvider
{
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;

    @Override
    public RecyclerView onCreate(Context context)
    {
        this.recyclerView = new RecyclerView(context);
        this.recyclerView.setLayoutParams(getLayoutParams(context));
        this.recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerView.setItemAnimator(null);
        this.recyclerView.setHasFixedSize(hasFixedSize());
        if (this.adapter != null)
        {
            this.recyclerView.setAdapter(this.adapter);
        }
        RecyclerView.ItemDecoration itemDecoration = getItemDecoration(context);
        if (itemDecoration != null)
        {
            this.recyclerView.addItemDecoration(itemDecoration);
        }
        return this.recyclerView;
    }

    protected boolean hasFixedSize()
    {
        return false;
    }

    protected FrameLayout.LayoutParams getLayoutParams(Context context)
    {
        return new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    protected RecyclerView.ItemDecoration getItemDecoration(Context context)
    {
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ResUtil.getDrawable(context, R.drawable.shape_list_divider_gray_0_5dp));
        return divider;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter)
    {
        this.adapter = adapter;
        if (this.recyclerView != null)
        {
            this.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public RecyclerView.Adapter getAdapter()
    {
        return adapter;
    }
}

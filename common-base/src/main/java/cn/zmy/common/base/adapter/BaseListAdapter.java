package cn.zmy.common.base.adapter;


import android.support.v7.widget.RecyclerView;

import cn.zmy.common.base.collection.ObservableArrayList;
import cn.zmy.common.base.collection.ObservableList;
import cn.zmy.common.base.collection.OnListChangedCallback;

/**
 * Created by zmy on 2017/11/29.
 * RecyclerView adapter基类。可以自动处理数据集变化。
 */

public abstract class BaseListAdapter<M> extends RecyclerView.Adapter implements OnListChangedCallback<ObservableList<M>>
{
    protected ObservableArrayList<M> mItems;

    public BaseListAdapter()
    {
        mItems = new ObservableArrayList<>();
    }

    public ObservableArrayList<M> getItems()
    {
        return mItems;
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
        mItems.addOnListChangedCallback(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView)
    {
        super.onDetachedFromRecyclerView(recyclerView);
        mItems.removeOnListChangedCallback(this);
    }

    //region OnListChangedCallback实现

    @Override
    public void onChanged(ObservableList<M> items)
    {
        notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableList<M> items, int positionStart, int itemCount)
    {
        notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableList<M> items, int positionStart, int itemCount)
    {
        notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableList<M> items, int fromPosition, int toPosition, int itemCount)
    {
        notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(ObservableList<M> items, int positionStart, int itemCount)
    {
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    //endregion
}

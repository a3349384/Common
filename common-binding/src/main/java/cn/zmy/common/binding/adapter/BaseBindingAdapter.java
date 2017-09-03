package cn.zmy.common.binding.adapter;

import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by zmy on 2016/7/8 0008.
 * M为模型（Model）构造类型，B为具体Binding
 */
public abstract class BaseBindingAdapter<M,B extends ViewDataBinding> extends RecyclerView.Adapter<BaseBindingViewHolder<B>>
{
    protected ObservableArrayList<M> items;
    protected ListChangedCallback itemsChangeCallback;

    public BaseBindingAdapter()
    {
        items = new ObservableArrayList<>();
        itemsChangeCallback = new ListChangedCallback();
    }

    public ObservableArrayList<M> getItems()
    {
        return items;
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public BaseBindingViewHolder<B> onCreateViewHolder(ViewGroup parent, int viewType)
    {
        B binding = onCreateItemBinding(parent,viewType);
        BaseBindingViewHolder<B> holder = new BaseBindingViewHolder<>(binding.getRoot());
        holder.setBinding(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseBindingViewHolder<B> holder, int position)
    {
        onBindItem(holder.getBinding(),items.get(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
        this.items.addOnListChangedCallback(itemsChangeCallback);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView)
    {
        super.onDetachedFromRecyclerView(recyclerView);
        this.items.removeOnListChangedCallback(itemsChangeCallback);
    }

    //region 处理数据集变化
    protected void onChanged(ObservableArrayList<M> newItems)
    {
        resetItems(newItems);
        notifyDataSetChanged();
    }

    protected void onItemRangeChanged(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        resetItems(newItems);
        notifyItemRangeChanged(positionStart,itemCount);
    }

    protected void onItemRangeInserted(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        resetItems(newItems);
        notifyItemRangeInserted(positionStart,itemCount);
    }

    protected void onItemRangeMoved(ObservableArrayList<M> newItems)
    {
        resetItems(newItems);
        notifyDataSetChanged();
    }

    protected void onItemRangeRemoved(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        resetItems(newItems);
        notifyItemRangeRemoved(positionStart,itemCount);
    }

    protected void resetItems(ObservableArrayList<M> newItems)
    {
        this.items = newItems;
        //BaseBindingAdapter.this.items.addAll(newItems);
    }
    //endregion

    public abstract B onCreateItemBinding(ViewGroup parent, int viewType);

    public abstract void onBindItem(B binding, M item);

    class ListChangedCallback extends ObservableArrayList.OnListChangedCallback<ObservableArrayList<M>>
    {
        @Override
        public void onChanged(ObservableArrayList<M> newItems)
        {
            BaseBindingAdapter.this.onChanged(newItems);
        }

        @Override
        public void onItemRangeChanged(ObservableArrayList<M> newItems, int i, int i1)
        {
            BaseBindingAdapter.this.onItemRangeChanged(newItems,i,i1);
        }

        @Override
        public void onItemRangeInserted(ObservableArrayList<M> newItems, int i, int i1)
        {
            BaseBindingAdapter.this.onItemRangeInserted(newItems,i,i1);
        }

        @Override
        public void onItemRangeMoved(ObservableArrayList<M> newItems, int i, int i1, int i2)
        {
            BaseBindingAdapter.this.onItemRangeMoved(newItems);
        }

        @Override
        public void onItemRangeRemoved(ObservableArrayList<M> sender, int positionStart, int itemCount)
        {
            BaseBindingAdapter.this.onItemRangeRemoved(sender,positionStart,itemCount);
        }
    }
}

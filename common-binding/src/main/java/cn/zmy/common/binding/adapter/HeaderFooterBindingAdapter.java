package cn.zmy.common.binding.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by zmy on 2016/7/12 0012.
 * 适用于具备Header及Footer的列表
 *
 * <p>
 * 重写 {@link #getHeaderCount()}告诉适配器Header的数目
 *
 * <p>
 * 重写 {@link #getFooterCount()}告诉适配器Footer的数目
 *
 */
public abstract class HeaderFooterBindingAdapter<M> extends BaseBindingAdapter<M,ViewDataBinding>
{
    private final int BASE_HEADER_TYPE = 80000;
    private final int BASE_FOOTER_TYPE = 8000000;

    @Override
    public final int getItemViewType(int position)
    {
        if (position < getHeaderCount())
        {
            return BASE_HEADER_TYPE + position;
        }

        if (position >= items.size() + getHeaderCount())
        {
            return BASE_FOOTER_TYPE + position;
        }

        int type = getItemType(position - getHeaderCount());
        if (type >= BASE_HEADER_TYPE)
        {
            throw new UnsupportedOperationException("item type的值必须小于" + BASE_HEADER_TYPE);
        }

        return type;
    }

    @Override
    public final int getItemCount()
    {
        return items.size() + getHeaderCount() + getFooterCount();
    }

    @Override
    public final BaseBindingViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType)
    {
        ViewDataBinding binding;
        if (viewType < BASE_HEADER_TYPE)
        {
            //item
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getItemLayout(viewType),parent,false);
        }
        else if (viewType < BASE_FOOTER_TYPE)
        {
            //header
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getHeaderLayout(viewType - BASE_HEADER_TYPE),parent,false);
        }
        else
        {
            //footer
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getFooterLayout(viewType - BASE_FOOTER_TYPE),parent,false);
        }
        BaseBindingViewHolder<ViewDataBinding> holder = new BaseBindingViewHolder<>(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public final void onBindViewHolder(BaseBindingViewHolder<ViewDataBinding> holder, int position)
    {
        if (position < getHeaderCount())
        {
            onBindHeader(holder.getBinding(),position);
        }
        else if (position >= items.size() + getHeaderCount())
        {
            onBindFooter(holder.getBinding(),position - items.size() - getHeaderCount());
        }
        else
        {
            onBindItem(holder.getBinding(),items.get(position - getHeaderCount()));
        }
    }

    @Override
    protected final void onItemRangeChanged(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        super.onItemRangeChanged(newItems, positionStart + getHeaderCount(), itemCount);
    }

    @Override
    protected final void onItemRangeInserted(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        resetItems(newItems);
        notifyItemRangeInserted(positionStart + getHeaderCount(),itemCount);
    }

    @Override
    protected final void onItemRangeRemoved(ObservableArrayList<M> newItems, int positionStart, int itemCount)
    {
        super.onItemRangeRemoved(newItems, positionStart + getHeaderCount(), itemCount);
    }

    public int getItemType(int position)
    {
        return super.getItemViewType(position);
    }

    public void notifyHeaderChanged(int position)
    {
        this.notifyItemChanged(position);
    }

    public void notifyFooterChanged(int position)
    {
        this.notifyItemChanged(position + getHeaderCount() + items.size());
    }

    public abstract int getHeaderCount();

    public abstract int getFooterCount();

    public abstract @LayoutRes int getHeaderLayout(int position);

    public abstract @LayoutRes int getFooterLayout(int position);

    public abstract @LayoutRes int getItemLayout(int viewType);

    public abstract void onBindHeader(ViewDataBinding binding,int position);

    public abstract void onBindFooter(ViewDataBinding binding,int position);
}

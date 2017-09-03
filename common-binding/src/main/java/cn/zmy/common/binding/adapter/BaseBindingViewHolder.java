package cn.zmy.common.binding.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zmy on 2017/4/20 0020.
 */

public class BaseBindingViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder
{
    private B binding;
    public BaseBindingViewHolder(View itemView)
    {
        super(itemView);
    }

    public B getBinding()
    {
        return binding;
    }

    public void setBinding(B binding)
    {
        this.binding = binding;
    }
}

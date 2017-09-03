package cn.zmy.common.binding.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by zmy on 2017/4/20 0020.
 */

public abstract class SingleTypeBindingAdapter<M> extends BaseBindingAdapter<M,ViewDataBinding>
{
    @Override
    public final ViewDataBinding onCreateItemBinding(ViewGroup parent, int viewType)
    {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getItemLayout(),parent,false);
    }

    @Override
    public void onBindItem(ViewDataBinding binding, M item)
    {
        binding.setVariable(getModelId(),item);
        binding.executePendingBindings();
    }

    protected abstract @LayoutRes
    int getItemLayout();

    protected abstract int getModelId();
}

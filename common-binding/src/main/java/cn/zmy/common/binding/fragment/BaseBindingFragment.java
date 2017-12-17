package cn.zmy.common.binding.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zmy.common.base.fragment.BaseFragment;


/**
 * Created by zmy on 2017/9/21.
 */

public abstract class BaseBindingFragment<T extends ViewDataBinding> extends BaseFragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        T binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        this.onBindingCreated(binding);
        return binding.getRoot();
    }

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void onBindingCreated(T binding);
}

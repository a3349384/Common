package cn.zmy.common.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import cn.zmy.common.base.ToolbarFragmentActivity;

/**
 * Created by zmy on 2016/3/19 0019.
 */
public abstract class NavigationFragment extends Fragment
{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if (navigationBarRequired())
        {
            setupNavigationBar(getToolbar());
        }
    }

    protected abstract void setupNavigationBar(Toolbar bar);

    protected boolean navigationBarRequired()
    {
        return true;
    }

    public final Toolbar getToolbar()
    {
        ToolbarFragmentActivity activity = (ToolbarFragmentActivity) getActivity();
        if (activity == null){
            return null;
        }
        return activity.getToolbar();
    }

    public void onBackPressed()
    {
        try
        {
            getActivity().finish();
        }
        catch (Exception ignore){}
    }
}

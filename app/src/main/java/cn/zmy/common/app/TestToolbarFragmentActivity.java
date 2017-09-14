package cn.zmy.common.app;

import android.support.v4.app.Fragment;

import cn.zmy.common.base.ToolbarFragmentActivity;

/**
 * Created by zmy on 2017/9/14.
 */

public class TestToolbarFragmentActivity extends ToolbarFragmentActivity
{
    @Override
    protected Fragment onCreateFragment()
    {
        return new Fragment();
    }
}

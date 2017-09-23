package cn.zmy.common.app;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cn.zmy.common.base.IToolbatAware;
import cn.zmy.common.base.ToolbarFragmentActivity;
import cn.zmy.common.utils.ToastUtil;

/**
 * Created by zmy on 2017/9/14.
 */

public class TestToolbarFragmentActivity extends ToolbarFragmentActivity implements IToolbatAware
{
    @Override
    protected Fragment onCreateFragment()
    {
        return new Fragment();
    }

    @Override
    protected Toolbar onCreateToolbar()
    {
        Toolbar toolbar = super.onCreateToolbar();
        toolbar.setBackgroundResource(R.color.colorPrimary);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("测试标题");
        toolbar.inflateMenu(R.menu.menu_test);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                ToastUtil.showShort(TestToolbarFragmentActivity.this, "Clicked");
                return true;
            }
        });
        return toolbar;
    }

    @Override
    public void setToolbar(Toolbar toolbar)
    {

    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }
}

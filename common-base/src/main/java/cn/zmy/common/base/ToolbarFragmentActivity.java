package cn.zmy.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cn.zmy.common.base.fragment.NavigationFragment;
import cn.zmy.common.utils.ViewUtil;

/**
 * Created by zmy on 2016/3/19 0019.
 * 这个Activity上方放置了一个Toolbar
 * 在Toolbar的下方可以放置一个Fragment
 */
public abstract class ToolbarFragmentActivity extends AppCompatActivity
{
    private static final String FRAGMENT_TAG_KEY = "FRAGMENT_TAG_KEY";

    private Toolbar mToolbar;
    private Fragment mFragmentContent;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
        {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        LinearLayout linearLayoutRoot = new LinearLayout(this);
        linearLayoutRoot.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayoutRoot);

        //Toolbar
        mToolbar = this.onCreateToolbar();
        linearLayoutRoot.addView(mToolbar);

        //放置content
        ViewGroup contentContainer = new FrameLayout(this);
        contentContainer.setId(ViewUtil.generateViewId());
        LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
        linearLayoutRoot.addView(contentContainer,contentLayoutParams);

        if (savedInstanceState != null)
        {
            mFragmentContent = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_KEY);
            if (mFragmentContent != null)
            {
                return;
            }
        }
        mFragmentContent = onCreateFragment();
        getSupportFragmentManager().beginTransaction().replace(contentContainer.getId(), mFragmentContent, FRAGMENT_TAG_KEY).commit();

        if (mFragmentContent != null && mFragmentContent instanceof IToolbatAware)
        {
            ((IToolbatAware) mFragmentContent).setToolbar(mToolbar);
        }
    }

    @Override
    public void onBackPressed()
    {
        if (mFragmentContent != null && mFragmentContent instanceof NavigationFragment)
        {
            ((NavigationFragment)mFragmentContent).onBackPressed();
            return;
        }
        super.onBackPressed();
    }

    protected Toolbar onCreateToolbar()
    {
        Toolbar toolbar = new Toolbar(this);
        return toolbar;
    }

    protected abstract Fragment onCreateFragment();

    public Fragment getContentFragment()
    {
        return mFragmentContent;
    }

    public Toolbar getToolbar()
    {
        return mToolbar;
    }
}

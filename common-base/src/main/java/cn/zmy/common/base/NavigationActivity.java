package cn.zmy.common.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import cn.zmy.common.utils.SystemUtil;
import cn.zmy.common.utils.Util;
import cn.zmy.common.utils.ViewUtil;

/**
 * Created by zmy on 2016/3/19 0019.
 */
public abstract class NavigationActivity extends AppCompatActivity
{
    private static final String FRAGMENT_TAG_KEY = "FRAGMENT_TAG_KEY";

    private ViewGroup contentContainer;
    private Toolbar toolbar;
    private String fragmentTag;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
        {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //放置Toolbar
        FrameLayout toolbarContainer = new FrameLayout(this);
        toolbarContainer.setBackgroundColor(Color.RED);
        linearLayout.addView(toolbarContainer,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //放置content
        contentContainer = new FrameLayout(this);
        contentContainer.setId(ViewUtil.generateViewId());
        LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
        linearLayout.addView(contentContainer,contentLayoutParams);
        super.setContentView(linearLayout);
        //初始化Toolbar
        toolbar = new Toolbar(this);
        toolbarContainer.removeAllViews();
        toolbarContainer.addView(toolbar,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,SystemUtil.getActionBarHeight(this)));
        setSupportActionBar(toolbar);

        //解决直接调用toolbar.setTitle无效的问题
        getSupportActionBar().setTitle("");

        if (savedInstanceState == null)
        {
            onSetContentFragment();
        }
        else
        {
            this.fragmentTag = savedInstanceState.getString(FRAGMENT_TAG_KEY);
            if (TextUtils.isEmpty(this.fragmentTag))
            {
                onSetContentFragment();
            }
            else
            {
                restoreContentFragment();
            }
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (!TextUtils.isEmpty(fragmentTag))
        {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
            if (fragment != null && !(fragment instanceof NavigationFragment))
            {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        if (!TextUtils.isEmpty(fragmentTag))
        {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
            if (fragment != null && fragment instanceof NavigationFragment)
            {
                ((NavigationFragment)fragment).onBackPressed();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (!TextUtils.isEmpty(this.fragmentTag))
        {
            outState.putString(FRAGMENT_TAG_KEY,this.fragmentTag);
        }
    }

    protected abstract void onSetContentFragment();

    protected int getContentContainerID()
    {
        return contentContainer.getId();
    }

    protected void setContentFragment(Fragment fragment, @Nullable String tag)
    {
        if (fragment == null)
        {
            return;
        }

        fragmentTag = tag;
        getSupportFragmentManager().beginTransaction().replace(getContentContainerID(),fragment,tag).commit();
    }

    protected void restoreContentFragment()
    {
        Fragment fragment = getContentFragment();
        if (fragment == null)
        {
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
        setContentFragment(fragment,this.fragmentTag);
    }

    public Fragment getContentFragment()
    {
        return getSupportFragmentManager().findFragmentByTag(Util.nullToDefault(fragmentTag));
    }

    public Toolbar getToolbar()
    {
        return toolbar;
    }
}

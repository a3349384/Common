package cn.zmy.common.base.provider;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by zmy on 2017/6/18.
 */

public class CommonEmptyViewProvider implements EmptyViewProvider
{
    private View viewEmpty;
    private ViewGroup viewGroupParent;

    public CommonEmptyViewProvider(Context context, @LayoutRes int layoutId, ViewGroup parent)
    {
        this.viewEmpty = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.viewGroupParent = parent;
        this.setup();
    }

    public CommonEmptyViewProvider(View viewEmpty, ViewGroup parent)
    {
        this.viewEmpty = viewEmpty;
        this.viewGroupParent = parent;
        this.setup();
    }

    @Override
    public void show()
    {
        this.viewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide()
    {
        this.viewEmpty.setVisibility(View.GONE);
    }

    protected void setup()
    {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        this.viewGroupParent.addView(this.viewEmpty, params);
        this.viewEmpty.setVisibility(View.GONE);
    }
}

package cn.zmy.common.base.fragment;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by zmy on 2016/3/19 0019.
 */
public abstract class BaseNavigationFragment extends NavigationFragment
{
    @Override
    protected void setupNavigationBar(Toolbar toolbar)
    {
        int textColor = getToolbarTextColor();
        int backgroundColor = getToolbarBackgroundColor();

        if (showNavigationIcon())
        {
            final Drawable upArrow = getResources().getDrawable(cn.zmy.common.base.R.drawable.ic_back);
            upArrow.setColorFilter(textColor, PorterDuff.Mode.SRC_ATOP);
            toolbar.setNavigationIcon(upArrow);
        }

        final Drawable overFlow = getResources().getDrawable(cn.zmy.common.base.R.drawable.ic_overflow);
        overFlow.setColorFilter(textColor,PorterDuff.Mode.SRC_ATOP);
        toolbar.setOverflowIcon(overFlow);

        toolbar.setBackgroundColor(backgroundColor);
        toolbar.setTitleTextColor(textColor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackClicked();
            }
        });
    }

    protected void onBackClicked()
    {
        getActivity().finish();
    }

    public abstract int getToolbarTextColor();

    public abstract int getToolbarBackgroundColor();

    public abstract boolean showNavigationIcon();
}

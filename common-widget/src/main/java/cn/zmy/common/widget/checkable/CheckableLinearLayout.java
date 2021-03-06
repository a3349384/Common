package cn.zmy.common.widget.checkable;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

import cn.zmy.common.widget.R;

/**
 * Created by zmy on 2017/9/14.
 */

public class CheckableLinearLayout extends LinearLayout implements Checkable
{
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private boolean mChecked;

    public CheckableLinearLayout(Context context)
    {
        this(context, null);
    }

    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CheckableLinearLayout, defStyleAttr, 0);
        setChecked(array.getBoolean(R.styleable.CheckableLinearLayout_CLL_checkable, false));
        array.recycle();
    }

    @Override
    public void setChecked(boolean checked)
    {
        if (mChecked != checked)
        {
            mChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked()
    {
        return mChecked;
    }

    @Override
    public void toggle()
    {
        setChecked(!mChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace)
    {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked())
        {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}

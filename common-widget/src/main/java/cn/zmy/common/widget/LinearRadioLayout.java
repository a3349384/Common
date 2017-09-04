package cn.zmy.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.zmy.common.utils.ViewUtil;

/**
 * Created by zmy on 2017/7/13 0013.
 */

public class LinearRadioLayout extends LinearLayout
{
    private int mCheckedId = -1;
    private boolean mProtectFromCheckedChange = false;
    private LinearRadioLayout.CheckedStateTracker mCheckedStateTracker;
    private LinearRadioLayout.OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private CheckChangeClickListener mCheckChangeClickListener;

    public LinearRadioLayout(Context context)
    {
        this(context, null);
    }

    public LinearRadioLayout(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public LinearRadioLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init()
    {
        mCheckedStateTracker = new CheckedStateTracker();
        mPassThroughListener = new PassThroughHierarchyChangeListener();
        mCheckChangeClickListener = new CheckChangeClickListener();
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }

    private void setCheckedStateForView(int viewId, boolean checked)
    {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof Checkable)
        {
            ((Checkable) checkedView).setChecked(checked);
        }
    }

    private void setCheckedId(@IdRes int id)
    {
        mCheckedId = id;
        if (mOnCheckedChangeListener != null)
        {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
        }
    }

    public void setOnCheckedChangeListener(LinearRadioLayout.OnCheckedChangeListener listener)
    {
        mOnCheckedChangeListener = listener;
    }

    /**
     * 获取选中项的索引
     * */
    public int getIndexOfChecked()
    {
        if (mCheckedId <= 0)
        {
            return -1;
        }

        for (int i= 0; i < this.getChildCount(); i++)
        {
            if (mCheckedId == this.getChildAt(i).getId())
            {
                return i;
            }
        }

        return -1;
    }

    public void check(int index)
    {
        int viewId = getChildAt(index).getId();
        setCheckedStateForView(viewId, true);
    }

    @Override
    protected LinearRadioLayout.LayoutParams generateDefaultLayoutParams()
    {
        LayoutParams params = new LayoutParams(super.generateDefaultLayoutParams());
        params.checkable = false;

        return params;
    }

    @Override
    protected LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
    {
        return new LayoutParams(p);
    }

    @Override
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams
    {
        public boolean checkable;

        public LayoutParams(Context c, AttributeSet attrs)
        {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.View);
            this.checkable = a.getBoolean(R.styleable.View_View_checkable, false);
            a.recycle();
        }

        public LayoutParams(int width, int height)
        {
            super(width, height);
        }

        public LayoutParams(int width, int height, float weight)
        {
            super(width, height, weight);
        }

        public LayoutParams(ViewGroup.LayoutParams p)
        {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source)
        {
            super(source);
        }
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener
    {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            doOnCheckedChanged(buttonView, isChecked);
        }

        public void onOtherCheckedChanged(View view, boolean isChecked)
        {
            doOnCheckedChanged(view, isChecked);
        }

        private void doOnCheckedChanged(View view, boolean isChecked)
        {
            if (mProtectFromCheckedChange)
            {
                return;
            }

            mProtectFromCheckedChange = true;
            if (mCheckedId != -1)
            {
                setCheckedStateForView(mCheckedId, false);
            }
            mProtectFromCheckedChange = false;

            int id = view.getId();
            setCheckedId(id);
        }
    }

    private class PassThroughHierarchyChangeListener implements OnHierarchyChangeListener
    {
        public void onChildViewAdded(View parent, View child)
        {
            if (parent == LinearRadioLayout.this)
            {
                if (child instanceof CompoundButton)
                {
                    generateViewIdIfNotExist(child);
                    setOnCheckedChangeWidgetListener((CompoundButton) child, mCheckedStateTracker);
                    return;
                }
                if (child.getLayoutParams() instanceof LinearRadioLayout.LayoutParams)
                {
                    if (((LayoutParams) child.getLayoutParams()).checkable)
                    {
                        generateViewIdIfNotExist(child);
                        child.setOnClickListener(mCheckChangeClickListener);
                    }
                }
            }
        }

        public void onChildViewRemoved(View parent, View child)
        {
            if (parent == LinearRadioLayout.this)
            {
                if (child instanceof CompoundButton)
                {
                    setOnCheckedChangeWidgetListener((CompoundButton) child, null);
                    return;
                }
                if (child.getLayoutParams() instanceof LinearRadioLayout.LayoutParams)
                {
                    if (((LayoutParams) child.getLayoutParams()).checkable)
                    {
                        child.setOnClickListener(null);
                    }
                }
            }
        }

        private void setOnCheckedChangeWidgetListener(CompoundButton button, CompoundButton.OnCheckedChangeListener listener)
        {
            try
            {
                Method method = CompoundButton.class.getDeclaredMethod("setOnCheckedChangeWidgetListener", CompoundButton.OnCheckedChangeListener.class);
                method.setAccessible(true);
                method.invoke(button, listener);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        private void generateViewIdIfNotExist(View child)
        {
            int id = child.getId();
            if (id == View.NO_ID)
            {
                id = ViewUtil.generateViewId();
                child.setId(id);
            }
        }
    }

    private class CheckChangeClickListener implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if (v.getId() != mCheckedId)
            {
                if (v instanceof Checkable)
                {
                    ((Checkable) v).setChecked(true);
                    mCheckedStateTracker.onOtherCheckedChanged(v, true);
                }
            }
        }
    }

    public interface OnCheckedChangeListener
    {
        void onCheckedChanged(LinearRadioLayout parent, @IdRes int checkedId);
    }
}

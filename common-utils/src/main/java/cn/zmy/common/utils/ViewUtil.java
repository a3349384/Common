package cn.zmy.common.utils;

import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zmy on 2016/5/31 0031.
 */
public class ViewUtil
{
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static <T> T findParent(View child, Class<T> tClass)
    {
        if (tClass.isInstance(child))
        {
            return (T) child;
        }
        while (true)
        {
            ViewParent viewParent = child.getParent();
            if (viewParent == null)
            {
                return null;
            }

            if (!(viewParent instanceof ViewGroup))
            {
                return null;
            }
            ViewGroup parent = (ViewGroup) viewParent;
            if (tClass.isInstance(parent))
            {
                return (T) parent;
            }

            child = parent;
        }
    }

    /**
     * 动态生成View ID
     * API LEVEL 17 以上View.generateViewId()生成
     * API LEVEL 17 以下需要手动生成
     */
    public static int generateViewId()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            for (; ; )
            {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF)
                {
                    newValue = 1; // Roll over to 1, not 0.
                }
                if (sNextGeneratedId.compareAndSet(result, newValue))
                {
                    return result;
                }
            }
        }
        else
        {
            return View.generateViewId();
        }
    }

    public static void setRippleCompact(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs);
            int backgroundResource = typedArray.getResourceId(0, 0);
            view.setBackgroundResource(backgroundResource);
            typedArray.recycle();
        }
    }

    public static void setRippleBorderlessCompact(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
            TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs);
            int backgroundResource = typedArray.getResourceId(0, 0);
            view.setBackgroundResource(backgroundResource);
            typedArray.recycle();
        }
    }

    public static void setMaxLength(TextView textView,int maxLength)
    {
        InputFilter[] filters = textView.getFilters();
        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(maxLength);

        InputFilter[] newFilters = new InputFilter[filters.length + 1];

        for (int i = 0; i < filters.length;i++)
        {
            InputFilter filter = filters[i];
            newFilters[i] = filter;
        }
        newFilters[filters.length] = lengthFilter;
        textView.setFilters(newFilters);
    }
}

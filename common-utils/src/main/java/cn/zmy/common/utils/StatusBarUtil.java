package cn.zmy.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.Window;

/**
 * Created by zmy on 2016/7/26 0026.
 */
public class StatusBarUtil
{
    /**
     * 设置状态栏的背景色
     *
     * @return 是否成功
     */
    public static boolean setBackground(Activity activity, int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            activity.getWindow().setStatusBarColor(color);
            return true;
        }

        return false;
    }

    /**
     * 设置状态栏文字的颜色
     *
     * @param dark true:文字颜色为黑色；false：文字颜色为白色
     */
    public static boolean setTextColor(Activity activity, boolean dark)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            try
            {
                //MIUI需要单独设置，日他大爷的
                if (ThirdOsUtil.isMIUI())
                {
                    ThirdOsUtil.setMiuiStatusBarDarkMode(activity, dark);
                }
                //FlyMe也需要单独设置，日他大爷的
                else if (ThirdOsUtil.isFlyme())
                {
                    ThirdOsUtil.setMeizuStatusBarDarkIcon(activity, dark);
                }
                else
                {
                    View view = activity.findViewById(android.R.id.content);
                    if (view != null)
                    {
                        view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                }

                return true;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public static int getHeight(Context context)
    {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        Window window = ((Activity)context).getWindow();
        if (window != null)
        {
            Rect rectangle = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
            return rectangle.top;
        }
        return 0;
    }
}

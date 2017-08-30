package cn.zmy.common.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zmy on 2016/6/3 0003.
 */
public class SystemUtil
{
    /**
     * 电话号码的最大位数
     */
    public static final int PHONE_NUMBER_MAX_COUNT = 11;

    public static int getActionBarHeight(Context context)
    {
        TypedValue tv = new TypedValue();

        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getApplicationContext().getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    /**
     * 关闭键盘
     */
    public static void closeKeyboard(Activity activity)
    {
        try
        {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getWindow().peekDecorView().getWindowToken(), 0);
        }
        catch (Exception ignored) {}
    }

    public static String getPhoneNumber(Context context)
    {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null)
        {
            return verifyPhoneNumberString(telephonyManager.getLine1Number());
        }
        return "";
    }

    private static String verifyPhoneNumberString(String phoneNumberString)
    {
        if (phoneNumberString == null)
        {
            return "";
        }
        String phoneString = phoneNumberString.replaceAll("[^\\d]", "");
        int phoneStringLength = phoneString.length();

        if (phoneStringLength > PHONE_NUMBER_MAX_COUNT)
        {
            return phoneString.substring(phoneStringLength - PHONE_NUMBER_MAX_COUNT);
        }
        else if (phoneString.length() == PHONE_NUMBER_MAX_COUNT)
        {
            return phoneString;
        }

        return "";
    }
}

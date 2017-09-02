package cn.zmy.common.utils;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by zmy on 2017/9/2.
 */

public class KeyboardUtil
{
    public static void showKeyboard(View editText)
    {
        Context context = editText.getContext().getApplicationContext();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, 0);
    }

    public static void closeKeyboard(Window window)
    {
        View view = window.peekDecorView();
        if (view != null)
        {
            Context context = view.getContext().getApplicationContext();
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

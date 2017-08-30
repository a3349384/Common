package cn.zmy.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by zmy on 2017/4/23.
 */

public class IntentUtil
{
    public static void browser(Context context,String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
}

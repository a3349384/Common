package cn.zmy.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by zmy on 2016/6/3 0003.
 */
public class AppUtil
{
    /**
     * 获取version code
     * @return 获取失败返回0,
     * */
    public static int getVersionCode(Context context)
    {
        try
        {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public static void installApk(Context context,Uri uri)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");

        context.startActivity(intent);
    }

    public static void installApk(Context context,String url)
    {
        installApk(context,Uri.parse(url));
    }
}

package cn.zmy.common.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zmy on 2016/6/7 0007.
 */
public class PermissionUtil
{
    /**
     * 判断当前程序是否缺少指定权限
     * @return true表示缺少某个（些）权限
     */
    public static boolean lackPermissions(Context context, String... permissions)
    {
        for (String permission : permissions)
        {
            if (lackPermission(context, permission))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前程序是否缺少指定权限
     * @return true表示缺少该权限
     */
    public static boolean lackPermission(Context context,String permission)
    {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }

    /**
     * 获取传入参数中缺少的权限
     * */
    public static String[] getLackPermissions(Context context,String... permissions)
    {
        List<String> lackList = new ArrayList<>();
        for (String permission : permissions)
        {
            if (lackPermission(context,permission))
            {
                lackList.add(permission);
            }
        }

        String[] lackArray = new String[lackList.size()];
        return lackList.toArray(lackArray);
    }
}

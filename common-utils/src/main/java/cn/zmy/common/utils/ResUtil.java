package cn.zmy.common.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * Created by zmy on 2016/7/7 0007.
 */
public class ResUtil
{
    public static String getString(Context context, int id)
    {
        return context.getResources().getString(id);
    }

    public static int getColor(Context context, int id)
    {
        return context.getResources().getColor(id);
    }

    public static Drawable getDrawable(Context context, int id)
    {
        return context.getResources().getDrawable(id);
    }

    public static int getDimenPixelSize(Context context, int id)
    {
        return context.getResources().getDimensionPixelSize(id);
    }

    public static final Uri getDrawableUri(Context context, int drawableId)
    {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getResources().getResourcePackageName(drawableId) + '/' + context.getResources().getResourceTypeName(drawableId) + '/' + context.getResources().getResourceEntryName(drawableId));
        return imageUri;
    }
}

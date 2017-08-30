package cn.zmy.common.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by zmy on 2016/7/7 0007.
 */
public class ResUtil
{
    public static String getString(Context context,@StringRes int id)
    {
        return context.getResources().getString(id);
    }

    public static int getColor(Context context,@ColorRes int id)
    {
        return context.getResources().getColor(id);
    }

    public static Drawable getDrawable(Context context,@DrawableRes int id)
    {
        return context.getResources().getDrawable(id);
    }

    public static int getDimenPixelSize(Context context,@DimenRes int id)
    {
        return context.getResources().getDimensionPixelSize(id);
    }

    public static final Uri getDrawableUri(Context context,@AnyRes int drawableId)
    {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                                         context.getResources().getResourcePackageName(drawableId) + '/' +
                                         context.getResources().getResourceTypeName(drawableId) + '/' +
                                         context.getResources().getResourceEntryName(drawableId));
        return imageUri;
    }
}

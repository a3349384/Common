package cn.zmy.common.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zmy on 2017/4/20 0020.
 */

public class BindingAdapters
{
    @BindingAdapter({"imageUrl", "holder"})
    public static void loadImage(ImageView view, String url, Drawable error)
    {
        if (TextUtils.isEmpty(url))
        {
            view.setImageDrawable(error);
            return;
        }
        Glide.with(view.getContext()).load(url).placeholder(error).into(view);
    }
}

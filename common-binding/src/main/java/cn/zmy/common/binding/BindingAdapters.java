package cn.zmy.common.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by zmy on 2017/4/20 0020.
 */

public class BindingAdapters
{
    //region ImageView
    @BindingAdapter({"imageUrl", "holder"})
    public static void loadImage(ImageView imageView, String url, Drawable error)
    {
        if (TextUtils.isEmpty(url))
        {
            imageView.setImageDrawable(error);
            return;
        }
        Glide.with(imageView.getContext()).load(url).placeholder(error).into(imageView);
    }

    @BindingAdapter({"imageLevel"})
    public static void setImageLevel(ImageView imageView, int level)
    {
        imageView.setImageLevel(level);
    }
    //endregion

    //region EditText
    @BindingAdapter({"editorActionListener"})
    public static void setEditorActionListener(EditText editText, final OnEditorActionListener listener)
    {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                listener.onEditorAction(event);
                return true;
            }
        });
    }

    public interface OnEditorActionListener
    {
        void onEditorAction(KeyEvent event);
    }
    //endregion
}

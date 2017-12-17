package cn.zmy.common.interfaces.listlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by zmy on 2017/11/28.
 */

public interface IListLayoutProvider
{
    ViewGroup getRootLayout(Context context);

    RecyclerView getRecyclerView(Context context);
}

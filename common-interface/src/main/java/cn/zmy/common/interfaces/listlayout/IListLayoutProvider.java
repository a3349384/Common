package cn.zmy.common.interfaces.listlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by zmy on 2017/11/28.
 * <p></p>
 * 用于{@link BaseListFragment}中创建相关View的接口
 */

public interface IListLayoutProvider
{
    ViewGroup getRootLayout(Context context);

    RecyclerView getRecyclerView(Context context);
}

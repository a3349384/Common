package cn.zmy.common.base.provider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zmy on 2017/4/30.
 */

public interface RecyclerViewProvider
{
    RecyclerView onCreate(Context context);

    void setAdapter(RecyclerView.Adapter adapter);

    RecyclerView.Adapter getAdapter();
}

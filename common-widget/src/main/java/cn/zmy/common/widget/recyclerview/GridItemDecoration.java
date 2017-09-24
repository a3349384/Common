package cn.zmy.common.widget.recyclerview;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by zmy on 2017/5/3 0003.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration
{
    protected DataProvider dataProvider;

    public GridItemDecoration(int horizontalSpace, int verticalSpace)
    {
        this.dataProvider = new TransparentProvider(horizontalSpace, verticalSpace);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        int position = parent.getChildAdapterPosition(view);
        int column = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();

        //除了最后一列，其他列都存在右侧offset
        int right = (position + 1) % column == 0 ? 0 : this.dataProvider.getHorizontalSpace();
        //除了最后一行，其他行都存在下侧offset
        int itemsCount = parent.getAdapter().getItemCount();
        int rowsCount = (itemsCount - 1) / column + 1;
        int bottom = position >= column * (rowsCount - 1) ? 0 : this.dataProvider.getVerticalSpace();
        outRect.set(0, 0, right, bottom);

        Log.d(String.format("p=【%d】,count=【%d】", position, itemsCount), outRect.toString());
    }

    interface DataProvider
    {
        int getHorizontalSpace();
        int getVerticalSpace();
        Drawable getHorizontalDrawable();
        Drawable getVerticalDrawable();
    }

    class TransparentProvider implements DataProvider
    {
        private int horizontalSpace;
        private int verticalSpace;

        public TransparentProvider(int horizontalSpace, int verticalSpace)
        {
            this.horizontalSpace = horizontalSpace;
            this.verticalSpace = verticalSpace;
        }

        @Override
        public int getHorizontalSpace()
        {
            return this.horizontalSpace;
        }

        @Override
        public int getVerticalSpace()
        {
            return this.verticalSpace;
        }

        @Override
        public Drawable getHorizontalDrawable()
        {
            return null;
        }

        @Override
        public Drawable getVerticalDrawable()
        {
            return null;
        }
    }
}

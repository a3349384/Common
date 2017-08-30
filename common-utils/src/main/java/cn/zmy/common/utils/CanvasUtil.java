package cn.zmy.common.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by zmy on 2017/6/1 0001.
 */

public class CanvasUtil
{
    public static void drawTextCenter(Canvas canvas, Paint paint, String s, Rect targetRect)
    {
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int baseLineY = (int) (targetRect.centerY() - top / 2 - bottom / 2);
        canvas.drawText(s, targetRect.centerX(), baseLineY, paint);
    }
}

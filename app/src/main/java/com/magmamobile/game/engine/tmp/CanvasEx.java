package com.magmamobile.game.engine.tmp;

import android.graphics.*;

public final class CanvasEx extends Canvas
{

    protected static Matrix matrix = new Matrix();
    protected static Rect rcDst = new Rect();
    protected static Rect rcSrc = new Rect();

    public CanvasEx(Bitmap bitmap)
    {
        super(bitmap);
    }

    public void draw(Bitmap bitmap, int i, int j)
    {
        drawBitmap(bitmap, i, j, null);
    }

    public void draw(Bitmap bitmap, int i, int j, int k, float f, Paint paint)
    {
        matrix.reset();
        if(k != 0)
        {
            matrix.postRotate(k);
        }
        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
        if(f != 1.0F)
        {
            matrix.postScale(f, f);
        }
        matrix.postTranslate(i, j);
        drawBitmap(bitmap, matrix, paint);
    }

    public void draw(Bitmap bitmap, int i, int j, int k, int l, Paint paint)
    {
        rcSrc.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        rcDst.set(i, j, i + k, j + l);
        drawBitmap(bitmap, rcSrc, rcDst, paint);
    }

}

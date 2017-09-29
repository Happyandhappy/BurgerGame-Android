package com.magmamobile.game.engine.tmp;

import android.graphics.*;
import com.magmamobile.game.engine.Game;

public class BufferCache
{

    private Bitmap cache;
    private Canvas canvas;
    private Rect dst;
    private Rect rect;

    public BufferCache(int i, int j, int k, int l)
    {
        rect = new Rect(i, j, k, l);
        dst = new Rect(0, 0, k - i, l - j);
        cache = Game.createBitmap(rect.width(), rect.height());
        canvas = new Canvas(cache);
    }

    public void capture()
    {
        canvas.drawBitmap(Game.buffer, rect, dst, null);
    }

    public void redraw()
    {
        Game.mCanvas.drawBitmap(cache, dst, rect, null);
    }
}

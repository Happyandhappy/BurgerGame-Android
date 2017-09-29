package com.magmamobile.game.engine;

import android.graphics.Bitmap;

public final class TiledBackground
{

    private static Bitmap b;
    private static int h;
    private static int i;
    private static int j;
    private static int p;
    private static int q;
    private static int w;
    private static int x;
    private static int y;

    public TiledBackground()
    {
    }

    public static final void onAction()
    {
        x = (1 + x) % w;
        y = (1 + y) % h;
    }

    public static final void onInitialize(int k)
    {
        b = Game.getBitmap(k);
        w = b.getWidth();
        h = b.getHeight();
        q = Game.mBufferWidth + w;
        p = Game.mBufferHeight + h;
        x = 0;
        y = 0;
    }

    public static final void onRender()
    {
        i = 0;
        while(i < q)
        {
            j = 0;
            while(j < p)
            {
                Game.drawBitmap(b, i - x, j - y);
                j += h;
            }
            i += w;
        }

    }

    public static final void onTerminate()
    {
        b = null;
    }
}

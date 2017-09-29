package com.magmamobile.game.engine;

import android.graphics.Paint;

public final class StarfieldBackground
{

    private static final int c = 8;
    private static int height;
    private static int numstars;
    private static Paint paint;
    private static int starx[];
    private static int stary[];
    private static int starz[];
    private static int width;

    public StarfieldBackground()
    {
    }

    public static void onAction()
    {
    }

    public static void onInitialize(int i)
    {
        numstars = i;
        width = Game.mBufferWidth;
        height = Game.mBufferHeight;
        paint = new Paint();
        paint.setColor(0xff000000);
        stary = new int[numstars];
        starx = new int[numstars];
        starz = new int[numstars];
        int j = 0;
        do
        {
            if(j >= numstars)
            {
                return;
            }
            starx[j] = 8 * (int)(Math.random() * (double)width - (double)Game.mBufferCW);
            stary[j] = 8 * (int)(Math.random() * (double)height - (double)Game.mBufferCH);
            starz[j] = j + 2;
            j++;
        } while(true);
    }

    public static void onRender()
    {
        paint.setColor(-1);
        int i = 0;
        do
        {
            if(i >= numstars)
            {
                return;
            }
            int j = starx[i] / starz[i] + Game.mBufferCW;
            int k = stary[i] / starz[i] + Game.mBufferCH;
            int ai[] = starz;
            ai[i] = -1 + ai[i];
            int l = starx[i] / starz[i] + Game.mBufferCW;
            int i1 = stary[i] / starz[i] + Game.mBufferCH;
            if(l < 0 || l > Game.mBufferWidth || i1 < 0 || i1 > Game.mBufferHeight || starz[i] < 2)
            {
                starx[i] = 8 * (int)(Math.random() * (double)width - (double)Game.mBufferCW);
                stary[i] = 8 * (int)(Math.random() * (double)height - (double)Game.mBufferCH);
                starz[i] = 66;
            } else
            {
                int j1 = 255 - 3 * starz[i];
                paint.setColor(0xff000000 | j1 | j1 << 8 | j1 << 16);
                Game.drawLine(j, k, l, i1, paint);
            }
            i++;
        } while(true);
    }

    public static void onTerminate()
    {
        paint = null;
    }
}

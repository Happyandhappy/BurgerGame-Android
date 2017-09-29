package com.magmamobile.game.engine;

public final class ColorUtils
{

    public ColorUtils()
    {
    }

    public static int getRandom()
    {
        return rgb(MathUtils.randomi(255), MathUtils.randomi(255), MathUtils.randomi(255));
    }

    public static int lerp(int i, int j, float f)
    {
        if(f <= 0.0F)
        {
            return i;
        }
        if(f >= 1.0F)
        {
            return j;
        } else
        {
            int k = i & 0xff;
            int l = 0xff & i >> 8;
            int i1 = 0xff & i >> 16;
            int j1 = 0xff & i >> 24;
            int k1 = j & 0xff;
            int l1 = 0xff & j >> 8;
            int i2 = 0xff & j >> 16;
            int j2 = 0xff & j >> 24;
            int k2 = (int)((float)k + f * (float)(k1 - k));
            int l2 = (int)((float)l + f * (float)(l1 - l));
            int i3 = (int)((float)i1 + f * (float)(i2 - i1));
            int j3 = (int)((float)j1 + f * (float)(j2 - j1));
            return k2 & 0xff | (l2 & 0xff) << 8 | (i3 & 0xff) << 16 | (j3 & 0xff) << 24;
        }
    }

    public static int rgb(int i, int j, int k)
    {
        return 0xff000000 | (i & 0xff | (j & 0xff) << 8 | (k & 0xff) << 16);
    }

    public static int rgba(int i, int j, int k, int l)
    {
        return i & 0xff | (j & 0xff) << 8 | (k & 0xff) << 16 | (l & 0xff) << 24;
    }
}

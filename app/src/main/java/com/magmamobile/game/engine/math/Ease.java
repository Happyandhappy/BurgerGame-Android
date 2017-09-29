package com.magmamobile.game.engine.math;


public final class Ease
{

    public Ease()
    {
    }

    public static final float OutElastic(float f, float f1, float f2, float f3, float f4, float f5)
    {
        if(f == 0.0F)
        {
            return f1;
        }
        float f6 = f / f3;
        if(f6 == 1.0F)
        {
            return f1 + f2;
        }
        if(f5 == 0.0F)
        {
            f5 = f3 * 0.3F;
        }
        float f7;
        if(f4 < Math.abs(f2))
        {
            f4 = f2;
            f7 = f5 / 4F;
        } else
        {
            f7 = (float)(((double)f5 / 6.2831853071795862D) * Math.asin(f2 / f4));
        }
        return (float)((double)f4 * Math.pow(2D, -10F * f6) * Math.sin((6.2831853071795862D * (double)(f6 * f3 - f7)) / (double)f5) + (double)f2 + (double)f1);
    }
}

package com.magmamobile.game.engine.interpolation;

public class AccelerateInterpolator
    implements Interpolator
{

    private float doubleFactor;
    private float factor;

    public AccelerateInterpolator()
    {
        factor = 1.0F;
        doubleFactor = 2.0F * factor;
    }

    public AccelerateInterpolator(float f)
    {
        factor = f;
        doubleFactor = 2.0F * f;
    }

    public float getInterpolation(float f)
    {
        if(factor == 1.0F)
        {
            return f * f;
        } else
        {
            return (float)Math.pow(f, doubleFactor);
        }
    }
}

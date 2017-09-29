package com.magmamobile.game.engine.interpolation;

public class DecelerateInterpolator
    implements Interpolator
{

    private float factor;

    public DecelerateInterpolator()
    {
        factor = 1.0F;
    }

    public DecelerateInterpolator(float f)
    {
        factor = f;
    }

    public float getInterpolation(float f)
    {
        if(factor == 1.0F)
        {
            return 1.0F - (1.0F - f) * (1.0F - f);
        } else
        {
            return (float)(1.0D - Math.pow(1.0F - f, 2.0F * factor));
        }
    }
}

package com.magmamobile.game.engine.interpolation;

public class AccelerateDecelerateInterpolator
    implements Interpolator
{

    public AccelerateDecelerateInterpolator()
    {
    }

    public float getInterpolation(float f)
    {
        return 0.5F + (float)(Math.cos(3.1415926535897931D * (double)(1.0F + f)) / 2D);
    }
}

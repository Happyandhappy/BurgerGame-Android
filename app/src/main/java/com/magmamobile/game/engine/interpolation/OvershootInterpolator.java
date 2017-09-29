package com.magmamobile.game.engine.interpolation;

public class OvershootInterpolator
    implements Interpolator
{

    private float tension;

    public OvershootInterpolator()
    {
        tension = 2.0F;
    }

    public OvershootInterpolator(float f)
    {
        tension = f;
    }

    public float getInterpolation(float f)
    {
        float f1 = f - 1.0F;
        return 1.0F + f1 * f1 * (f1 * (1.0F + tension) + tension);
    }
}

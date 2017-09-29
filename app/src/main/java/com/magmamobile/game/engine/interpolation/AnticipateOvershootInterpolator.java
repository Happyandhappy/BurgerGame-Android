package com.magmamobile.game.engine.interpolation;

public class AnticipateOvershootInterpolator
    implements Interpolator
{

    private float tension;

    public AnticipateOvershootInterpolator()
    {
        tension = 3F;
    }

    public AnticipateOvershootInterpolator(float f)
    {
        tension = 1.5F * f;
    }

    private static float a(float f, float f1)
    {
        return f * f * (f * (1.0F + f1) - f1);
    }

    private static float o(float f, float f1)
    {
        return f * f * (f1 + f * (1.0F + f1));
    }

    public float getInterpolation(float f)
    {
        if(f < 0.5F)
        {
            return 0.5F * a(f * 2.0F, tension);
        } else
        {
            return 0.5F * (2.0F + o(f * 2.0F - 2.0F, tension));
        }
    }
}

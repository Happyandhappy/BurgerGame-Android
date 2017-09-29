package com.magmamobile.game.engine.interpolation;

public class AnticipateInterpolator
    implements Interpolator
{

    private float tension;

    public AnticipateInterpolator()
    {
        tension = 2.0F;
    }

    public AnticipateInterpolator(float f)
    {
        tension = f;
    }

    public float getInterpolation(float f)
    {
        return f * f * (f * (1.0F + tension) - tension);
    }
}

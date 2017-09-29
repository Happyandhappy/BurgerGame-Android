package com.magmamobile.game.engine.interpolation;

public final class BounceInterpolator
    implements Interpolator
{

    private float amplitude;
    private boolean bounce;
    private float mass;
    private float phase;
    private float stiffness;

    public BounceInterpolator()
    {
        bounce = false;
        stiffness = 12F;
        amplitude = 1.0F;
        mass = 0.09F;
        phase = 0.0F;
    }

    public float getInterpolation(float f)
    {
        float f1 = (float)(-Math.cos(3.1415926535897931D + (Math.sqrt(stiffness / mass) * (double)f + (double)phase)) * (double)(1.0F - f) * (double)amplitude);
        if(bounce)
        {
            return 1.0F - Math.abs(f1);
        } else
        {
            return 1.0F - f1;
        }
    }
}

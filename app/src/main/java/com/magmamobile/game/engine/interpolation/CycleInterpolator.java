package com.magmamobile.game.engine.interpolation;

public class CycleInterpolator
    implements Interpolator
{

    private int cycles;

    public CycleInterpolator()
    {
        cycles = 2;
    }

    public CycleInterpolator(int i)
    {
        cycles = i;
    }

    public float getInterpolation(float f)
    {
        return (float)Math.abs(Math.sin(3.1415926535897931D * (double)cycles * (double)f));
    }
}

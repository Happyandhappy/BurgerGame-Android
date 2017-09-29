package com.magmamobile.game.engine.tmp.tween;


public class Tweener
{

    protected float factor;
    protected boolean playing;
    protected float speed;

    public Tweener()
    {
        playing = false;
        speed = 0.1F;
        factor = 0.0F;
    }

    public Tweener(float f)
    {
        speed = f;
    }

    public float getFactor()
    {
        return factor;
    }

    public float getSpeed()
    {
        return speed;
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public void setSpeed(float f)
    {
        speed = f;
    }

    public void start()
    {
        factor = 0.0F;
        playing = true;
    }

    public void stop()
    {
        factor = 0.0F;
        playing = false;
    }

    public boolean update()
    {
        boolean flag = playing;
        boolean flag1 = false;
        if(flag)
        {
            if(factor == 1.0F)
            {
                playing = false;
                flag1 = true;
            } else
            {
                factor = factor + speed;
                flag1 = false;
                if(factor >= 1.0F)
                {
                    factor = 1.0F;
                    return false;
                }
            }
        }
        return flag1;
    }
}

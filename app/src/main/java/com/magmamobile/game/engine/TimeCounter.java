package com.magmamobile.game.engine;

import android.os.SystemClock;

public final class TimeCounter
{

    private boolean _next;
    public boolean alt;
    public int count;
    public long current;
    public int delay;
    public long next;
    public int step;

    public TimeCounter()
    {
    }

    public final boolean cycle()
    {
        _next = false;
        current = SystemClock.elapsedRealtime();
        if(current >= next)
        {
            if(step > 0)
            {
                step = -1 + step;
            }
            next = current + (long)delay;
            _next = true;
            boolean flag = alt;
            boolean flag1 = false;
            if(!flag)
            {
                flag1 = true;
            }
            alt = flag1;
        }
        return _next;
    }

    public boolean next()
    {
        return _next;
    }

    public final void start(int i, int j)
    {
        alt = false;
        delay = i;
        count = j;
        step = j;
        current = SystemClock.elapsedRealtime();
        next = current + (long)delay;
    }
}

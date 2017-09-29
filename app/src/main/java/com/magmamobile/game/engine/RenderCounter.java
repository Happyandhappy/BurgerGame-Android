package com.magmamobile.game.engine;

import android.os.SystemClock;

public final class RenderCounter
{

    private static int counter;
    private static int frame;
    private static long nextTime;

    public RenderCounter()
    {
    }

    public static int getCounter()
    {
        return counter;
    }

    public static int getFPS()
    {
        return frame;
    }

    public static long getNextTime()
    {
        return nextTime;
    }

    public static void next()
    {
        counter = 1 + counter;
        long l = SystemClock.elapsedRealtime();
        if(l > nextTime)
        {
            nextTime = 1000L + l;
            frame = counter;
            counter = 0;
        }
    }

    public static void restart()
    {
        nextTime = 1000L + SystemClock.elapsedRealtime();
        counter = 0;
        frame = 0;
    }
}

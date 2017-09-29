package com.magmamobile.game.Burger.game;

import android.os.SystemClock;

public class MenuTimer
{

    private static int flashTimer;
    public static int height;
    private static long pauseClock;
    private static boolean paused;
    public static float percent;
    public static int phase;
    public static int sh;
    private static int timer;
    private static long timerEnd;
    private static long timerPause;
    private static long timerStart;
    private static int timerTotal;
    public static int width;
    public static int x;
    public static int y;

    public MenuTimer()
    {
    }

    public static void clear()
    {
        timerEnd = 0L;
        timerPause = 0L;
        stop();
    }

    public static void init()
    {
        timer = 0;
        timerEnd = 0L;
        timerPause = 0L;
        percent = 1.0F;
        phase = 0;
    }

    public static boolean isFinished()
    {
        return phase == 2;
    }

    public static boolean isPaused()
    {
        return paused;
    }

    public static boolean isRunning()
    {
        return phase != 0;
    }

    public static void onTimer()
    {
        float f = 1.0F;
        timer = (int)((timerEnd + timerPause) - SystemClock.elapsedRealtime());
        percent = f - (float)timer / (float)timerTotal;
        if(timer > 0)
        {
            if(timer <= flashTimer)
            {
                if((float)timer < 0.33F * (float)flashTimer)
                {
                    if((timer / 100) % 2 != 0)
                    {
                        f = percent;
                    }
                    percent = f;
                } else
                {
                    if((timer / 250) % 2 != 0)
                    {
                        f = percent;
                    }
                    percent = f;
                }
            }
            sh = Math.round((float)height * percent);
            return;
        } else
        {
            phase = 2;
            return;
        }
    }

    public static void pause()
    {
        if(!paused)
        {
            paused = true;
            pauseClock = SystemClock.elapsedRealtime();
        }
    }

    public static void resume()
    {
        timerPause += SystemClock.elapsedRealtime() - pauseClock;
        paused = false;
    }

    public static void setTimer(int i)
    {
        timerTotal = i;
    }

    public static void start()
    {
        timerStart = SystemClock.elapsedRealtime();
        timerEnd = timerStart + (long)timerTotal;
        timerPause = 0L;
        flashTimer = timerTotal / 4;
        phase = 1;
    }

    public static void stop()
    {
        phase = 0;
        paused = false;
    }
}

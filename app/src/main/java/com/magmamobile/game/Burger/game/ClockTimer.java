package com.magmamobile.game.Burger.game;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.engine.Game;

public class ClockTimer
{

    private static boolean clock;
    private static boolean paused;
    private static int phase;
    private static long timerPause;
    private static long totalPause;
    private final int RING_COUNT = 40;
    private int count;
    public int cx;
    public int cy;
    private int delay;
    public float dx;
    public float dy;
    private float eHour;
    private float eMin;
    public boolean hammerRing;
    public Matrix mtxHammer;
    public Matrix mtxHour;
    public Matrix mtxMin;
    public Matrix mtxShadHour;
    public Matrix mtxShadMin;
    public int numbers[];
    public int nx;
    public int ny;
    private float p;
    public Paint paint;
    private long rest;
    private boolean ringing;
    private int time;
    private int timer;
    private long timerEnd;
    private long timerStart;
    public int x;
    public int xDots;
    public int xHammer;
    private int xHour;
    private int xMin;
    public int xNum1;
    public int xNum2;
    public int xNum3;
    public int y;
    public int yDots;
    public int yHammer;
    private int yHour;
    private int yMin;
    public int yNum1;
    public int yNum2;
    public int yNum3;

    public ClockTimer()
    {
        numbers = new int[3];
        paint = new Paint();
        paint.setAlpha(128);
        mtxHour = new Matrix();
        mtxMin = new Matrix();
        mtxShadHour = new Matrix();
        mtxShadMin = new Matrix();
        mtxHammer = new Matrix();
    }

    private void convertTime()
    {
        time = 999 + timer;
        numbers[0] = time / 60000;
        rest = time - 60000 * numbers[0];
        numbers[1] = (int)rest / 10000;
        numbers[2] = (int)(rest % 10000L) / 1000;
    }

    public static boolean isPaused()
    {
        return paused;
    }

    public static boolean isRunning()
    {
        return phase != 0;
    }

    private void matrixHandler()
    {
        if(delay <= 0)
        {
            p = 0.0F;
        } else
        {
            p = 1.0F - (float)timer / (float)delay;
        }
        eHour = 300F * p - 90F;
        mtxHour.setTranslate(-(float)xHour, -(float)yHour);
        mtxHour.postRotate(eHour);
        mtxHour.postTranslate(cx, cy);
        mtxShadHour.set(mtxHour);
        mtxShadHour.postTranslate(dx, dy);
        eMin = 3600F * p;
        mtxMin.setTranslate(-(float)xMin, -(float)yMin);
        mtxMin.postRotate(eMin);
        mtxMin.postTranslate(cx, cy);
        mtxShadMin.set(mtxMin);
        mtxShadMin.postTranslate(dx, dy);
    }

    public static void pause()
    {
        if(!paused)
        {
            paused = true;
            timerPause = SystemClock.elapsedRealtime();
            if(clock)
            {
                if(SoundManager.isClockPlaying())
                {
                    SoundManager.pauseClock();
                    return;
                } else
                {
                    clock = false;
                    return;
                }
            }
        }
    }

    public static void resume()
    {
        totalPause += SystemClock.elapsedRealtime() - timerPause;
        if(SoundManager.isClockPaused())
        {
            SoundManager.resumeClock();
        }
        paused = false;
    }

    public void addTime(int i)
    {
label0:
        {
            int j = i;
            if(j + timer > 0x923d8)
            {
                j = 0x923d8 - timer;
            }
            timer = j + timer;
            delay = j + delay;
            timerEnd = timerStart + (long)delay;
            convertTime();
            if(clock)
            {
                if(timer > 10000)
                {
                    break label0;
                }
                clock = true;
                SoundManager.seekClockTo(SoundManager.getClockDuration() - timer);
            }
            return;
        }
        clock = false;
        SoundManager.stopClock();
    }

    public void animate()
    {
        boolean flag = true;
        if(!ringing)
        {
            timer = (int)((timerEnd + totalPause) - SystemClock.elapsedRealtime());
            if(timer <= 0)
            {
                timer = 0;
                phase = 2;
                SoundManager.playSound(25);
            }
            convertTime();
            if(GameManager.gameMode == 0)
            {
                matrixHandler();
            }
            if(timer <= 10500 && !clock)
            {
                clock = flag;
                SoundManager.playClock();
            }
        } else
        if(GameManager.gameMode == 0)
        {
            count = -1 + count;
            if(count % 2 != 0)
            {
                flag = false;
            }
            hammerRing = flag;
            if(count <= 0)
            {
                count = 0;
                ringing = false;
                hammerRing = false;
                return;
            }
        }
    }

    public boolean isDotVisible()
    {
        boolean flag = true;
        if((timer / 500) % 2 != 1 && timer != 0 && isRunning())
        {
            flag = false;
        }
        return flag;
    }

    public boolean isOver()
    {
        return phase == 2;
    }

    public boolean isRinging()
    {
        return ringing;
    }

    public boolean isVisible()
    {
        if(timer == 0)
            return true;
        if(timer > 1000)
        {
            if(timer > 5000)
            {
                if(timer > 10000 || (timer / 500) % 2 == 1)
                    return true;
                return false;
            }
            if((timer / 250) % 2 == 1)
                return true;
            return false;
        }
        if((timer / 100) % 2 != 1)
        {
            return false;
        }
        return true;
    }

    public void offset(int i, int j)
    {
        x = i;
        y = j;
        cx = i + Game.scalei(46);
        cy = j + Game.scalei(36);
        nx = i + Game.scalei(78);
        ny = j;
        xHammer = i + Game.scalei(72);
        yHammer = j + Game.scalei(60);
        xNum1 = i + Game.scalei(84);
        xNum2 = i + Game.scalei(116);
        xNum3 = i + Game.scalei(136);
        xDots = i + Game.scalei(106);
        int k = j + Game.scalei(4);
        yNum3 = k;
        yNum2 = k;
        yNum1 = k;
        yDots = j + Game.scalei(8);
        xHour = Game.scalei(2);
        yHour = Game.scalei(24);
        xMin = Game.scalei(2);
        yMin = Game.scalei(29);
        dx = Game.scalef(2.0F);
        dy = Game.scalef(2.0F);
        mtxHammer.setRotate(-10F, Game.scalef(3F), 0.0F);
        mtxHammer.postTranslate((float)xHammer + Game.scalef(4F), (float)yHammer - Game.scalef(3F));
    }

    public void reset()
    {
        delay = 0;
        timer = 0;
        timerStart = 0L;
        timerEnd = 0L;
        timerPause = 0L;
        totalPause = 0L;
        phase = 0;
        ringing = false;
        hammerRing = false;
        paused = false;
        clock = false;
        SoundManager.stopClock();
    }

    public void ring()
    {
        ringing = true;
        count = 40;
        SoundManager.stopClock();
        VibrationManager.vibrate(1500);
    }

    public void setTimer(int i)
    {
        delay = i * 1000;
        timer = i * 1000;
        if(GameManager.gameMode == 0)
        {
            matrixHandler();
        }
        convertTime();
    }

    public void start()
    {
        if(paused)
        {
            resume();
            return;
        } else
        {
            timerStart = SystemClock.elapsedRealtime();
            timerEnd = timerStart + (long)delay;
            totalPause = 0L;
            timerPause = 0L;
            phase = 1;
            clock = false;
            return;
        }
    }

    public void stop()
    {
        timer = 0;
        phase = 0;
        paused = false;
        SoundManager.stopClock();
    }
}

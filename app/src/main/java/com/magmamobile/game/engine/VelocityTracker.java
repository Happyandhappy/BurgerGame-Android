package com.magmamobile.game.engine;

import android.os.SystemClock;

public final class VelocityTracker
{

    private static final int LONGEST_PAST_TIME = 200;
    private static final int NUM_PAST = 10;
    private static final VelocityTracker mPool[] = new VelocityTracker[1];
    private final long mPastTime[] = new long[10];
    private final float mPastX[] = new float[10];
    private final float mPastY[] = new float[10];
    private float mXVelocity;
    private float mYVelocity;

    private VelocityTracker()
    {
    }

    private void addPoint(float f, float f1, long l)
    {
        int i = -1;
        long al[] = mPastTime;
        int j = 0;
        while(j < 10 && al[j] != 0) {
        	if(al[j] < l - 200L)
            {
                i = j;
            }
            j++;
        }
        if(j == 10 && i < 0)
        {
            i = 0;
        }
        if(i == j)
        {
            i--;
        }
        float af[] = mPastX;
        float af1[] = mPastY;
        if(i >= 0)
        {
            int i1 = i + 1;
            int j1 = -1 + (10 - i);
            System.arraycopy(af, i1, af, 0, j1);
            System.arraycopy(af1, i1, af1, 0, j1);
            System.arraycopy(al, i1, al, 0, j1);
            j -= i + 1;
        }
        af[j] = f;
        af1[j] = f1;
        al[j] = l;
        int k = j + 1;
        if(k < 10)
        {
            al[k] = 0L;
        }
    }

    public static VelocityTracker obtain()
    {
        VelocityTracker avelocitytracker[] = mPool;
        synchronized (avelocitytracker) {
            VelocityTracker velocitytracker = mPool[0];
            if(velocitytracker == null)
            {
                VelocityTracker velocitytracker1 = new VelocityTracker();
                return velocitytracker1;
            }
            velocitytracker.clear();
            return velocitytracker;
        }
    }

    public void addMovement(int i, int j)
    {
        addPoint(i, j, SystemClock.elapsedRealtime());
    }

    public void clear()
    {
        mPastTime[0] = 0L;
    }

    public void computeCurrentVelocity(int i)
    {
        float af[];
        float af1[];
        long al[];
        float f;
        float f1;
        long l;
        float f2;
        float f3;
        int k;
        af = mPastX;
        af1 = mPastY;
        al = mPastTime;
        f = af[0];
        f1 = af1[0];
        l = al[0];
        f2 = 0.0F;
        f3 = 0.0F;
        int j = 0;
        while(j < 10 && al[j] != 0L) 
        {
            j++;
        }
        if(j > 3)
        {
            j--;
        }
        k = 1;
        while(k < j)
        {
            int i1 = (int)(al[k] - l);
            if(i1 != 0)
            {
                float f4 = ((af[k] - f) / (float)i1) * (float)i;
                float f5;
                if(f2 == 0.0F)
                {
                    f2 = f4;
                } else
                {
                    f2 = 0.5F * (f2 + f4);
                }
                f5 = ((af1[k] - f1) / (float)i1) * (float)i;
                if(f3 == 0.0F)
                {
                    f3 = f5;
                } else
                {
                    f3 = 0.5F * (f3 + f5);
                }
            }
            k++;
        }
        mXVelocity = f2;
        mYVelocity = f3;
    }

    public float getXVelocity()
    {
        return mXVelocity;
    }

    public float getYVelocity()
    {
        return mYVelocity;
    }

    public void recycle()
    {
        synchronized(mPool)
        {
            mPool[0] = this;
        }
    }

}

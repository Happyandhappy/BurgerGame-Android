package com.magmamobile.game.engine;

import android.graphics.Paint;
import android.os.SystemClock;

public class BufferedTime
{

    private static final int CMAX = 16;
    private char buffer[];
    private int laststamp;
    private int minutes;
    private Paint paint;
    private String prefix;
    private int seconds;
    private int stamp;
    private long start;
    private String suffix;
    private long time;
    private String tostring;
    private float width;

    public BufferedTime()
    {
        buffer = new char[16];
    }

    public void capture()
    {
        time = SystemClock.elapsedRealtime() - start;
        stamp = (int)(time / 1000L);
        if(laststamp != stamp || tostring == null)
        {
            seconds = stamp % 60;
            minutes = (stamp / 60) % 60;
            laststamp = stamp;
            int j;
            int k;
            float f;
            if(seconds > 9)
            {
                buffer[15] = (char)(48 + seconds % 10);
                int k1 = 1 + 1;
                buffer[14] = (char)(48 + (seconds / 10) % 10);
                j = k1 + 1;
            } else
            {
                buffer[15] = (char)(48 + seconds);
                int i = 1 + 1;
                buffer[14] = '0';
                j = i + 1;
            }
            buffer[13] = ':';
            k = j + 1;
            if(minutes == 0)
            {
                buffer[12] = '0';
                int j1 = k + 1;
                buffer[11] = '0';
                k = j1 + 1;
            } else
            if(minutes < 10)
            {
                buffer[12] = (char)(48 + minutes);
                int i1 = k + 1;
                buffer[11] = '0';
                k = i1 + 1;
            } else
            {
                int l = minutes;
                while(l > 0) 
                {
                    buffer[16 - k] = (char)(48 + l % 10);
                    k++;
                    l /= 10;
                }
            }
            tostring = String.copyValueOf(buffer, 1 + (16 - k), k - 1);
            if(prefix != null)
            {
                tostring = (new StringBuilder(String.valueOf(prefix))).append(tostring).toString();
            }
            if(suffix != null)
            {
                tostring = (new StringBuilder(String.valueOf(tostring))).append(suffix).toString();
            }
            if(paint != null)
            {
                f = paint.measureText(tostring);
            } else
            {
                f = 0.0F;
            }
            width = f;
        }
    }

    public void fromNow()
    {
        start = SystemClock.elapsedRealtime();
        capture();
    }

    public long getMinutes()
    {
        return (long)minutes;
    }

    public Paint getPaint()
    {
        return paint;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public long getSeconds()
    {
        return (long)seconds;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public long getTime()
    {
        return time;
    }

    public float getWidth()
    {
        return width;
    }

    public void setPaint(Paint paint1)
    {
        paint = paint1;
    }

    public void setPrefix(String s)
    {
        prefix = s;
        tostring = null;
    }

    public void setSuffix(String s)
    {
        suffix = s;
        tostring = null;
    }

    public String toString()
    {
        return tostring;
    }
}

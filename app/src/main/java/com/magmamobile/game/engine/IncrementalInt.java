package com.magmamobile.game.engine;

import android.graphics.Paint;

public class IncrementalInt
{

    private int format;
    private int last;
    private Paint paint;
    private String prefix;
    private int seed;
    private String string;
    private String suffix;
    private int value;
    private float width;

    public IncrementalInt()
    {
    }

    public IncrementalInt(String s)
    {
        this(s, null);
    }

    public IncrementalInt(String s, String s1)
    {
        prefix = s;
        suffix = s1;
    }

    public void add(int i)
    {
        seed = i + seed;
    }

    public int getFormat()
    {
        return format;
    }

    public Paint getPaint()
    {
        return paint;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public int getValue()
    {
        return value;
    }

    public float getWidth()
    {
        return width;
    }

    public void onAction()
    {
        int i;
label0:
        {
label1:
            {
                if(seed != 0)
                {
                    i = Math.abs(seed);
                    if(i <= 100)
                    {
                        break label0;
                    }
                    if(seed <= 0)
                    {
                        break label1;
                    }
                    value = 100 + value;
                    seed = -100 + seed;
                }
                return;
            }
            value = -100 + value;
            seed = 100 + seed;
            return;
        }
        if(i > 10)
        {
            if(seed > 0)
            {
                value = 10 + value;
                seed = -10 + seed;
                return;
            } else
            {
                value = -10 + value;
                seed = 10 + seed;
                return;
            }
        }
        if(seed > 0)
        {
            value = 1 + value;
            seed = -1 + seed;
            return;
        } else
        {
            value = -1 + value;
            seed = 1 + seed;
            return;
        }
    }

    public void realize()
    {
        value = value + seed;
        seed = 0;
    }

    public void setFormat(int i)
    {
        format = i;
    }

    public void setPaint(Paint paint1)
    {
        paint = paint1;
    }

    public void setPrefix(String s)
    {
        prefix = s;
    }

    public void setSuffix(String s)
    {
        suffix = s;
    }

    public void setValue(int i)
    {
        value = i;
    }

    public String toString()
    {
        if(value != last || string == null)
        {
            last = value;
            float f;
            if(prefix != null && suffix != null)
            {
                string = (new StringBuilder(String.valueOf(prefix))).append(String.valueOf(value)).append(suffix).toString();
            } else
            if(prefix != null)
            {
                string = (new StringBuilder(String.valueOf(prefix))).append(String.valueOf(value)).toString();
            } else
            if(suffix != null)
            {
                string = (new StringBuilder(String.valueOf(String.valueOf(value)))).append(suffix).toString();
            } else
            {
                string = String.valueOf(value);
            }
            if(paint != null)
            {
                f = paint.measureText(string);
            } else
            {
                f = 0.0F;
            }
            width = f;
        }
        return string;
    }
}

package com.magmamobile.game.engine;

import android.graphics.Paint;

public class BufferedInt
{

    public static final int FORMAT_STANDARD = 0;
    public static final int FORMAT_TIME_MM_SS = 1;
    public static final int FORMAT_TIME_M_SS = 2;
    private int format;
    private int last;
    private Paint paint;
    private String prefix;
    private String string;
    private String suffix;
    private int value;
    private float width;

    public BufferedInt()
    {
        format = 0;
    }

    public BufferedInt(String s)
    {
        this(s, 0);
    }

    public BufferedInt(String s, int i)
    {
        this(s, null, i);
    }

    public BufferedInt(String s, String s1, int i)
    {
        prefix = s;
        suffix = s1;
        format = i;
    }

    public void add(int i)
    {
        value = i + value;
    }

    public void dec()
    {
        value = -1 + value;
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

    public void inc()
    {
        value = 1 + value;
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
            if(format == 0)
            {
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
            } else
            if(format == 1)
            {
                int k = value % 60;
                int l = (value / 60) % 60;
                String s2;
                StringBuilder stringbuilder1;
                String s3;
                if(l > 9)
                {
                    s2 = (new StringBuilder()).append(l).toString();
                } else
                {
                    s2 = (new StringBuilder("0")).append(l).toString();
                }
                stringbuilder1 = (new StringBuilder(String.valueOf(s2))).append(":");
                if(k > 9)
                {
                    s3 = (new StringBuilder()).append(k).toString();
                } else
                {
                    s3 = (new StringBuilder("0")).append(k).toString();
                }
                string = stringbuilder1.append(s3).toString();
                if(prefix != null)
                {
                    string = (new StringBuilder(String.valueOf(prefix))).append(string).toString();
                }
                if(suffix != null)
                {
                    string = (new StringBuilder(String.valueOf(string))).append(suffix).toString();
                }
            } else
            if(format == 2)
            {
                int i = value % 60;
                int j = (value / 60) % 60;
                String s;
                StringBuilder stringbuilder;
                String s1;
                if(j > 9)
                {
                    s = (new StringBuilder()).append(j).toString();
                } else
                {
                    s = (new StringBuilder()).append(j).toString();
                }
                stringbuilder = (new StringBuilder(String.valueOf(s))).append(":");
                if(i > 9)
                {
                    s1 = (new StringBuilder()).append(i).toString();
                } else
                {
                    s1 = (new StringBuilder("0")).append(i).toString();
                }
                string = stringbuilder.append(s1).toString();
                if(prefix != null)
                {
                    string = (new StringBuilder(String.valueOf(prefix))).append(string).toString();
                }
                if(suffix != null)
                {
                    string = (new StringBuilder(String.valueOf(string))).append(suffix).toString();
                }
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

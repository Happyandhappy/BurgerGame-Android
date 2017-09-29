package com.magmamobile.game.engine;

import android.graphics.Paint;
import android.graphics.Rect;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TextUtils
{

    private static final int CMAX = 16;
    private static Rect _bounds;
    private static char chars[];

    public TextUtils()
    {
    }

    private static final String additif(int i, char c, char c1)
    {
        switch(i)
        {
        default:
            return "";

        case 1: // '\001'
            return (new StringBuilder()).append(c).toString();

        case 2: // '\002'
            return (new StringBuilder()).append(c).append(c).toString();

        case 3: // '\003'
            return (new StringBuilder()).append(c).append(c).append(c).toString();

        case 4: // '\004'
            return (new StringBuilder()).append(c).append(c).append(c).append(c).toString();

        case 5: // '\005'
            return (new StringBuilder()).append(c1).toString();

        case 6: // '\006'
            return (new StringBuilder()).append(c1).append(c).toString();

        case 7: // '\007'
            return (new StringBuilder()).append(c1).append(c).append(c).toString();

        case 8: // '\b'
            return (new StringBuilder()).append(c1).append(c).append(c).append(c).toString();

        case 9: // '\t'
            return (new StringBuilder()).append(c1).append(c).append(c).append(c).append(c).toString();
        }
    }

    public static final String format00(int i)
    {
        if(i < 0)
        {
            if(i < 9)
            {
                return (new StringBuilder("-")).append(-i).toString();
            } else
            {
                return (new StringBuilder("-0")).append(-i).toString();
            }
        }
        if(i > 9)
        {
            return (new StringBuilder()).append(i).toString();
        } else
        {
            return (new StringBuilder("0")).append(i).toString();
        }
    }

    public static String formatDist(long l)
    {
        if(chars == null)
        {
            chars = new char[16];
        }
        long l1 = Math.abs(l);
        chars[15] = 'm';
        int i = 1 + 1;
        if(l1 == 0L)
        {
            chars[14] = '-';
            i++;
        } else
        {
            for(; l1 > 0L; l1 /= 10L)
            {
                chars[16 - i] = (char)(int)(48L + l1 % 10L);
                i++;
            }

        }
        return String.copyValueOf(chars, 1 + (16 - i), i - 1);
    }

    public static String formatSpeed(float f)
    {
        if(chars == null)
        {
            chars = new char[16];
        }
        long l = Math.abs((int)f);
        chars[15] = 'h';
        int i = 1 + 1;
        chars[14] = '/';
        int j = i + 1;
        chars[13] = 'm';
        int k = j + 1;
        chars[12] = 'k';
        int i1 = k + 1;
        if(l == 0L)
        {
            chars[11] = '-';
            i1++;
        } else
        {
            for(; l > 0L; l /= 10L)
            {
                chars[16 - i1] = (char)(int)(48L + l % 10L);
                i1++;
            }

        }
        return String.copyValueOf(chars, 1 + (16 - i1), i1 - 1);
    }

    public static String formatTime(long l)
    {
        if(chars == null)
        {
            chars = new char[16];
        }
        long l1 = Math.abs(l / 10L);
        chars[15] = 's';
        int i = 1 + 1;
        if(l1 == 0L)
        {
            chars[14] = '-';
            i++;
        } else
        {
            for(; l1 > 0L; l1 /= 10L)
            {
                chars[16 - i] = (char)(int)(48L + l1 % 10L);
                if(++i == 4)
                {
                    chars[16 - i] = '.';
                    i++;
                }
            }

        }
        return String.copyValueOf(chars, 1 + (16 - i), i - 1);
    }

    public static String formatmmss(long l)
    {
        int k;
        int k1;
        if(chars == null)
        {
            chars = new char[16];
        }
        if(l < 0L)
        {
            l = -l;
        }
        int i = (int)(l / 1000L);
        int j = i % 60;
        k = (i / 60) % 60;
        int j1;
        int j2;
        if(j > 9)
        {
            chars[15] = (char)(48 + j % 10);
            int k2 = 1 + 1;
            chars[14] = (char)(48 + (j / 10) % 10);
            j1 = k2 + 1;
        } else
        {
            chars[15] = (char)(j + 48);
            int i1 = 1 + 1;
            chars[14] = '0';
            j1 = i1 + 1;
        }
        chars[13] = ':';
        k1 = j1 + 1;
        if(k == 0) {
            chars[12] = '0';
            j2 = k1 + 1;
            chars[11] = '0';
            k1 = j2 + 1;
        }else if(k >= 10){
            int l1 = k;
            while(l1 > 0)
            {
                chars[16 - k1] = (char)(48 + l1 % 10);
                if(++k1 == 4)
                {
                    chars[16 - k1] = '.';
                    k1++;
                }
                l1 /= 10;
            }
        } else {
            chars[12] = (char) (k + 48);
            int i2 = k1 + 1;
            chars[11] = '0';
            k1 = i2 + 1;
        }
        return String.copyValueOf(chars, 1 + (16 - k1), k1 - 1);
    }

    public static int getBottomLine(String s, Paint paint)
    {
        if(_bounds == null)
        {
            _bounds = new Rect();
        }
        paint.getTextBounds(s, 0, s.length(), _bounds);
        return _bounds.top;
    }

    public static String getFormatTime(long l)
    {
        int i = (int)(l / 1000L);
        int j = i % 60;
        int k = i / 60;
        String s;
        StringBuilder stringbuilder;
        String s1;
        if(k < 10)
        {
            s = (new StringBuilder("0")).append(k).toString();
        } else
        {
            s = (new StringBuilder()).append(k).toString();
        }
        stringbuilder = (new StringBuilder(String.valueOf(s))).append(":");
        if(j < 10)
        {
            s1 = (new StringBuilder("0")).append(j).toString();
        } else
        {
            s1 = (new StringBuilder()).append(j).toString();
        }
        return stringbuilder.append(s1).toString();
    }

    public static final String getNowDate()
    {
        return (new SimpleDateFormat("yyyy/MM/dd")).format(new Date());
    }

    public static final String getNowDateTime()
    {
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date());
    }

    public static final String getNowTime()
    {
        return (new SimpleDateFormat("HH:mm:ss")).format(new Date());
    }

    public static int getTextHeight(String s, Paint paint)
    {
        if(_bounds == null)
        {
            _bounds = new Rect();
        }
        paint.getTextBounds(s, 0, s.length(), _bounds);
        return _bounds.bottom - _bounds.top;
    }

    public static int getTextWidth(String s, Paint paint)
    {
        if(_bounds == null)
        {
            _bounds = new Rect();
        }
        paint.getTextBounds(s, 0, s.length(), _bounds);
        return _bounds.right - _bounds.left;
    }

    public static String int2String000(int i)
    {
        String s = String.valueOf(i);
        return "000".substring(0, "000".length() - s.length()).concat(s);
    }

    public static final boolean isEqual(String s, String s1)
    {
        if(s == null)
            return s1 == null;
        else
            return s.equals(s1);
    }

    public static final boolean isNullOrEmpty(String s)
    {
        while(s == null || s.length() <= 0) 
        {
            return true;
        }
        return false;
    }

    public static final String limit(String s, int i)
    {
        return limit(s, i, "...");
    }

    public static final String limit(String s, int i, String s1)
    {
        if(s == null)
        {
            s = null;
        } else
        if(s.length() > i)
        {
            return s.substring(0, i).concat(s1);
        }
        return s;
    }

    private static final String soustractif(int i, char c, char c1, char c2)
    {
        switch(i)
        {
        default:
            return "";

        case 1: // '\001'
            return (new StringBuilder()).append(c).toString();

        case 2: // '\002'
            return (new StringBuilder()).append(c).append(c).toString();

        case 3: // '\003'
            return (new StringBuilder()).append(c).append(c).append(c).toString();

        case 4: // '\004'
            return (new StringBuilder()).append(c).append(c1).toString();

        case 5: // '\005'
            return (new StringBuilder()).append(c1).toString();

        case 6: // '\006'
            return (new StringBuilder()).append(c1).append(c).toString();

        case 7: // '\007'
            return (new StringBuilder()).append(c1).append(c).append(c).toString();

        case 8: // '\b'
            return (new StringBuilder()).append(c1).append(c).append(c).append(c).toString();

        case 9: // '\t'
            return (new StringBuilder()).append(c).append(c2).toString();
        }
    }

    public static final String toRoman(int i, boolean flag)
    {
        if(i >= 1 && i <= 4999)
        {
            int j = i % 10;
            int k = (i / 10) % 10;
            int l = (i / 100) % 10;
            int i1 = (i / 1000) % 1000;
            if(flag)
            {
                return (new StringBuilder(String.valueOf(additif(i1, 'M', '?')))).append(additif(l, 'C', 'D')).append(additif(k, 'X', 'L')).append(additif(j, 'I', 'V')).toString();
            } else
            {
                return (new StringBuilder(String.valueOf(soustractif(i1, 'M', '?', '?')))).append(soustractif(l, 'C', 'D', 'M')).append(soustractif(k, 'X', 'L', 'C')).append(soustractif(j, 'I', 'V', 'X')).toString();
            }
        } else
        {
            return "";
        }
    }
}

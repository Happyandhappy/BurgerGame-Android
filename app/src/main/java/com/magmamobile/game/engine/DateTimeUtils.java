package com.magmamobile.game.engine;


public final class DateTimeUtils
{

    public DateTimeUtils()
    {
    }

    public static final long MakeTime(int i, int j, int k, int l)
    {
        return (long)(l * 1000 + 60000 * k + 0x36ee80 * j + 0x5265c00 * i);
    }

    public static final long getDays(long l)
    {
        return l / 0x5265c00L;
    }

    public static final long getHours(long l)
    {
        return l / 0x36ee80L;
    }

    public static final long getMinutes(long l)
    {
        return l / 60000L;
    }

    public static final long getSeconds(long l)
    {
        return l / 1000L;
    }
}

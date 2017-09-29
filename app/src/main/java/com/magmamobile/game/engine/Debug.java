package com.magmamobile.game.engine;

import android.graphics.Paint;
import android.util.Log;

public final class Debug
{

    public static final boolean DEBUG_ACTIVITY = false;
    public static final boolean DEBUG_ADS = false;
    public static final boolean DEBUG_APPLICATION = false;
    public static final boolean DEBUG_ATLASMANAGER = false;
    public static final boolean DEBUG_ENGINE = false;
    public static final boolean DEBUG_LAYER = false;
    public static final boolean DEBUG_LAYERMANAGER = false;
    public static final boolean DEBUG_MORE_STACK = false;
    public static final boolean DEBUG_MUSIC = false;
    public static final boolean DEBUG_NODE = false;
    public static final boolean DEBUG_RENDERER = false;
    public static final boolean DEBUG_REPORT = false;
    public static final boolean DEBUG_SENSOR = false;
    public static final boolean DEBUG_SOUND = false;
    public static final boolean DEBUG_SURFACE = false;
    public static final boolean DEBUG_TEXT = false;
    public static final boolean DEBUG_TRACKER = false;
    private static final String LOG_NAME = "-= Engine =-";
    public static Paint focusRect;
    public static Paint labelPaint;

    private Debug()
    {
    }

    public static final void d(String s)
    {
        Log.d("-= Engine =-", (new StringBuilder(String.valueOf(getInfo()))).append(s).toString());
    }

    public static final void e(String s)
    {
        Log.e("-= Engine =-", (new StringBuilder(String.valueOf(getInfo()))).append(s).toString());
    }

    private static final String getInfo()
    {
        StackTraceElement stacktraceelement = Thread.currentThread().getStackTrace()[4];
        String s = stacktraceelement.getFileName();
        if(s == null)
        {
            return "";
        } else
        {
            return (new StringBuilder("[")).append(s).append(":").append(stacktraceelement.getLineNumber()).append("] ").toString();
        }
    }

    public static final void i(String s)
    {
        Log.i("-= Engine =-", (new StringBuilder(String.valueOf(getInfo()))).append(s).toString());
    }

    public static final void v(String s)
    {
        Log.v("-= Engine =-", (new StringBuilder(String.valueOf(getInfo()))).append(s).toString());
    }

    public static final void w(String s)
    {
        Log.w("-= Engine =-", (new StringBuilder(String.valueOf(getInfo()))).append(s).toString());
    }

    static 
    {
        labelPaint = new Paint();
        labelPaint.setColor(0xff0000ff);
        labelPaint.setStyle(android.graphics.Paint.Style.STROKE);
        labelPaint.setStrokeWidth(1.0F);
        focusRect = new Paint();
        focusRect.setColor(0x80ff00ff);
    }
}

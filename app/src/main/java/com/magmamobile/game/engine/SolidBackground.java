package com.magmamobile.game.engine;

import android.graphics.Paint;

public final class SolidBackground
{

    private static Paint _pn;

    public SolidBackground()
    {
    }

    public static int getColor()
    {
        return _pn.getColor();
    }

    public static void onAction()
    {
    }

    public static void onInitialize(int i)
    {
        _pn = new Paint();
        _pn.setColor(i);
    }

    public static void onRender()
    {
        Game.drawPaint(_pn);
    }

    public static void onTerminate()
    {
        _pn = null;
    }

    public static void setColor(int i)
    {
        _pn.setColor(i);
    }
}

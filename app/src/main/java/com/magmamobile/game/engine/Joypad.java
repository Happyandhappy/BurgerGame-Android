package com.magmamobile.game.engine;

import android.view.MotionEvent;

public abstract class Joypad
{

    protected int p;
    protected int r;
    protected boolean v;
    protected int x;
    protected int y;

    public Joypad()
    {
        v = true;
    }

    public Joypad(int i, int j, int k)
    {
        v = true;
        x = i;
        y = j;
        r = k;
    }

    protected void JoyDown(int i, int j, MotionEvent motionevent)
    {
    }

    protected void JoyMove(int i, int j, MotionEvent motionevent)
    {
    }

    protected void JoyUp(int i, int j, MotionEvent motionevent)
    {
    }

    public int getRadius()
    {
        return r;
    }

    protected boolean hasPointerOnIt(MotionEvent motionevent)
    {
        if(Game.androidSDKVersion >= 5)
        {
            return JoypadUtils_API5.hasPointerOnIt(this, motionevent);
        } else
        {
            return JoypadUtils_API4.hasPointerOnIt(this, motionevent);
        }
    }

    protected boolean hit(int i, int j)
    {
        return i >= x - r && j >= y - r && i <= x + r && j <= y + r;
    }

    public boolean isPressed()
    {
        return p > 0;
    }

    public void onRender()
    {
    }
}

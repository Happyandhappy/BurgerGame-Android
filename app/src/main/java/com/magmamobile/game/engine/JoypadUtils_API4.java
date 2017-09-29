package com.magmamobile.game.engine;

import android.view.MotionEvent;
import java.util.ArrayList;

public final class JoypadUtils_API4
{

    private static ArrayList joypads;

    public JoypadUtils_API4()
    {
    }

    static final boolean hasPointerOnIt(Joypad joypad, MotionEvent motionevent)
    {
        return false;
    }

    static final void onJoypadClear()
    {
        if(joypads == null)
        {
            return;
        } else
        {
            joypads.clear();
            return;
        }
    }

    static final void onJoypadDown(int i, int j, MotionEvent motionevent)
    {
        if(joypads != null)
        {
            int k = joypads.size();
            int l = 0;
            while(l < k) 
            {
                ((Joypad)joypads.get(l)).JoyDown(i, j, motionevent);
                l++;
            }
        }
    }

    static final boolean onJoypadEvent(MotionEvent motionevent)
    {
        if(joypads != null && joypads.size() > 0)
        {
            int i = motionevent.getAction();
            if(i == 0)
            {
                onJoypadDown(TouchScreen.ScreenToBufferX(motionevent.getX()), TouchScreen.ScreenToBufferY(motionevent.getY()), motionevent);
                return true;
            }
            if(i == 2)
            {
                onJoypadMove(TouchScreen.ScreenToBufferX(motionevent.getX()), TouchScreen.ScreenToBufferY(motionevent.getY()), motionevent);
                return true;
            }
            if(i == 1 || i == 3 || i == 4)
            {
                onJoypadUp(TouchScreen.ScreenToBufferX(motionevent.getX()), TouchScreen.ScreenToBufferY(motionevent.getY()), motionevent);
                return true;
            }
        }
        return false;
    }

    static final void onJoypadInitialize()
    {
        joypads = new ArrayList();
    }

    static final void onJoypadMove(int i, int j, MotionEvent motionevent)
    {
        if(joypads != null)
        {
            int k = joypads.size();
            int l = 0;
            while(l < k) 
            {
                ((Joypad)joypads.get(l)).JoyMove(i, j, motionevent);
                l++;
            }
        }
    }

    static final void onJoypadSleep()
    {
        try
        {
            Thread.sleep(16L);
            return;
        }
        catch(InterruptedException interruptedexception)
        {
            return;
        }
    }

    static final void onJoypadUp(int i, int j, MotionEvent motionevent)
    {
        if(joypads != null)
        {
            int k = joypads.size();
            int l = 0;
            while(l < k) 
            {
                ((Joypad)joypads.get(l)).JoyUp(i, j, motionevent);
                l++;
            }
        }
    }

    static final void registerJoypad(Joypad joypad)
    {
        if(joypads == null)
        {
            return;
        } else
        {
            joypads.add(joypad);
            return;
        }
    }

    static final void unregisterJoypad(Joypad joypad)
    {
        if(joypads == null)
        {
            return;
        } else
        {
            joypads.remove(joypad);
            return;
        }
    }
}

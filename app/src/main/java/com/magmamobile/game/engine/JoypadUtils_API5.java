package com.magmamobile.game.engine;

import android.view.MotionEvent;
import java.util.ArrayList;

public final class JoypadUtils_API5
{

    private static ArrayList joypads;

    public JoypadUtils_API5()
    {
    }

    static final boolean hasPointerOnIt(Joypad joypad, MotionEvent motionevent)
    {
        int i = 0;
        do
        {
            if(i >= motionevent.getPointerCount())
            {
                return false;
            }
            if(joypad.hit((int)(motionevent.getX(i) * Game.mMulRatioX), (int)(motionevent.getY(i) * Game.mMulRatioY)))
            {
                return true;
            }
            i++;
        } while(true);
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
            int j = i & 0xff;
            int k = (0xff00 & i) >> 8;
            if(j == 0)
            {
                onJoypadDown(TouchScreen.ScreenToBufferX(motionevent.getX()), TouchScreen.ScreenToBufferY(motionevent.getY()), motionevent);
                return true;
            }
            if(j == 5)
            {
                onJoypadDown(TouchScreen.ScreenToBufferX(motionevent.getX(k)), TouchScreen.ScreenToBufferY(motionevent.getY(k)), motionevent);
                return true;
            }
            if(j == 2)
            {
                int l = 0;
                do
                {
                    if(l >= motionevent.getPointerCount())
                    {
                        return true;
                    }
                    onJoypadMove(TouchScreen.ScreenToBufferX(motionevent.getX(l)), TouchScreen.ScreenToBufferY(motionevent.getY(l)), motionevent);
                    l++;
                } while(true);
            }
            if(j == 6)
            {
                onJoypadUp(TouchScreen.ScreenToBufferX(motionevent.getX(k)), TouchScreen.ScreenToBufferY(motionevent.getY(k)), motionevent);
                return true;
            }
            if(j == 1 || j == 3 || j == 4)
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

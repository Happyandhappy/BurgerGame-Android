package com.magmamobile.game.engine;

import android.view.MotionEvent;
import com.magmamobile.game.engine.ex.BaseEntity;
import com.magmamobile.game.engine.ex.Vector2D;

public final class TouchScreen
{

    public static boolean eventDown;
    public static boolean eventMoving;
    public static boolean eventUp;
    public static boolean pressed;
    public static long pt;
    public static int px;
    public static int py;
    public static int x;
    public static int y;

    public TouchScreen()
    {
    }

    public static int BufferToScreenX(int i)
    {
        return (int)((float)i * Game.mDivRatioX);
    }

    public static int BufferToScreenY(int i)
    {
        return (int)((float)i * Game.mDivRatioY);
    }

    public static int ScreenToBufferX(float f)
    {
        return (int)(f * Game.mMulRatioX);
    }

    public static int ScreenToBufferY(float f)
    {
        return (int)(f * Game.mMulRatioY);
    }

    protected static void clear()
    {
        eventMoving = false;
        eventDown = false;
        eventUp = false;
    }

    protected static boolean ev(MotionEvent motionevent)
    {
        Game.onJoypadEvent(motionevent);
        switch(motionevent.getAction()) {
            default:
                break;
            case 0:
                x = (int)(motionevent.getX() * Game.mMulRatioX);
                y = (int)(motionevent.getY() * Game.mMulRatioY);
                px = x;
                py = y;
                pt = Game.tick;
                pressed = true;
                eventDown = true;
                if(Game._touchmode == 1)
                {
                    Game.getCurrentStage().onTouchEvent(1, x, y);
                }
                break;
            case 1:case 3:case 4:
                pressed = false;
                eventDown = false;
                eventMoving = false;
                eventUp = true;
                if(Game._touchmode == 1)
                {
                    Game.getCurrentStage().onTouchEvent(3, x, y);
                }
                break;
            case 2:
                x = (int)(motionevent.getX() * Game.mMulRatioX);
                y = (int)(motionevent.getY() * Game.mMulRatioY);
                pressed = true;
                eventMoving = true;
                if(Game._touchmode == 1)
                {
                    Game.getCurrentStage().onTouchEvent(2, x, y);
                }
                break;
        }
        try
        {
            Thread.sleep(8L);
        }
        catch(InterruptedException interruptedexception)
        {
            return true;
        }
        return true;
    }

    public static boolean isIn(int i, int j, int k)
    {
        return MathUtils.PtInRect(i - k, j - k, i + k, j + k, x, y);
    }

    public static boolean isIn(int i, int j, int k, int l)
    {
        return MathUtils.PtInRect(i - k, j - l, i + k, j + l, x, y);
    }

    public static boolean isInRect(int i, int j, int k, int l)
    {
        return MathUtils.PtInRect(i, j, i + k, j + l, x, y);
    }

    protected static void reset()
    {
        x = 0;
        y = 0;
        px = 0;
        py = 0;
        pt = 0L;
        pressed = false;
        eventMoving = false;
        eventDown = false;
        eventUp = false;
    }

    public static boolean touch(int i, int j, int k)
    {
        if(eventDown)
        {
            return MathUtils.PtInRect(i - k, j - k, i + k, j + k, x, y);
        } else
        {
            return false;
        }
    }

    public static boolean touch(int i, int j, int k, int l)
    {
        if(eventDown)
        {
            return MathUtils.PtInRect(i, j, k, l, x, y);
        } else
        {
            return false;
        }
    }

    public static final boolean touch(BaseEntity baseentity)
    {
        while(!eventDown || (float)px < baseentity.pos.x || (float)py < baseentity.pos.y || (float)py >= baseentity.pos.x + baseentity.size.x || (float)py >= baseentity.pos.y + baseentity.size.y) 
        {
            return false;
        }
        return true;
    }
}

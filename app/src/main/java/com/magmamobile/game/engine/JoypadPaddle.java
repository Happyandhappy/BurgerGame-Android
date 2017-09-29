package com.magmamobile.game.engine;

import android.graphics.Paint;
import android.view.MotionEvent;

public class JoypadPaddle extends Joypad
{

    protected int dx;
    protected int dy;
    private Paint paint1;
    private Paint paint2;
    private int pr;

    public JoypadPaddle()
    {
        this(Game.scalei(74), Game.getBufferHeight() - Game.scalei(74), Game.scalei(64));
    }

    public JoypadPaddle(int i, int j, int k)
    {
        paint1 = new Paint();
        paint1.setColor(0x80000000);
        paint2 = new Paint();
        paint2.setColor(0x80ffffff);
        pr = (int)(0.10000000000000001D * (double)k);
        r = k;
        x = i;
        y = j;
    }

    protected void JoyDown(int i, int j, MotionEvent motionevent)
    {
        if(hit(i, j))
        {
            p = 1 + p;
            dx = i - x;
            dy = j - y;
        }
    }

    protected void JoyMove(int i, int j, MotionEvent motionevent)
    {
        if(hit(i, j))
        {
            dx = i - x;
            dy = j - y;
            if(p == 0)
            {
                p = 1;
            }
        }
    }

    protected void JoyUp(int i, int j, MotionEvent motionevent)
    {
        if(p > 0)
        {
            if(hit(i, j))
            {
                p = -1 + p;
                if(p < 0)
                {
                    p = 0;
                }
            } else
            if(!hasPointerOnIt(motionevent))
            {
                p = 0;
                return;
            }
        }
    }

    public float getDirLength()
    {
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    public int getDirX()
    {
        return dx;
    }

    public int getDirY()
    {
        return dy;
    }

    public void onRender()
    {
        if(isPressed())
        {
            paint1.setAlpha(128);
            paint2.setAlpha(128);
            Game.drawCircle(x, y, r, paint1);
            Game.drawCircle(x + dx, y + dy, pr, paint2);
            return;
        } else
        {
            paint1.setAlpha(48);
            paint2.setAlpha(48);
            Game.drawCircle(x, y, r, paint1);
            Game.drawCircle(x + dx, y + dy, pr, paint2);
            return;
        }
    }

    public void release()
    {
        p = 0;
        dx = 0;
        dy = 0;
    }
}

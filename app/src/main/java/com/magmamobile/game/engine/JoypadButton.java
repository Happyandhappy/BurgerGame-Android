package com.magmamobile.game.engine;

import android.graphics.Paint;
import android.view.MotionEvent;

public class JoypadButton extends Joypad
{

    private Paint paint;

    public JoypadButton()
    {
        this(Game.getBufferWidth() - Game.scalei(42), Game.getBufferHeight() - Game.scalei(74), Game.scalei(32));
    }

    public JoypadButton(int i, int j, int k)
    {
        paint = new Paint();
        paint.setColor(0x80000000);
        r = k;
        x = i;
        y = j;
    }

    protected void JoyDown(int i, int j, MotionEvent motionevent)
    {
        if(hit(i, j))
        {
            p = 1 + p;
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

    public void onRender()
    {
        if(isPressed())
        {
            paint.setAlpha(128);
            Game.drawCircle(x, y, r, paint);
            return;
        } else
        {
            paint.setAlpha(48);
            Game.drawCircle(x, y, r, paint);
            return;
        }
    }
}

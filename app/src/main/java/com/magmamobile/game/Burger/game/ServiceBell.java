package com.magmamobile.game.Burger.game;

import android.graphics.*;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.SoundManager;
import com.magmamobile.game.engine.*;

public class ServiceBell
{

    private final float DELTA = Game.scalef(3.6F);
    private final float SPEED = 0.075F;
    private boolean _activated;
    private float _f;
    private int _h;
    private Matrix _mtx;
    private Paint _p;
    private int _w;
    private int _x;
    private float _xB;
    private int _y;
    private float _yB;

    public ServiceBell(int i, int j)
    {
        _x = i;
        _y = j;
        _w = BitmapManager.serviceBell.getWidth();
        _h = BitmapManager.serviceBell.getHeight();
        _xB = i + Game.scalei(28);
        _yB = j + Game.scalei(-4);
        _mtx = new Matrix();
        _p = new Paint(1);
    }

    public void animate()
    {
        if(TouchScreen.eventDown && TouchScreen.isInRect(_x, _y, _w, _h))
        {
            _activated = true;
        }
        if(_f > 0.0F)
        {
            _f = _f - 0.075F;
            _mtx.setTranslate(_xB, MathUtils.lerpAccelerate(_yB, _yB + DELTA, _f));
            return;
        } else
        {
            _f = 0.0F;
            return;
        }
    }

    public void draw()
    {
        Game.drawBitmap(BitmapManager.serviceBell, _x, _y);
        if(_f > 0.0F)
        {
            Game.drawBitmap(BitmapManager.serviceButton, _mtx, _p);
            return;
        } else
        {
            Game.drawBitmap(BitmapManager.serviceButton, _xB, _yB);
            return;
        }
    }

    public void init()
    {
        _f = 0.0F;
        _activated = false;
        _mtx.setTranslate(_xB, MathUtils.lerpAccelerate(_yB, _yB + DELTA, _f));
    }

    public boolean onAction()
    {
        boolean flag = _activated;
        boolean flag1 = false;
        if(flag)
        {
            _activated = false;
            flag1 = true;
        }
        return flag1;
    }

    public void ring()
    {
        _f = 1.0F;
        SoundManager.playSound(34);
    }
}

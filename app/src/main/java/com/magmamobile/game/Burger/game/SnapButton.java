package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.TouchScreen;

public class SnapButton
{

    private boolean _activated;
    private int _h;
    private boolean _pressed;
    private int _w;
    private int _x;
    private int _y;

    public SnapButton(int i, int j)
    {
        _x = i;
        _y = j;
        _w = BitmapManager.snapUp.getWidth();
        _h = BitmapManager.snapUp.getHeight();
    }

    public void animate()
    {
        if(!TouchScreen.isInRect(_x, _y, _w, _h)){
            if(_pressed)
            {
                _pressed = false;
            }
            return;
        }
        if(_pressed || !TouchScreen.eventDown) {
            if(_pressed && TouchScreen.eventUp)
            {
                _pressed = false;
            }
            return;
        }
        _activated = true;
        _pressed = true;
    }

    public void draw()
    {
        if(!_pressed)
        {
            Game.drawBitmap(BitmapManager.snapUp, _x, _y);
            return;
        } else
        {
            Game.drawBitmap(BitmapManager.snapDown, _x, _y);
            return;
        }
    }

    public void init()
    {
        _activated = false;
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
}

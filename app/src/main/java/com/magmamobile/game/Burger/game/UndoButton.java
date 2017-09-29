package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.SoundManager;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.TouchScreen;

public class UndoButton
{

    private boolean _activated;
    private int _h;
    private boolean _pressed;
    private int _w;
    private int _x;
    private int _xB;
    private int _xD;
    private int _y;
    private int _yB;
    private int _yD;

    public UndoButton(int i, int j)
    {
        _x = i;
        _y = j;
        _w = BitmapManager.serviceBell.getWidth();
        _h = BitmapManager.serviceBell.getHeight();
        _xB = i + Game.scalei(10);
        _yB = j + Game.scalei(8);
        _xD = _xB - Game.scalei(2);
        _yD = _yB - Game.scalei(2);
    }

    public void animate()
    {
        if(!TouchScreen.isInRect(_x, _y, _w, _h)){
            if(_pressed)
            {
                _pressed = false;
                SoundManager.playSound(36);
            }
            return;
        }
        if(_pressed || !TouchScreen.eventDown) {
            if(_pressed && TouchScreen.eventUp)
            {
                _pressed = false;
                SoundManager.playSound(36);
            }
            return;
        }
        _activated = true;
        _pressed = true;
        SoundManager.playSound(35);
    }

    public void draw()
    {
        Game.drawBitmap(BitmapManager.undoBox, _x, _y);
        if(!_pressed)
        {
            Game.drawBitmap(BitmapManager.undoButton, _xB, _yB);
            return;
        } else
        {
            Game.drawBitmap(BitmapManager.undoButton, _xD, _yD);
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

package com.magmamobile.game.Burger.ui;

import android.graphics.*;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.TouchScreen;

public class OptionButton
{

    private final double PI2;
    private final float SCALE_ADD;
    private final float SPEED;
    private boolean _activated;
    private Bitmap _bmpDIS;
    private Bitmap _bmpOFF;
    private Bitmap _bmpON;
    private int _cx;
    private int _cy;
    private float _f;
    private int _h;
    private boolean _pressed;
    private int _w;
    private int _x;
    private int _y;
    public boolean added;
    public boolean animated;
    public boolean enabled;
    private Matrix mtx;
    private Paint pAlias;

    public OptionButton(int i, int j)
    {
        this(i, j, null, null, null);
    }

    public OptionButton(int i, int j, Bitmap bitmap, Bitmap bitmap1)
    {
        this(i, j, bitmap, bitmap1, null);
    }

    public OptionButton(int i, int j, Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2)
    {
        SPEED = 0.25F;
        SCALE_ADD = 0.1F;
        PI2 = 6.2831853071795862D;
        _x = i;
        _y = j;
        _bmpON = bitmap1;
        _bmpOFF = bitmap;
        _bmpDIS = bitmap2;
        if(bitmap != null)
        {
            _w = _bmpON.getWidth();
            _h = _bmpON.getHeight();
        }
        _cx = _w / 2;
        _cy = _h / 2;
        _f = 0.0F;
        mtx = new Matrix();
        pAlias = new Paint(1);
    }

    public void animate()
    {
        if(animated && enabled)
        {
            _f = 0.25F + _f;
            if((double)_f >= 6.2831853071795862D)
            {
                _f = 0.0F;
            }
            float f = 1.0F + (float)(0.10000000149011612D * Math.cos(_f));
            mtx.setTranslate(_x, _y);
            mtx.preScale(f, f, _cx, _cy);
        }
    }

    public void draw()
    {
        Bitmap bitmap;
label0:
        {
            if(added)
            {
                if(!enabled)
                {
                    bitmap = _bmpDIS;
                } else
                if(_pressed)
                {
                    bitmap = _bmpON;
                } else
                {
                    bitmap = _bmpOFF;
                }
                if(!animated)
                {
                    break label0;
                }
                Game.drawBitmap(bitmap, mtx, pAlias);
            }
            return;
        }
        Game.drawBitmap(bitmap, _x, _y);
    }

    public void init()
    {
        _pressed = false;
        animated = false;
        enabled = true;
        _f = 0.0F;
    }

    public void interact()
    {
        if(!added || !enabled || !TouchScreen.isInRect(_x, _y, _w, _h)){
            if(_pressed)
            {
                _pressed = false;
            }
            return;
        }
        if(!TouchScreen.eventDown){
            _pressed = false;
            return;
        }
        _pressed = true;
        _activated = true;
    }

    public boolean onPress()
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

    public void setBitmapStates(Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2)
    {
        _bmpON = bitmap1;
        _bmpOFF = bitmap;
        _bmpDIS = bitmap2;
        _w = _bmpON.getWidth();
        _h = _bmpON.getHeight();
    }

    public void setX(int i)
    {
        _x = i;
    }
}

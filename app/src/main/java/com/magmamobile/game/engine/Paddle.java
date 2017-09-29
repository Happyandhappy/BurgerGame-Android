package com.magmamobile.game.engine;

import android.graphics.Bitmap;

public class Paddle extends GameObject
{

    private int _dir;
    private int _dirX;
    private int _dirY;
    private boolean _down;
    private Bitmap _image;
    private int _paddingBottom;
    private int _paddingLeft;
    private int _paddingRight;
    private int _paddingTop;

    public Paddle()
    {
        visible = true;
        enabled = true;
    }

    private void update()
    {
        if(_image != null)
        {
            setSize(_image.getWidth() + _paddingLeft + _paddingRight, _image.getHeight() + _paddingTop + _paddingBottom);
            return;
        } else
        {
            setSize(_paddingLeft + _paddingRight, _paddingTop + _paddingBottom);
            return;
        }
    }

    public int dir()
    {
        return _dir;
    }

    public int dirX()
    {
        return _dirX;
    }

    public int dirY()
    {
        return _dirY;
    }

    public boolean hit()
    {
        return intersectPoint(TouchScreen.x, TouchScreen.y);
    }

    public boolean isDown()
    {
        return _down;
    }

    public void onAction()
    {
        _down = false;
        if(TouchScreen.eventDown && hit())
        {
            _down = true;
            int i = TouchScreen.x - ((int)x + cw);
            int j = TouchScreen.y - ((int)y + ch);
            if(Math.abs(i) > Math.abs(j))
            {
                _dirX = MathUtils.sgni(i);
                _dirY = 0;
            } else
            {
                _dirX = 0;
                _dirY = MathUtils.sgni(j);
            }
            if(_dirY == -1)
            {
                _dir = 0;
            } else
            {
                if(_dirX == 1)
                {
                    _dir = 1;
                    return;
                }
                if(_dirY == 1)
                {
                    _dir = 2;
                    return;
                }
                if(_dirX == -1)
                {
                    _dir = 3;
                    return;
                }
            }
        }
    }

    public void onRender()
    {
        while(!visible || !enabled || _image == null) 
        {
            return;
        }
        Game.drawBitmap(_image, (int)x + _paddingLeft, (int)y + _paddingTop);
    }

    public void setImage(Bitmap bitmap)
    {
        _image = bitmap;
        update();
    }

    public void setPadding(int i, int j, int k, int l)
    {
        _paddingLeft = i;
        _paddingTop = j;
        _paddingRight = k;
        _paddingBottom = l;
        update();
    }
}

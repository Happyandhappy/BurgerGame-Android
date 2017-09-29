package com.magmamobile.game.engine;

import android.graphics.*;

public class Sprite extends GameObject
{

    private int _alpha;
    private int _angle;
    private boolean _antialias;
    private Bitmap _bitmap;
    private boolean _dirty;
    private Paint _paint;
    private float _zoom;

    public Sprite()
    {
        _paint = new Paint();
        _antialias = false;
        _dirty = false;
        _bitmap = null;
        _alpha = 255;
        _angle = 0;
        _zoom = 1.0F;
    }

    public Sprite(int i)
    {
        this();
        _bitmap = Game.getBitmap(i);
        show();
    }

    private Paint getInnerPaint()
    {
        if(_antialias || _alpha != 255)
        {
            return _paint;
        } else
        {
            return null;
        }
    }

    private void update()
    {
        _paint.setAlpha(_alpha);
        w = _bitmap.getWidth();
        h = _bitmap.getHeight();
        w = (int)((float)w * _zoom);
        h = (int)((float)h * _zoom);
        cw = w >> 1;
        ch = h >> 1;
        _dirty = false;
    }

    public int getAlpha()
    {
        return _alpha;
    }

    public int getAngle()
    {
        return _angle;
    }

    public boolean getAntiAlias()
    {
        return _antialias;
    }

    public Bitmap getBitmap()
    {
        return _bitmap;
    }

    public Paint getPaint()
    {
        return _paint;
    }

    public Rect getRect()
    {
        return new Rect((int)(x - (float)cw), (int)(y - (float)ch), (int)(x + (float)cw), (int)(y + (float)ch));
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getZoom()
    {
        return _zoom;
    }

    public boolean hit(int i, int j)
    {
        if(!visible || !enabled)
        {
            return false;
        } else
        {
            return MathUtils.PtInRect((int)x - cw, (int)y - ch, (int)x + cw, (int)y + ch, i, j);
        }
    }

    public void invalidate()
    {
        _dirty = true;
    }

    public void moveBackward(float f)
    {
        float f1 = 0.01745329F * (float)_angle;
        x = (float)((double)x - Math.cos(f1) * (double)f);
        y = (float)((double)y - Math.sin(f1) * (double)f);
    }

    public void moveForward(float f)
    {
        float f1 = 0.01745329F * (float)_angle;
        x = (float)((double)x + Math.cos(f1) * (double)f);
        y = (float)((double)y + Math.sin(f1) * (double)f);
    }

    public void onAction()
    {
        if(_dirty)
        {
            update();
        }
        if(enabled);
    }

    public void onRender()
    {
        if(!visible || !enabled)
        {
            return;
        }
        if(_dirty)
        {
            update();
        }
        if(_angle != 0 || _zoom != 1.0F)
        {
            Game.drawBitmap(_bitmap, (int)x, (int)y, _angle, _zoom, getInnerPaint());
            return;
        } else
        {
            Game.drawBitmap(_bitmap, (int)x - cw, (int)y - ch, getInnerPaint());
            return;
        }
    }

    public void setAlpha(int i)
    {
        _alpha = i;
        _dirty = true;
    }

    public void setAngle(int i)
    {
        _angle = i;
        _dirty = true;
    }

    public void setAntiAlias(boolean flag)
    {
        if(flag != _antialias)
        {
            _antialias = flag;
            _paint.setAntiAlias(_antialias);
            _paint.setFilterBitmap(_antialias);
        }
    }

    public void setBitmap(int i)
    {
        setBitmap(Game.getBitmap(i));
    }

    public void setBitmap(Bitmap bitmap)
    {
        _bitmap = bitmap;
        _dirty = true;
    }

    public void setX(float f)
    {
        x = f;
    }

    public void setY(float f)
    {
        y = f;
    }

    public void setZoom(float f)
    {
        _zoom = f;
        _dirty = true;
    }
}

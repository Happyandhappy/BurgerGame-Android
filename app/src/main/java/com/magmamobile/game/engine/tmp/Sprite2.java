package com.magmamobile.game.engine.tmp;

import android.graphics.*;
import com.magmamobile.game.engine.Game;

public class Sprite2
{

    private int _angle;
    private boolean _autoSize;
    private Bitmap _bitmap;
    private boolean _enabled;
    private float _factorHeight;
    private float _factorWidth;
    private int _height;
    private int _innerHeight;
    private int _innerWidth;
    private float _left;
    private Matrix _matrix;
    private int _midHeight;
    private int _midWidth;
    private Paint _paint;
    private float _top;
    private boolean _visible;
    private int _width;

    public Sprite2()
    {
        _autoSize = true;
        _paint = new Paint();
        _matrix = new Matrix();
        setVisible(true);
        setEnabled(true);
        setAntialias(true);
    }

    public Sprite2(int i)
    {
        this(Game.getBitmap(i));
    }

    public Sprite2(Bitmap bitmap)
    {
        this();
        setBitmap(bitmap);
    }

    protected void doAction()
    {
    }

    protected void doRender()
    {
        _matrix.reset();
        if(_angle != 0)
        {
            _matrix.postRotate(_angle);
        }
        if(_innerWidth != _width || _innerHeight != _height)
        {
            _matrix.postScale(_factorWidth, _factorHeight);
        }
        _matrix.preTranslate(-_midWidth, -_midHeight);
        _matrix.postTranslate(_left, _top);
        Game.drawBitmap(_bitmap, _matrix, _paint);
    }

    public int getAlpha()
    {
        return _paint.getAlpha();
    }

    public int getAngle()
    {
        return _angle;
    }

    public Bitmap getBitmap()
    {
        return _bitmap;
    }

    public float getFactorHeight()
    {
        return _factorHeight;
    }

    public float getFactorWidth()
    {
        return _factorWidth;
    }

    public int getHeight()
    {
        return _height;
    }

    public int getInnerHeight()
    {
        return _innerHeight;
    }

    public int getInnerWidth()
    {
        return _innerWidth;
    }

    public float getLeft()
    {
        return _left;
    }

    public float getMidHeight()
    {
        return (float)_midHeight;
    }

    public float getMidWidth()
    {
        return (float)_midWidth;
    }

    public Paint getPaint()
    {
        return _paint;
    }

    public float getTop()
    {
        return _top;
    }

    public int getWidth()
    {
        return _width;
    }

    public boolean isAntialias()
    {
        return _paint.isAntiAlias();
    }

    public boolean isEnabled()
    {
        return _enabled;
    }

    public boolean isVisible()
    {
        return _visible;
    }

    public void onAction()
    {
        if(!_enabled)
        {
            return;
        } else
        {
            doAction();
            return;
        }
    }

    public void onRender()
    {
        if(!_visible)
        {
            return;
        } else
        {
            doRender();
            return;
        }
    }

    public boolean ptInBox(int i, int j)
    {
        return (float)i >= _left - (float)_midWidth && (float)i <= _left + (float)_midWidth && (float)j >= _top - (float)_midHeight && (float)j <= _top + (float)_midHeight;
    }

    public void scale(float f)
    {
        setWidth((int)(f * (float)_innerWidth));
        setHeight((int)(f * (float)_innerHeight));
    }

    public void setAlpha(int i)
    {
        _paint.setAlpha(i);
    }

    public void setAngle(int i)
    {
        _angle = i;
    }

    public void setAntialias(boolean flag)
    {
        _paint.setAntiAlias(flag);
        _paint.setFilterBitmap(flag);
    }

    public void setBitmap(int i)
    {
        setBitmap(Game.getBitmap(i));
    }

    public void setBitmap(Bitmap bitmap)
    {
        _bitmap = bitmap;
        if(_bitmap != null)
        {
            _innerWidth = _bitmap.getWidth();
            _innerHeight = _bitmap.getHeight();
        } else
        {
            _innerWidth = 0;
            _innerHeight = 0;
        }
        _midWidth = _innerWidth >> 1;
        _midHeight = _innerHeight >> 1;
        if(_autoSize)
        {
            _width = _innerWidth;
            _height = _innerHeight;
        }
        if(_innerWidth != 0)
        {
            _factorWidth = (float)_width / (float)_innerWidth;
        }
        if(_innerWidth != 0)
        {
            _factorHeight = (float)_height / (float)_innerHeight;
        }
    }

    public void setEnabled(boolean flag)
    {
        _enabled = flag;
    }

    public void setHeight(int i)
    {
        _height = i;
        _autoSize = false;
        if(_innerHeight != 0)
        {
            _factorHeight = (float)_height / (float)_innerHeight;
            return;
        } else
        {
            _factorHeight = 0.0F;
            return;
        }
    }

    public void setLeft(float f)
    {
        _left = f;
    }

    public void setTop(float f)
    {
        _top = f;
    }

    public void setVisible(boolean flag)
    {
        _visible = flag;
    }

    public void setWidth(int i)
    {
        _width = i;
        _autoSize = false;
        if(_innerWidth != 0)
        {
            _factorWidth = (float)_width / (float)_innerWidth;
            return;
        } else
        {
            _factorWidth = 0.0F;
            return;
        }
    }
}

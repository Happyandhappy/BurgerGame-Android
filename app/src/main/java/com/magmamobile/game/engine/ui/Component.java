package com.magmamobile.game.engine.ui;

import com.magmamobile.game.engine.*;

public class Component
    implements IGameEvents
{

    protected boolean _clickable;
    protected boolean _dirty;
    protected boolean _enabled;
    protected int _height;
    protected float _left;
    protected int _measuredHeight;
    protected int _measuredWidth;
    protected boolean _pressed;
    protected float _top;
    protected boolean _visible;
    protected int _width;

    public Component()
    {
        _visible = true;
        _enabled = true;
        _dirty = true;
    }

    public int getHeight()
    {
        return _height;
    }

    public float getLeft()
    {
        return _left;
    }

    public final int getMeasuredHeight()
    {
        return _measuredHeight;
    }

    public final int getMeasuredWidth()
    {
        return _measuredWidth;
    }

    public float getTop()
    {
        return _top;
    }

    public int getWidth()
    {
        return _width;
    }

    public void invalidate()
    {
        _dirty = true;
    }

    public boolean isClickable()
    {
        return _clickable;
    }

    public boolean isDirty()
    {
        return _dirty;
    }

    public boolean isPressed()
    {
        return _pressed;
    }

    public void onAction()
    {
        if(!_visible || !_enabled || !_clickable)
            return;
        if(!_pressed)
        {
            if(!TouchScreen.eventDown || !MathUtils.PtInRect((int)_left, (int)_top, (int)_left + _width, (int)_top + _height, TouchScreen.x, TouchScreen.y))
                return;
            _pressed = true;
            onTouchDown();
            return;
        }
        if(TouchScreen.pressed)
            return;
        _pressed = false;
        onTouchUp();
    }

    protected void onDraw()
    {
    }

    public void onRender()
    {
        if(!_visible)
        {
            return;
        } else
        {
            onDraw();
            return;
        }
    }

    protected void onTouchDown()
    {
    }

    protected void onTouchUp()
    {
    }

    public void setClickable(boolean flag)
    {
        _clickable = flag;
    }

    public void setHeight(int i)
    {
        if(i == _height)
        {
            return;
        } else
        {
            _dirty = true;
            _height = i;
            return;
        }
    }

    public void setLeft(float f)
    {
        if(f == _left)
        {
            return;
        } else
        {
            _dirty = true;
            _left = f;
            return;
        }
    }

    public void setTop(float f)
    {
        if(f == _top)
        {
            return;
        } else
        {
            _dirty = true;
            _top = f;
            return;
        }
    }

    public void setWidth(int i)
    {
        if(i == _width)
        {
            return;
        } else
        {
            _dirty = true;
            _width = i;
            return;
        }
    }
}

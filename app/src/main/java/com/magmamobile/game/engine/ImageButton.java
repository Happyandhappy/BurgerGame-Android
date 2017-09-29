package com.magmamobile.game.engine;

import android.graphics.Bitmap;

public class ImageButton extends GameObject
{
    public static class FIT
    {

        public static final int FIT_AUTO = 1;
        public static final int FIT_NONE = 0;

        public FIT()
        {
        }
    }


    private boolean _clicked;
    private int _fit;
    private Bitmap _icon;
    private Bitmap _iconOff;
    private Bitmap _iconOn;
    private int _iconx;
    private int _icony;
    private boolean _pressed;
    private Sound _sound;
    private Label _text;

    public ImageButton()
    {
        int i = Game.getParameters().DEFAULT_BUTTON_SOUND;
        Sound sound;
        if(i != 0)
        {
            sound = Game.getSound(i);
        } else
        {
            sound = null;
        }
        _sound = sound;
        _text = new Label();
        visible = true;
        enabled = true;
    }

    public ImageButton(int i, int j, int k, int l, String s, Bitmap bitmap)
    {
        this();
        setX(i);
        setY(j);
        setSize(k, l);
        _text.setText(s);
        _text.setSize(Game.getParameters().DEFAULT_BUTTON_TEXT_SIZE);
        _text.setColor(Game.getParameters().DEFAULT_BUTTON_TEXT_COLOR);
        _icon = bitmap;
        _iconOn = bitmap;
        _iconOff = bitmap;
        update();
    }

    public ImageButton(int i, int j, int k, int l, String s, Bitmap bitmap, int i1)
    {
        this(i, j, k, l, s, bitmap);
        _fit = i1;
    }

    public ImageButton(int i, int j, int k, int l, String s, Bitmap bitmap, Bitmap bitmap1)
    {
        this(i, j, k, l, s, bitmap);
        _iconOn = bitmap1;
    }

    private void update()
    {
        if(hasMoved || isResized)
        {
            if(_icon != null)
            {
                if(_fit == 1)
                {
                    _iconx = (int)x;
                    _icony = (int)y;
                    _text.x = (int)x + (cw - _text.cw);
                    _text.setY(8 + (_icony + ch));
                } else
                {
                    _iconx = (int)x + (cw - _icon.getWidth() / 2);
                    _icony = (int)y;
                    _text.x = (int)x + (cw - _text.cw);
                    _text.setY(8 + (_icony + _icon.getHeight()));
                }
            }
            hasMoved = false;
            isResized = false;
        }
    }

    public int getFit()
    {
        return _fit;
    }

    public Bitmap getIconOff()
    {
        return _iconOff;
    }

    public Bitmap getIconOn()
    {
        return _iconOn;
    }

    public Sound getSound()
    {
        return _sound;
    }

    public boolean intersectPoint(int i, int j)
    {
        if(_fit == 1)
        {
            return MathUtils.PtInRect((int)x - cw, (int)y - ch, (int)x + cw, (int)y + ch, i, j);
        } else
        {
            return super.intersectPoint(i, j);
        }
    }

    public boolean isClicked()
    {
        return _clicked;
    }

    public void onAction()
    {
        if(!enabled)
            return;
        _clicked = false;
        if(focusClick)
        {
            focusClick = false;
            if(_sound != null)
            {
                _sound.play();
            }
            if(Game.opHaptic)
            {
                Game.vibrate(25L);
            }
            _pressed = false;
            _clicked = true;
            return;
        }
        if(_pressed)
        {
            if(TouchScreen.eventUp)
            {
                if(_sound != null)
                {
                    _sound.play();
                }
                if(Game.opHaptic)
                {
                    Game.vibrate(25L);
                }
                _pressed = false;
                _clicked = true;
                _icon = _iconOff;
                update();
                return;
            }
            if(TouchScreen.pressed && !intersectPoint(TouchScreen.x, TouchScreen.y))
            {
                _pressed = false;
                _icon = _iconOff;
                update();
                return;
            }
            return;
        }
        if(TouchScreen.eventDown && intersectPoint(TouchScreen.x, TouchScreen.y))
        {
            _pressed = true;
            _icon = _iconOn;
            update();
            return;
        }
    }

    public boolean onClick()
    {
        return _clicked;
    }

    protected void onDrawFocus()
    {
        Game.drawRect((int)x, (int)y, w, h, Debug.focusRect);
    }

    public void onRender()
    {
        update();
        if(!visible)
        {
            return;
        }
        if(_fit == 1)
        {
            Game.drawBitmap(_icon, _iconx - cw, _icony - ch, w, h);
        } else
        {
            Game.drawBitmap(_icon, _iconx, _icony);
        }
        if(selected)
        {
            onDrawFocus();
        }
        _text.onRender();
    }

    public void setFit(int i)
    {
        _fit = i;
    }

    public void setIcon(int i)
    {
        _icon = getBitmap(i);
        setIconOn(_icon);
        setIconOff(_icon);
        hasMoved = true;
    }

    public void setIcon(Bitmap bitmap)
    {
        _icon = bitmap;
        setIconOn(_icon);
        setIconOff(_icon);
        hasMoved = true;
    }

    public void setIconOff(Bitmap bitmap)
    {
        _iconOff = bitmap;
        hasMoved = true;
    }

    public void setIconOn(Bitmap bitmap)
    {
        _iconOn = bitmap;
        hasMoved = true;
    }

    public void setSound(Sound sound)
    {
        _sound = sound;
    }

    public void setText(int i)
    {
        setText(Game.getResString(i));
    }

    public void setText(String s)
    {
        _text.setText(s);
        hasMoved = true;
    }

    public void setTextColor(int i)
    {
        _text.setColor(i);
        hasMoved = true;
    }

    public void setTextSize(float f)
    {
        _text.setSize(f);
        hasMoved = true;
    }
}

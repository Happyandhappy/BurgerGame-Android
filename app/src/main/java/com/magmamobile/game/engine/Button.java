package com.magmamobile.game.engine;

import android.graphics.Bitmap;
import android.graphics.Typeface;

public class Button extends GameObject
{

    public static final int ICON_BOTTOM = 8;
    public static final int ICON_CENTER = 0;
    public static final int ICON_CENTER_HORIZONTAL = 0;
    public static final int ICON_CENTER_VERTICAL = 0;
    public static final int ICON_LEFT = 1;
    public static final int ICON_RIGHT = 2;
    public static final int ICON_TOP = 4;
    protected int _bevelX;
    protected int _bevelY;
    protected int _iconAlign;
    protected int _iconOfsX;
    protected int _iconOfsY;
    protected int _iconPadding;
    protected boolean _iconTextAlign;
    protected Label _label;
    protected Sound _sound;
    protected Bitmap bmpDisabled;
    protected Bitmap bmpIcon;
    protected Bitmap bmpNormal;
    protected Bitmap bmpPressed;
    protected Bitmap bmpSelected;
    public boolean ninePatch;
    public boolean onClick;
    public boolean pressed;
    public boolean switchable;
    public boolean switched;

    public Button()
    {
        makeLabel(null, -1);
        _iconTextAlign = true;
        switchable = false;
        switched = false;
        onClick = false;
        visible = true;
        enabled = true;
        ninePatch = true;
        _sound = null;
        _bevelX = Game.scalei(1);
        _bevelY = Game.scalei(1);
    }

    public Button(int i, int j, int k, int l, boolean flag, String s, Bitmap bitmap, 
            Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3)
    {
        this(i, j, k, l, flag, s, bitmap, bitmap1, bitmap2, bitmap3, null, getDefaultTextColor(), getDefaultSound());
    }

    public Button(int i, int j, int k, int l, boolean flag, String s, Bitmap bitmap, 
            Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, int i1)
    {
        this(i, j, k, l, flag, s, bitmap, bitmap1, bitmap2, bitmap3, null, i1, getDefaultSound());
    }

    public Button(int i, int j, int k, int l, boolean flag, String s, Bitmap bitmap, 
            Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4)
    {
        this(i, j, k, l, flag, s, bitmap, bitmap1, bitmap2, bitmap3, bitmap4, getDefaultTextColor(), getDefaultSound());
    }

    public Button(int i, int j, int k, int l, boolean flag, String s, Bitmap bitmap, 
            Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, int i1)
    {
        this(i, j, k, l, flag, s, bitmap, bitmap1, bitmap2, bitmap3, bitmap4, i1, getDefaultSound());
    }

    public Button(int i, int j, int k, int l, boolean flag, String s, Bitmap bitmap, 
            Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, int i1, int j1)
    {
        _bevelX = Game.scalei(1);
        _bevelY = Game.scalei(1);
        _iconTextAlign = true;
        switchable = flag;
        switched = false;
        onClick = false;
        visible = true;
        enabled = true;
        ninePatch = true;
        bmpIcon = bitmap;
        bmpNormal = bitmap1;
        bmpPressed = bitmap2;
        bmpSelected = bitmap3;
        Sound sound;
        if(j1 != 0)
        {
            sound = Game.getSound(j1);
        } else
        {
            sound = null;
        }
        _sound = sound;
        makeLabel(s, i1);
        if(k == 0x80000000)
        {
            k = _label.w + Game.scalei(20);
        }
        w = k;
        if(l == 0x80000000)
        {
            l = _label.h + Game.scalei(20);
        }
        h = l;
        cw = w >> 1;
        ch = h >> 1;
        if(i == 0x80000000)
        {
            i = Game.centerX(w);
        }
        x = i;
        if(j == 0x80000000)
        {
            j = Game.centerY(h);
        }
        y = j;
        setBackgrounds(bitmap1, bitmap3, bitmap2, bitmap4);
        computeTextOffsets();
        computeIconOffsets();
    }

    private void computeIconOffsets()
    {
        if(bmpIcon == null)
            return;
        int i;
        int j;
        i = 3 & _iconAlign;
        j = 3 & _iconAlign >> 3;
        if(!_iconTextAlign) {
            switch(i) {
                default:
                    _iconOfsX = w - bmpIcon.getWidth() >> 1;
                    break;
                case 0:
                    _iconOfsX = _iconPadding;
                    break;
                case 1:
                    _iconOfsX = w - bmpIcon.getWidth() - _iconPadding;
                    break;
            }
            switch(j)
            {
                case 0: // '\0'
                    _iconOfsY = h - bmpIcon.getHeight() >> 1;
                    break;

                case 1: // '\001'
                    _iconOfsY = h >> 1;
                    break;

                case 2: // '\002'
                    _iconOfsY = h >> 1;
                    break;
            }
        }else {
            switch (i) {
                default:
                    break;
                case 0:
                    _iconOfsX = w - bmpIcon.getWidth() >> 1;
                    break;
                case 1:
                    _iconOfsX = cw - _label.cw - bmpIcon.getWidth() - _iconPadding;
                    break;
                case 2:
                    _iconOfsX = cw + _label.cw + _iconPadding;
                    break;
            }
            switch (j) {
                default:
                    break;
                case 0:
                    _iconOfsY = h - bmpIcon.getHeight() >> 1;
                    break;
                case 1:
                    _iconOfsY = h >> 1;
                    break;
                case 2:
                    _iconOfsY = h >> 1;
                    break;
            }
            if (pressed) {
                _iconOfsX = _iconOfsX + _bevelX;
                _iconOfsY = _iconOfsY + _bevelY;
            }
        }
    }

    private void computeTextOffsets()
    {
        if(!pressed)
        {
            _label.x = (x + (float)cw) - (float)_label.cw;
            _label.y = (y + (float)ch) - (float)_label.ch;
            return;
        } else
        {
            _label.x = ((x + (float)cw) - (float)_label.cw) + (float)_bevelX;
            _label.y = ((y + (float)ch) - (float)_label.ch) + (float)_bevelY;
            return;
        }
    }

    public static final GameArray createArray(int i)
    {
        return new GameArray(i) {

            public Button[] createArray(int j)
            {
                return new Button[j];
            }

            public Button createObject()
            {
                return new Button();
            }

        };
    }

    private void drawBackGround(Bitmap bitmap)
    {
        if(ninePatch)
        {
            Game.drawBitmap9(bitmap, (int)x, (int)y, w, h, null);
            return;
        } else
        {
            Game.drawBitmap(bitmap, (int)x, (int)y);
            return;
        }
    }

    public static final int getDefaultSound()
    {
        return Game.parameters.DEFAULT_BUTTON_SOUND;
    }

    public static final int getDefaultTextColor()
    {
        return Game.parameters.DEFAULT_BUTTON_TEXT_COLOR;
    }

    private void makeLabel(String s, int i)
    {
        _label = new Label();
        _label.setText(s);
        _label.setAutoSize(true);
        _label.setColor(i);
        _label.setSize(Game.getParameters().DEFAULT_BUTTON_TEXT_SIZE);
    }

    public static final void setDefaultSound(int i)
    {
        Game.parameters.DEFAULT_BUTTON_SOUND = i;
    }

    public static final void setDefaultTextColor(int i)
    {
        Game.parameters.DEFAULT_BUTTON_TEXT_COLOR = i;
    }

    public int getBevel()
    {
        return _bevelX;
    }

    public int getBevelY()
    {
        return _bevelY;
    }

    public boolean getClickAndRelease()
    {
        boolean flag = onClick;
        onClick = false;
        return flag;
    }

    public Bitmap getIcon()
    {
        return bmpIcon;
    }

    public int getIconAlign()
    {
        return _iconAlign;
    }

    public int getIconPadding()
    {
        return _iconPadding;
    }

    public boolean getIconTextAlign()
    {
        return _iconTextAlign;
    }

    public Label getLabel()
    {
        return _label;
    }

    public Painter getPainter()
    {
        return _label.getPainter();
    }

    public Sound getSound()
    {
        return _sound;
    }

    public String getText()
    {
        return _label.getText();
    }

    public int getTextColor()
    {
        return _label.getColor();
    }

    public Typeface getTypeface()
    {
        return _label.getTypeface();
    }

    public void onAction()
    {
        if(!(visible && enabled))
            return;
        applyAnimation();
        onClick = false;
        if(!focusClick)
        {
            if(!pressed)
            {
                if(!TouchScreen.pressed || intersectPoint(TouchScreen.x, TouchScreen.y))
                    return;
                pressed = false;
                computeTextOffsets();
                computeIconOffsets();
                return;
            }
            if(!TouchScreen.eventUp)
            {
                if(!TouchScreen.eventDown || !intersectPoint(TouchScreen.x, TouchScreen.y))
                    return;
                pressed = true;
                computeTextOffsets();
                computeIconOffsets();
                return;
            }
            if(_sound != null)
            {
                _sound.play();
            }
            if(Game.opHaptic)
            {
                Game.vibrate(25L);
            }
            pressed = false;
            onClick = true;
            computeTextOffsets();
            computeIconOffsets();
            if(!switchable)
                return;
            boolean flag = switched;
            boolean flag1 = false;
            if(!flag)
            {
                flag1 = true;
            }
            switched = flag1;
            return;
        }
        focusClick = false;
        pressed = false;
        onClick = true;
        if(switchable)
        {
            boolean flag2 = switched;
            boolean flag3 = false;
            if(!flag2)
            {
                flag3 = true;
            }
            switched = flag3;
        }
    }

    protected void onDrawFocus()
    {
        Game.drawRect((int)x, (int)y, w, h, Debug.focusRect);
    }

    public void onRender()
    {
        if(!visible)
            return;
        update();
        if(enabled)
        {
            if(pressed)
            {
                if(bmpPressed != null)
                {
                    drawBackGround(bmpPressed);
                }
            } else
            if(switched)
            {
                if(bmpSelected != null)
                {
                    drawBackGround(bmpSelected);
                }
            } else
            if(bmpNormal != null)
            {
                drawBackGround(bmpNormal);
            }
        }else {
            if (bmpDisabled != null) {
                drawBackGround(bmpDisabled);
            }
        }
        if(selected)
        {
            onDrawFocus();
        }
        if(bmpIcon != null)
        {
            Game.drawBitmap(bmpIcon, (int)x + _iconOfsX, (int)y + _iconOfsY);
        }
        if(_label.getText() != null)
        {
            _label.onRender();
        }
    }

    public void setBackgrounds(Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3)
    {
        bmpNormal = bitmap;
        bmpPressed = bitmap2;
        bmpSelected = bitmap1;
        bmpDisabled = bitmap3;
        if(bmpSelected == null)
        {
            bmpSelected = bmpNormal;
        }
        if(bmpPressed == null)
        {
            bmpPressed = bmpNormal;
        }
        if(bmpDisabled == null)
        {
            bmpDisabled = bmpNormal;
        }
    }

    public void setBevelX(int i)
    {
        _bevelX = i;
    }

    public void setBevelY(int i)
    {
        _bevelY = i;
    }

    public void setIcon(Bitmap bitmap)
    {
        bmpIcon = bitmap;
        computeIconOffsets();
    }

    public void setIconAlign(int i)
    {
        _iconAlign = i;
        computeIconOffsets();
    }

    public void setIconPadding(int i)
    {
        _iconPadding = i;
        computeIconOffsets();
    }

    public void setIconTextAlign(boolean flag)
    {
        _iconTextAlign = flag;
        computeIconOffsets();
    }

    public void setNinePatch(boolean flag)
    {
        ninePatch = flag;
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
        _label.setText(s);
        computeTextOffsets();
        computeIconOffsets();
    }

    public void setTextColor(int i)
    {
        _label.setColor(i);
    }

    public void setTextSize(float f)
    {
        _label.setSize(f);
        computeTextOffsets();
        computeIconOffsets();
    }

    public void setTypeface(Typeface typeface)
    {
        _label.setTypeface(typeface);
        computeTextOffsets();
        computeIconOffsets();
    }

    public void setVisible(boolean flag)
    {
        super.setVisible(flag);
        if(!flag)
        {
            onClick = false;
        }
    }

    public void update()
    {
        if(hasMoved || isResized)
        {
            computeTextOffsets();
            computeIconOffsets();
            hasMoved = false;
            isResized = false;
        }
    }

    public boolean useNinePatch()
    {
        return ninePatch;
    }
}

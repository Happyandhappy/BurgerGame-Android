package com.magmamobile.game.engine;

import android.graphics.Paint;

public class StrokeTextPainter
{

    protected android.graphics.Paint.Align _align;
    protected boolean _bold;
    protected int _fillColor;
    protected Paint _paint1;
    protected Paint _paint2;
    protected float _size;
    protected int _strokeColor;
    protected float _strokeWidth;
    protected String _text;

    public StrokeTextPainter()
    {
        _size = 20F;
        _text = null;
        _bold = false;
        _align = android.graphics.Paint.Align.LEFT;
        _fillColor = 0xff000000;
        _strokeColor = -1;
        _paint1 = Label.createLabelPaint(_size, _fillColor, _bold, false, false);
        _paint2 = Label.createLabelPaint(_size, _strokeColor, _bold, false, false);
        _paint1.setStyle(android.graphics.Paint.Style.FILL);
        _paint2.setStyle(android.graphics.Paint.Style.STROKE);
    }

    public void draw(int i, int j)
    {
        if(_text != null)
        {
            Game.drawText(_text, i, j, _paint1);
            Game.drawText(_text, i, j, _paint2);
        }
    }

    public android.graphics.Paint.Align getAlign()
    {
        return _align;
    }

    public int getFillColor()
    {
        return _fillColor;
    }

    public float getSize()
    {
        return _size;
    }

    public int getStrokeColor()
    {
        return _strokeColor;
    }

    public float getStrokeWidth()
    {
        return _strokeWidth;
    }

    public String getText()
    {
        return _text;
    }

    public boolean isBold()
    {
        return _bold;
    }

    public void setAlign(android.graphics.Paint.Align align)
    {
        _align = align;
        _paint1.setTextAlign(_align);
        _paint2.setTextAlign(_align);
    }

    public void setBold(boolean flag)
    {
        _bold = flag;
        _paint1.setFakeBoldText(_bold);
        _paint2.setFakeBoldText(_bold);
    }

    public void setFillColor(int i)
    {
        _fillColor = i;
        _paint1.setColor(_fillColor);
    }

    public void setSize(float f)
    {
        _size = f;
        _paint1.setTextSize(_size);
        _paint2.setTextSize(_size);
    }

    public void setStrokeColor(int i)
    {
        _strokeColor = i;
        _paint2.setColor(_strokeColor);
    }

    public void setStrokeWidth(float f)
    {
        _strokeWidth = f;
        _paint2.setStrokeWidth(_strokeWidth);
    }

    public void setText(String s)
    {
        _text = s;
    }
}

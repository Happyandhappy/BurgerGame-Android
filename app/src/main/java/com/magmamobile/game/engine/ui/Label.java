package com.magmamobile.game.engine.ui;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.TextUtils;

public class Label extends Component
{

    private Paint _paint;
    private String _text;
    private int _textColor;
    private float _textSize;
    private Typeface _typeface;

    public Label()
    {
        _paint = new Paint();
        _typeface = Typeface.DEFAULT;
    }

    public String getText()
    {
        return _text;
    }

    public int getTextColor()
    {
        return _textColor;
    }

    public float getTextSize()
    {
        return (float)_textColor;
    }

    public Typeface getTypeface()
    {
        return _typeface;
    }

    protected void onDraw()
    {
        if(_text == null)
        {
            return;
        } else
        {
            Game.drawText(_text, (int)_left, (int)_top, _paint);
            return;
        }
    }

    public void setText(String s)
    {
        if(TextUtils.isEqual(s, _text))
        {
            return;
        } else
        {
            _text = s;
            _dirty = true;
            return;
        }
    }

    public void setTextColor(int i)
    {
        if(_textColor != i)
        {
            _textColor = i;
            _paint.setColor(_textColor);
        }
    }

    public void setTextSize(float f)
    {
        if(_textSize != f)
        {
            _textSize = f;
            _paint.setTextSize(_textSize);
            _dirty = true;
        }
    }

    public void setTypeface(Typeface typeface)
    {
        _typeface = typeface;
        _paint.setTypeface(_typeface);
        _dirty = true;
    }
}

package com.magmamobile.game.engine;

import android.graphics.LinearGradient;
import android.graphics.Paint;

public class GradientTextPainter extends StrokeTextPainter
{

    private int _color1;
    private int _color2;
    private int _lastX;
    private int _lastY;

    public GradientTextPainter()
    {
        _color1 = -1;
        _color2 = 0xff000000;
        _paint1.setShader(new LinearGradient(0.0F, (float)_lastY - _size, 0.0F, _lastY, _color1, _color2, android.graphics.Shader.TileMode.CLAMP));
    }

    public void draw(int i, int j)
    {
        if(i != _lastX || j != _lastY)
        {
            _lastX = i;
            _lastY = j;
            _paint1.setShader(new LinearGradient(0.0F, (float)_lastY - _size, 0.0F, _lastY, _color1, _color2, android.graphics.Shader.TileMode.CLAMP));
        }
        if(_text != null)
        {
            Game.drawText(_text, i, j, _paint1);
            Game.drawText(_text, i, j, _paint2);
        }
    }

    public int getColor1()
    {
        return _color1;
    }

    public int getColor2()
    {
        return _color2;
    }

    public void setColor1(int i)
    {
        _color1 = i;
    }

    public void setColor2(int i)
    {
        _color2 = i;
    }
}

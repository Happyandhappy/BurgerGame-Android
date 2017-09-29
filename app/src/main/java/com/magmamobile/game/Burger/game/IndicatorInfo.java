package com.magmamobile.game.Burger.game;

import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;

public class IndicatorInfo
{

    public int decalX;
    public int decalY;
    private int dx;
    private int dy;
    private float f;
    private Paint p1;
    private Paint p2;
    private int phase;
    public boolean stroked;
    private int value;
    public int x;
    public int y;

    public IndicatorInfo()
    {
        p1 = new Paint();
        p1.setTypeface(App.defaultFont);
        p1.setTextSize(App.scalefFont(32F));
        p1.setColor(-1);
        p1.setTextAlign(android.graphics.Paint.Align.CENTER);
        p2 = App.getStroked(p1, Game.scalef(4F), 0xff338eff);
        phase = 0;
    }

    public void animate()
    {
        if(phase != 0)
        {
            f = 0.075F + f;
            if(f >= 1.0F)
            {
                f = 1.0F;
                phase = 0;
            }
            int i;
            if(f >= 0.5F)
            {
                i = 255 - (int)(512F * (f - 0.5F));
            } else
            {
                i = 255;
            }
            p1.setAlpha(i);
            dx = (int)MathUtils.lerpAccelerate(0.0F, decalX, f);
            dy = (int)MathUtils.lerpAccelerate(0.0F, decalY, f);
        }
    }

    public void draw()
    {
        if(phase != 0)
        {
            if(stroked)
            {
                Game.drawText((new StringBuilder("+")).append(value).toString(), x + dx, y + dy, p2);
            }
            Game.drawText((new StringBuilder("+")).append(value).toString(), x + dx, y + dy, p1);
        }
    }

    public boolean isPlaying()
    {
        return phase != 0;
    }

    public void setColor(int i)
    {
        p1.setColor(i);
    }

    public void setColor(int i, int j)
    {
        p1.setColor(i);
        p2.setColor(j);
    }

    public void showValue(int i)
    {
        value = i;
        dy = 0;
        f = 0.0F;
        phase = 2;
        p1.setAlpha(255);
    }
}

package com.magmamobile.game.Burger.ui;

import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.engine.Game;

public class MenuButton
{

    private final int DISABLED_COLOR = 0xffc0c0c0;
    public boolean enabled;
    private int f;
    public int height;
    public boolean inverted;
    public String label;
    private int maxWidth;
    public Paint pStroke;
    public Paint pText;
    private int phase;
    private int strokeColor;
    private int textColor;
    private int th;
    private int tx;
    private int ty;
    public boolean visible;
    public int width;
    public int x;
    public int y;

    public MenuButton()
    {
        phase = 0;
        pText = new Paint();
        pText.setAntiAlias(true);
        pText.setTypeface(App.defaultFont);
        pStroke = App.getStroked(pText);
        init();
    }

    private void computeScaleX()
    {
        float f1 = 1.0F;
        if(label != null && maxWidth > 0)
        {
            pText.setTextScaleX(f1);
            int i = Game.getTextWidth(label, pText);
            if(i > maxWidth)
            {
                f1 = (float)maxWidth / (float)i;
            }
            pText.setTextScaleX(f1);
            pStroke.setTextScaleX(f1);
        }
    }

    private void setTextColor()
    {
        if(inverted)
        {
            pText.setColor(strokeColor);
            pStroke.setColor(textColor);
            return;
        } else
        {
            pText.setColor(textColor);
            pStroke.setColor(strokeColor);
            return;
        }
    }

    public void animate()
    {
        f = -1 + f;
        if(f % 3 == 0)
        {
            invertColor();
        }
        if(f == 0)
        {
            phase = 5;
        }
    }

    public void center(int i, int j)
    {
        tx = i;
        ty = j;
        pText.setTextAlign(android.graphics.Paint.Align.CENTER);
        pStroke.setTextAlign(android.graphics.Paint.Align.CENTER);
    }

    public void centerRect(int i, int j)
    {
        width = i;
        height = j;
        x = tx - width / 2;
        y = (ty - height) + (height - th) / 2;
    }

    public void draw()
    {
        if(visible)
        {
            Game.drawText(label, tx, ty, pStroke);
            Game.drawText(label, tx, ty, pText);
        }
    }

    public int getTextHeight()
    {
        return Game.getTextHeight(label, pText);
    }

    public void init()
    {
        visible = true;
        f = 0;
        setEnabled(true);
        reinitColor();
    }

    public void invertColor()
    {
        boolean flag;
        if(inverted)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        inverted = flag;
        setTextColor();
    }

    public boolean isFinished()
    {
        return phase == 5;
    }

    public boolean isPlaying()
    {
        return phase != 0;
    }

    public boolean isReactive()
    {
        return enabled && visible;
    }

    public void left(int i, int j)
    {
        tx = i;
        ty = j;
        pText.setTextAlign(android.graphics.Paint.Align.LEFT);
        pStroke.setTextAlign(android.graphics.Paint.Align.LEFT);
    }

    public void play()
    {
        f = 15;
        phase = 6;
    }

    public void rect(int i, int j)
    {
        width = i;
        height = j;
        x = 0;
        y = (ty - height) + (height - th) / 2;
    }

    public void reinitColor()
    {
        if(enabled)
        {
            inverted = false;
            setTextColor();
        }
    }

    public void setColors(int i, int j)
    {
        textColor = i;
        strokeColor = j;
        setTextColor();
    }

    public void setEnabled(boolean flag)
    {
        if(!enabled && flag)
        {
            enabled = true;
            setTextColor();
        } else
        if(enabled && !flag)
        {
            enabled = false;
            inverted = false;
            pText.setColor(-1);
            pStroke.setColor(0xffc0c0c0);
            return;
        }
    }

    public void setLabel(String s)
    {
        label = s;
        computeScaleX();
    }

    public void setMaxWidth(int i)
    {
        maxWidth = i;
        computeScaleX();
    }

    public void setSize(float f1, float f2)
    {
        pText.setTextSize(f1);
        pStroke.setTextSize(f1);
        pStroke.setStrokeWidth(f2);
        if(label != null)
        {
            th = Game.getTextHeight(label, pText);
        }
        computeScaleX();
    }

    public void stop()
    {
        f = 0;
        phase = 0;
    }
}

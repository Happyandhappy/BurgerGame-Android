package com.magmamobile.game.engine.tmp.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.magmamobile.game.engine.Game;

public class NormalTextDrawer extends TextDrawer
{

    private Paint p;

    public NormalTextDrawer(int i, int j, boolean flag, android.graphics.Paint.Align align)
    {
        p = new Paint();
        p.setColor(j);
        p.setTextSize(i);
        p.setTextAlign(align);
        p.setAntiAlias(true);
        p.setFilterBitmap(true);
        p.setFakeBoldText(flag);
        p.setTypeface(Game.typeface);
    }

    public void draw(String s, int i, int j)
    {
        Game.mCanvas.drawText(s, i, j, p);
    }
}

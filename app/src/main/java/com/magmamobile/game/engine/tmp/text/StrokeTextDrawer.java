package com.magmamobile.game.engine.tmp.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.magmamobile.game.engine.Game;

public class StrokeTextDrawer extends TextDrawer
{

    private Paint p;
    private Paint s;

    public StrokeTextDrawer(int i, int j, boolean flag, android.graphics.Paint.Align align, int k, float f)
    {
        p = new Paint();
        p.setColor(j);
        p.setTextSize(i);
        p.setTextAlign(align);
        p.setAntiAlias(true);
        p.setFilterBitmap(true);
        p.setFakeBoldText(flag);
        p.setTypeface(Game.typeface);
        s = new Paint();
        s.setColor(k);
        s.setTextSize(i);
        s.setTextAlign(align);
        s.setAntiAlias(true);
        s.setFilterBitmap(true);
        s.setFakeBoldText(flag);
        s.setTypeface(Game.typeface);
    }

    public void draw(String s1, int i, int j)
    {
        int k = -1;
        while(k <= 1)
        {
            int l = -1;
            while(l <= 1)
            {
                Game.mCanvas.drawText(s1, k + i, l + j, s);
                l++;
            }
            k++;


        }
        Game.mCanvas.drawText(s1, i, j, p);
    }
}

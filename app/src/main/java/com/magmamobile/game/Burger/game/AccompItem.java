package com.magmamobile.game.Burger.game;

import android.graphics.Matrix;
import com.magmamobile.game.Burger.stages.Board;
import com.magmamobile.game.engine.MathUtils;
import com.magmamobile.game.engine.math.Ease;

public class AccompItem
{

    private final float SPEED_IN = 0.05F;
    private final float SPEED_OUT = 0.15F;
    public boolean added;
    public int cx;
    public int cy;
    public int dx;
    public int dy;
    private float f;
    public Matrix mtx;
    private int phase;
    private float scale;
    private float scaleX;
    public int type;
    public int x;
    public int y;

    public AccompItem()
    {
        mtx = new Matrix();
    }

    private void setMatrix(float f1, float f2)
    {
        mtx.setTranslate(cx, cy);
        mtx.postScale(f1, f2, cx, cy);
        mtx.postTranslate(-f1 * (float)dx, -f2 * (float)dy);
    }

    public void animate()
    {
        switch(phase)
        {
        default:
            mtx.reset();
            phase = 0;
            return;

        case 1: // '\001'
            f = 0.05F + f;
            if(f > 1.0F)
            {
                scale = 1.0F;
                phase = 0;
            } else
            {
                scale = Ease.OutElastic(f, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F);
            }
            scaleX = 1.0F / scale;
            setMatrix(scaleX, scale);
            return;

        case 2: // '\002'
            f = 0.15F + f;
            break;
        }
        if(f > 1.0F)
        {
            scale = 0.0F;
            phase = 0;
            added = false;
            Board.activate();
        } else
        {
            scale = MathUtils.lerpAccelerate(1.0F, 0.0F, f);
        }
        setMatrix(scale, scale);
    }

    public void clear()
    {
        scale = 0.0F;
        type = 0;
        added = false;
        phase = 0;
        mtx.reset();
    }

    public boolean isPlaying()
    {
        return phase != 0;
    }

    public void moveIn()
    {
        f = 0.0F;
        setMatrix(0.05F, 0.05F);
        phase = 1;
        added = true;
    }

    public void moveOut()
    {
        f = 0.0F;
        setMatrix(1.0F, 1.0F);
        phase = 2;
    }

    public void setCenter(int i, int j)
    {
        cx = i;
        cy = j;
    }

    public void setType(int i)
    {
        type = i - 12;
        dx = Accomp.deltaX[type];
        dy = Accomp.deltaY[type];
        x = cx - dx;
        y = cy - dy;
    }

    public void show()
    {
        added = true;
        phase = 0;
        mtx.reset();
    }
}

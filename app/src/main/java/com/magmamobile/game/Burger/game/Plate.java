package com.magmamobile.game.Burger.game;

import android.graphics.Matrix;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.engine.MathUtils;

public class Plate
{

    private final float SPEED = 0.15F;
    public boolean added;
    public int cx;
    public int cy;
    public int dx;
    public int dy;
    private float f;
    public Matrix mtx;
    public int phase;
    public float scale;
    public boolean visible;
    public int x;
    public int y;

    public Plate()
    {
        visible = true;
        mtx = new Matrix();
    }

    private void setMatrix(float f1)
    {
        mtx.setTranslate(cx, cy);
        mtx.postScale(f1, f1, cx, cy);
        mtx.postTranslate(-f1 * (float)dx, -f1 * (float)dy);
    }

    public void animate()
    {
        switch(phase) {
            default:
                scale = 1.0F;
                break;
            case 1:
                f = 0.15F + f;
                if (f > 1.0F) {
                    phase = 0;
                    scale = 1.0F;
                } else {
                    scale = MathUtils.lerpOvershoot(0.0F, 1.0F, f);
                }
                break;
            case 2:
                f = 0.15F + f;
                if (f > 1.0F) {
                    phase = 0;
                    scale = 0.0F;
                    added = false;
                } else {
                    scale = MathUtils.lerpAccelerate(1.0F, 0.0F, f);
                }
                break;
        }
        setMatrix(scale);
    }

    public void clear()
    {
        visible = true;
        added = false;
        scale = 0.0F;
        phase = 0;
        mtx.reset();
    }

    public boolean isPlaying()
    {
        return phase != 0;
    }

    public void moveIn()
    {
        phase = 1;
        f = 0.0F;
        setMatrix(0.0F);
        added = true;
        visible = true;
    }

    public void moveOut()
    {
        phase = 2;
        setMatrix(1.0F);
        f = 0.0F;
        if(!PrefManager.configs[3])
        {
            visible = false;
        }
    }

    public void setCenter(int i, int j)
    {
        cx = i;
        cy = j;
        x = cx - dx;
        y = cy - dy;
    }
}

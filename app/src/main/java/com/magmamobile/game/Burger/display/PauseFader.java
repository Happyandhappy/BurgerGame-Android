package com.magmamobile.game.Burger.display;

import android.graphics.Color;
import com.magmamobile.game.Burger.managers.UIManager;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;

public class PauseFader
{

    public int bgAlpha;
    private float f;
    private int phase;

    public PauseFader()
    {
        f = 0.0F;
        phase = 0;
    }

    public void animate()
    {
        switch(phase)
        {
        default:
            return;

        case 1: // '\001'
            f = 0.2F + f;
            if(f < 1.0F)
            {
                bgAlpha = Math.round(255F * MathUtils.lerpLinear(0.0F, 1.0F, f));
                return;
            } else
            {
                bgAlpha = 255;
                phase = 5;
                UIManager.backButtonActive = true;
                return;
            }

        case 2: // '\002'
            f = 0.2F + f;
            break;
        }
        if(f < 1.0F)
        {
            bgAlpha = Math.round(255F * MathUtils.lerpLinear(1.0F, 0.0F, f));
            return;
        } else
        {
            bgAlpha = 0;
            phase = 5;
            UIManager.backButtonActive = true;
            return;
        }
    }

    public void draw()
    {
        Game.drawColor(Color.argb(bgAlpha, 0, 0, 0));
    }

    public void fadeIn()
    {
        f = 0.0F;
        phase = 2;
        bgAlpha = 255;
    }

    public void fadeOut()
    {
        f = 0.0F;
        phase = 1;
        bgAlpha = 0;
    }

    public boolean isFinished()
    {
        return phase == 5;
    }

    public boolean isPlaying()
    {
        return phase != 0;
    }

    public void stop()
    {
        phase = 0;
    }
}

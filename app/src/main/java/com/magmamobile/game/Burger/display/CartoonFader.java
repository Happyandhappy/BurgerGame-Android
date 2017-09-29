package com.magmamobile.game.Burger.display;

import android.graphics.*;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.managers.SoundManager;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;

public class CartoonFader
{

    private final float SPEED = 0.075F;
    public int color;
    private int cx;
    private int cy;
    private float f;
    private int height;
    public boolean keepClosed;
    private float maxRadius;
    private int nextPhase;
    public boolean opaque;
    public Paint paint;
    public Path path;
    private int phase;
    private float radius;
    private boolean saved;
    private Paint stroke;
    private int w;
    private int width;

    public CartoonFader()
    {
        width = Game.scalei(480);
        height = Game.scalei(320);
        cx = width / 2;
        cy = height / 2;
        maxRadius = 1.25F * (float)width;
        color = 0xff000000;
        path = new Path();
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        stroke = new Paint(paint);
        stroke.setStyle(android.graphics.Paint.Style.STROKE);
        stroke.setStrokeWidth(2.0F);
    }

    public void animate()
    {
        float f1;
label0:
        {
label1:
            {
                f1 = 1.0F;
                if(phase != 4)
                {
                    break label0;
                }
                w = -1 + w;
                if(w <= 0)
                {
                    if(nextPhase != 1)
                    {
                        break label1;
                    }
                    fadeIn();
                }
                return;
            }
            fadeOut();
            return;
        }
        f = 0.075F + f;
        if(f >= f1)
        {
            f = f1;
            if(phase != 2)
            {
                f1 = maxRadius;
            }
            radius = f1;
            phase = 5;
        }
        switch(phase){
            default:
                break;
            case 1:
                radius = MathUtils.lerpAccelerate(0.0F, maxRadius, f);
                break;
            case 2:
                radius = MathUtils.lerpDecelerate(maxRadius, 0.0F, f);
                break;
        }
        path.rewind();
        path.addCircle(cx, cy, radius, android.graphics.Path.Direction.CCW);
    }

    public void apply()
    {
        if(isPlaying() && !isWaiting() || keepClosed)
        {
            Game.drawColor(App.fader.color);
            Game.mCanvas.save();
            Game.mCanvas.clipPath(App.fader.path);
            saved = true;
        }
    }

    public void fadeIn()
    {
        fadeIn(false);
    }

    public void fadeIn(boolean flag)
    {
        opaque = false;
        keepClosed = false;
        if(Game.getPrefBoolean("prefAnimate", true))
        {
            f = 0.0F;
            phase = 1;
            if(!flag)
            {
                SoundManager.playSound(26);
            }
            return;
        } else
        {
            phase = 5;
            return;
        }
    }

    public void fadeOut()
    {
        fadeOut(false);
    }

    public void fadeOut(boolean flag)
    {
        opaque = true;
        keepClosed = false;
        if(Game.getPrefBoolean("prefAnimate", true))
        {
            f = 0.0F;
            phase = 2;
            path.addCircle(cx, cy, maxRadius, android.graphics.Path.Direction.CCW);
            if(!flag)
            {
                SoundManager.playSound(27);
            }
        } else
        {
            phase = 5;
        }
    }

    public void init()
    {
        phase = 0;
        f = 0.0F;
    }

    public boolean isFinished()
    {
        return phase == 5;
    }

    public boolean isPlaying()
    {
        return phase != 0;
    }

    public boolean isWaiting()
    {
        return phase == 4;
    }

    public void restore()
    {
        if(saved)
        {
            Game.mCanvas.restore();
            Game.mCanvas.drawPath(path, stroke);
            saved = false;
        }
    }

    public void stop()
    {
        phase = 0;
        path.rewind();
    }

    public void wait(int i, int j)
    {
        w = i;
        nextPhase = j;
        phase = 4;
    }
}

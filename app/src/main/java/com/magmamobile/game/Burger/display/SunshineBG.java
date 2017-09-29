package com.magmamobile.game.Burger.display;

import android.graphics.*;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.engine.Game;

public class SunshineBG
{

    public static final int COLORS[] = {
        0xff84c3f7, -15294, 0xffb06000
    };
    private final int GRADIENT_COLORS[][] = {
        {
            -1, 0xff84c3f7
        }, {
            -1, -15294
        }, {
            -1, 0xfff7d452, 0xffed9017, 0xffed9017
        }
    };
    private final float SPEED = 1.0F;
    private Canvas canvas;
    public int currentColor;
    private RadialGradient g;
    public int id;
    private Matrix mtx;
    private Paint pGradient;
    private Paint pShine;
    private Path path;

    public SunshineBG()
    {
        pShine = new Paint();
        pShine.setAntiAlias(true);
        pGradient = new Paint();
        pGradient.setAntiAlias(true);
        setGradient();
        mtx = new Matrix();
        path = new Path();
        drawPath();
    }

    private void drawPath()
    {
        int i = Game.mBufferDiagonal / 2;
        float f = (float)(Math.sin(0.1308996938995747D) * (double)i);
        int j = 0;
        do
        {
            if(j >= 12)
            {
                mtx.setRotate(1.0F, Game.mBufferCW, Game.mBufferCH);
                return;
            }
            mtx.setRotate(30F, Game.mBufferCW, Game.mBufferCH);
            path.transform(mtx);
            path.moveTo(Game.mBufferCW, Game.mBufferCH);
            path.lineTo(i + Game.mBufferCW, (float)Game.mBufferCH - f);
            path.lineTo(i + Game.mBufferCW, f + (float)Game.mBufferCH);
            path.close();
            j++;
        } while(true);
    }

    private void setGradient()
    {
        g = new RadialGradient(Game.mBufferCW, Game.mBufferCH, Game.mBufferCW, GRADIENT_COLORS[id], null, android.graphics.Shader.TileMode.CLAMP);
    }

    public void draw()
    {
        Game.drawBitmap(BitmapManager.background);
        Game.drawPath(path, pShine);
    }

    public void rotate()
    {
        if(PrefManager.configs[3])
        {
            path.transform(mtx);
        }
    }

    public void setColor(int i)
    {
        id = i;
        currentColor = COLORS[i];
        pShine.setColor(currentColor);
        setGradient();
        pGradient.setShader(g);
        BitmapManager.setBackground(7);
        canvas = new Canvas(BitmapManager.background);
        canvas.drawPaint(pGradient);
    }

}

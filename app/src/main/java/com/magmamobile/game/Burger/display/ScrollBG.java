package com.magmamobile.game.Burger.display;

import android.graphics.*;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.engine.Game;

public class ScrollBG
{

    public static final int COLOR_CONFIG = 2;
    public static final int COLOR_GALLERY = 6;
    public static final int COLOR_GOODJOB = 4;
    public static final int COLOR_HOME = 1;
    public static final int COLOR_MODE = 3;
    public static final int COLOR_QUITGAME = 5;
    private static final int colors[] = {
        0xffc6e3ff, 0xffefffff, 0xffd1f6b8, 0xfffefffe, -14136, -258, -7258, -2, 0xffeec8ff, 0xfffefeff, 
        0xffecd4c1, -258
    };
    private Canvas canvas;
    public int color;
    private int decal;
    private Matrix mBG;
    private Paint pBG;
    private Paint pCanvas;
    private int tileW;
    private int tileW4;

    public ScrollBG()
    {
        tileW = BitmapManager.scrollTiles[0].getWidth();
        tileW4 = 4 * tileW;
        pCanvas = new Paint();
        pCanvas.setFilterBitmap(true);
        pBG = new Paint();
        pBG.setAntiAlias(true);
        mBG = new Matrix();
    }

    public void draw()
    {
        Game.drawPaint(pBG);
    }

    public void scroll()
    {
        try {
            if (PrefManager.configs[3]) {
                decal = -2 + decal;
                decal = decal % tileW4;
                mBG.setTranslate(decal, decal);
                pBG.getShader().setLocalMatrix(mBG);
            }
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setColor(int i)
    {
        int i1;
        int j = i - 1;
        int k = colors[j * 2];
        int l = colors[1 + j * 2];
        canvas = new Canvas(BitmapManager.tiledBG);
        canvas.drawColor(k);
        pCanvas.setColorFilter(new PorterDuffColorFilter(l, android.graphics.PorterDuff.Mode.SRC_ATOP));
        pBG.setShader(new BitmapShader(BitmapManager.tiledBG, android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.REPEAT));
        i1 = 0;
        while(i1 < 8)
        {
            int j1 = 0;
            while(j1 < 8)
            {
                if((j1 + i1) % 2 != 1)
                {
                    int k1 = j1 / 2 + i1 / 2;
                    int l1;
                    int i2;
                    if(i1 % 2 == 0)
                    {
                        l1 = 0;
                    } else
                    {
                        l1 = 3;
                    }
                    i2 = k1 + l1;
                    canvas.drawBitmap(BitmapManager.scrollTiles[i2 % 4], j1 * tileW, i1 * tileW, pCanvas);
                }
                j1++;
            }
            i1++;
        }
    }

}

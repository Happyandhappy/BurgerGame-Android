package com.magmamobile.game.Burger.game;

import android.graphics.*;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;
import java.lang.reflect.Array;

public class MenuInfo
{

    public static int aCoche[][];
    public static boolean aInfo[];
    public static Matrix aMtx[];
    public static Paint aPaint[];
    public static boolean aPlaying[];
    public static float aScaleMax;
    public static float aScaleMin;
    public static Matrix bMtx;
    public static Paint bPaint;
    public static boolean bPlaying;
    public static float bScaleMax;
    public static float bScaleMin;
    public static int baseX;
    public static int baseY;
    public static int bcx;
    public static int bcy;
    public static int bh;
    public static int bw;
    public static int bx;
    public static int by;
    private static int cocheH;
    private static int cocheW;
    public static int currentY;
    private static int decal;
    private static float fa[];
    private static float fb;
    private static float fh;
    private static float fv;
    public static int height;
    private static int nextY;
    private static int phase;
    public static int width;
    public static int x;
    public static int y;

    public MenuInfo()
    {
    }

    public static void animate()
    {
        int i;
        switch(phase) {
            default:
                break;
            case 1:
                fh -= 0.075F;
                if(fh <= 0.0F)
                {
                    fh = 0.0F;
                    phase = 2;
                }
                break;
            case 2:
                fh = 0.075F + fh;
                if(fh >= 1.0F)
                {
                    fh = 1.0F;
                    phase = 1;
                }
                break;
        }
        x = Math.round(MathUtils.lerpDecelerate(baseX, baseX - decal, fh));
        if(fv > 0.0F)
        {
            fv -= 0.2F;
            y = Math.round(MathUtils.lerpAccelerate(nextY, currentY, fv));
        }
        if(bPlaying)
        {
            fb = 0.2F + fb;
            if(fb >= 1.0F)
            {
                fb = 1.0F;
                bPlaying = false;
            }
            float f1 = MathUtils.lerpAccelerate(bScaleMax, bScaleMin, fb);
            bMtx.setTranslate(bcx, bcy);
            bMtx.postScale(f1, f1, bcx, bcy);
            bMtx.postTranslate(-f1 * (float)cocheW, -f1 * (float)cocheH);
            bPaint.setAlpha((int)(255F * fb));
        }
        i = 0;
        while(i < 6)
        {
            if(aInfo[i] && aPlaying[i])
            {
                float af[] = fa;
                af[i] = 0.2F + af[i];
                if(fa[i] >= 1.0F)
                {
                    fa[i] = 1.0F;
                    aPlaying[i] = false;
                }
                float f = MathUtils.lerpAccelerate(aScaleMax, aScaleMin, fa[i]);
                aMtx[i].setTranslate(aCoche[i][0], aCoche[i][1]);
                aMtx[i].postScale(f, f, aCoche[i][0], aCoche[i][1]);
                aMtx[i].postTranslate(-f * (float)cocheW, -f * (float)cocheH);
                aPaint[i].setAlpha((int)(255F * fa[i]));
            }
            i++;
        }
    }

    public static void init()
    {
        decal = Game.scalei(4);
        cocheW = BitmapManager.menuCoche.getWidth() / 2;
        cocheH = BitmapManager.menuCoche.getHeight() / 2;
        aInfo = new boolean[6];
        aPlaying = new boolean[6];
        int ai[] = {
            6, 2
        };
        aCoche = (int[][])Array.newInstance(Integer.TYPE, ai);
        bMtx = new Matrix();
        aMtx = new Matrix[6];
        fa = new float[6];
        bPaint = new Paint();
        bPaint.setAntiAlias(true);
        bPaint.setAlpha(0);
        aPaint = new Paint[6];
        int i = 0;
        do
        {
            if(i >= 6)
            {
                reinit();
                return;
            }
            aMtx[i] = new Matrix();
            aPaint[i] = new Paint(bPaint);
            i++;
        } while(true);
    }

    public static void nextPart(int i)
    {
        currentY = y;
        nextY = i;
        if(PrefManager.configs[3])
        {
            fv = 1.0F;
            return;
        } else
        {
            y = nextY;
            return;
        }
    }

    public static void noAnim()
    {
        x = baseX - decal;
        phase = 1;
        bPlaying = false;
        int i = 0;
        do
        {
            if(i >= 6)
            {
                return;
            }
            aPlaying[i] = false;
            i++;
        } while(true);
    }

    public static void reinit()
    {
        x = baseX;
        y = baseY;
        fb = 0.0F;
        fv = 0.0F;
        fh = 0.0F;
        phase = 2;
        bPlaying = false;
        int i = 0;
        do
        {
            if(i >= 6)
            {
                return;
            }
            aInfo[i] = false;
            aPlaying[i] = false;
            fa[i] = 0.0F;
            i++;
        } while(true);
    }

    public static void validateAccomp(int i)
    {
        aInfo[i] = true;
        aPlaying[i] = true;
        fa[i] = 0.0F;
        Paint paint = aPaint[i];
        int j;
        if(PrefManager.configs[3])
        {
            j = 0;
        } else
        {
            j = 255;
        }
        paint.setAlpha(j);
    }

    public static void validateBurger()
    {
        bPlaying = true;
        fb = 0.0F;
        Paint paint = bPaint;
        int i;
        if(PrefManager.configs[3])
        {
            i = 0;
        } else
        {
            i = 255;
        }
        paint.setAlpha(i);
    }
}

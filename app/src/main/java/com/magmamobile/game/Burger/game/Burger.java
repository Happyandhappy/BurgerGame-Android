package com.magmamobile.game.Burger.game;

import android.graphics.*;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.stages.Board;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;
import com.magmamobile.game.engine.math.Ease;
import java.util.Arrays;

public class Burger
{

    public static int length;
    public final int HEIGHT = 290;
    private final float SPEED_BOING = 0.05F;
    private final float SPEED_IN = 0.25F;
    private final float SPEED_OUT = 0.15F;
    public final int WIDTH = 138;
    private int baseY;
    public int burger[];
    private Canvas canvas;
    public int cx;
    public int cy;
    private int delta[][];
    public int dx;
    public int dy;
    private float f;
    private float from;
    public Matrix mtx;
    public int phase;
    public float scale;
    private int snd[];
    public boolean visible;
    public int x;
    public int y;

    public Burger()
    {
        int ai[] = new int[18];
        ai[2] = 1;
        ai[3] = 1;
        ai[4] = 2;
        ai[5] = 3;
        ai[6] = 4;
        ai[7] = 5;
        ai[8] = 6;
        ai[9] = 7;
        ai[10] = 8;
        ai[11] = 9;
        ai[12] = 10;
        ai[13] = 11;
        ai[14] = 12;
        ai[15] = 13;
        ai[16] = 14;
        ai[17] = 15;
        snd = ai;
        int ai1[][] = new int[12][];
        int ai2[] = new int[3];
        ai2[1] = 4;
        ai2[2] = 13;
        ai1[0] = ai2;
        int ai3[] = new int[3];
        ai3[1] = 5;
        ai1[1] = ai3;
        ai1[2] = (new int[] {
            -1, -4, 6
        });
        int ai4[] = new int[3];
        ai4[1] = -3;
        ai4[2] = 6;
        ai1[3] = ai4;
        int ai5[] = new int[3];
        ai5[1] = 6;
        ai5[2] = 6;
        ai1[4] = ai5;
        int ai6[] = new int[3];
        ai6[1] = 6;
        ai6[2] = 7;
        ai1[5] = ai6;
        int ai7[] = new int[3];
        ai7[0] = -2;
        ai7[2] = 7;
        ai1[6] = ai7;
        int ai8[] = new int[3];
        ai8[1] = 7;
        ai8[2] = 6;
        ai1[7] = ai8;
        int ai9[] = new int[3];
        ai9[1] = 4;
        ai9[2] = 17;
        ai1[8] = ai9;
        int ai10[] = new int[3];
        ai10[1] = -6;
        ai10[2] = 6;
        ai1[9] = ai10;
        int ai11[] = new int[3];
        ai11[1] = -10;
        ai11[2] = 4;
        ai1[10] = ai11;
        ai1[11] = (new int[] {
            2, -4, 10
        });
        delta = ai1;
        visible = true;
        mtx = new Matrix();
        burger = new int[15];
        clearData();
        BitmapManager.burger = Bitmap.createBitmap(Game.scalei(138), Game.scalei(290), android.graphics.Bitmap.Config.ARGB_8888);
        canvas = new Canvas(BitmapManager.burger);
        int i = 0;
        do
        {
            if(i >= delta.length)
            {
                return;
            }
            int ai12[][] = delta;
            int ai13[] = new int[3];
            ai13[0] = Game.scalei(delta[i][0]);
            ai13[1] = Game.scalei(delta[i][1]);
            ai13[2] = Game.scalei(delta[i][2]);
            ai12[i] = ai13;
            i++;
        } while(true);
    }

    private void drawItem(int i)
    {
        float f1 = (BitmapManager.burger.getWidth() - BitmapManager.burgerParts[i].getWidth()) / 2 + delta[i][0];
        float f2 = BitmapManager.burger.getHeight() - BitmapManager.burgerParts[i].getHeight() - (delta[i][1] + baseY);
        baseY = baseY + delta[i][2];
        canvas.drawBitmap(BitmapManager.burgerParts[i], f1, f2, null);
    }

    private void rebuild()
    {
        clearView();
        int i = 0;
        do
        {
            if(i >= length)
            {
                return;
            }
            drawItem(burger[i]);
            i++;
        } while(true);
    }

    private void setMatrix(float f1, float f2)
    {
        mtx.setTranslate(cx, cy);
        mtx.postScale(f1, f2, cx, cy);
        mtx.postTranslate(-f1 * (float)dx, -f2 * (float)dy);
    }

    public void addItem(int i)
    {
        visible = true;
        if(length < 15)
        {
            burger[length] = i;
            length = 1 + length;
            drawItem(i);
            if(PrefManager.configs[3])
            {
                if(length == 1)
                {
                    moveIn();
                } else
                {
                    bounce();
                }
            } else
            {
                setMatrix(1.0F, 1.0F);
            }
            SoundManager.playSound(snd[i]);
            VibrationManager.vibrateItem(i);
        }
    }

    public void animate()
    {
        switch(phase)
        {
        default:
            phase = 0;
            setMatrix(1.0F, 1.0F);
            return;

        case 1: // '\001'
            f = 0.25F + f;
            if(f > 1.0F)
            {
                phase = 0;
                scale = 1.0F;
            } else
            {
                scale = MathUtils.lerpDecelerate(0.0F, 1.0F, f);
            }
            setMatrix(scale, scale);
            return;

        case 2: // '\002'
            f = 0.15F + f;
            if(f > 1.0F)
            {
                clear();
                phase = 0;
                scale = 0.0F;
                Board.activate();
            } else
            {
                scale = MathUtils.lerpAccelerate(1.0F, 0.0F, f);
            }
            setMatrix(scale, scale);
            return;

        case 3: // '\003'
            f = 0.05F + f;
            break;
        }
        if(f >= 1.0F)
        {
            phase = 0;
            setMatrix(1.0F, 1.0F);
            return;
        } else
        {
            scale = 1.0F + 0.1F * Ease.OutElastic(f, from, -1F, 1.0F, 0.0F, 0.0F);
            setMatrix(scale, 1.0F / scale);
            return;
        }
    }

    public void bounce()
    {
        f = 0.0F;
        from = scale;
        setMatrix(1.0F, 1.0F);
        phase = 3;
    }

    public void clear()
    {
        clearData();
        clearView();
    }

    public void clearData()
    {
        Arrays.fill(burger, -1);
        length = 0;
    }

    public void clearView()
    {
        phase = 0;
        baseY = 0;
        visible = true;
        BitmapManager.burger.eraseColor(0);
    }

    public int getLastItem()
    {
        return burger[-1 + length];
    }

    public void init()
    {
        clear();
        visible = true;
    }

    public boolean isEmpty()
    {
        return length == 0;
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
        visible = true;
    }

    public void moveOut()
    {
        f = 0.0F;
        setMatrix(1.0F, 1.0F);
        phase = 2;
        if(!PrefManager.configs[3])
        {
            visible = false;
        }
    }

    public void removeLastItem()
    {
        if(length <= 0 || burger[-1 + length] < 0)
        {
            return;
        }
        length = -1 + length;
        burger[length] = -1;
        if(length == 0)
        {
            clear();
            return;
        } else
        {
            rebuild();
            bounce();
            return;
        }
    }

    public void restore(int ai[])
    {
        restore(ai, true);
    }

    public void restore(int ai[], boolean flag)
    {
        if(ai != null)
        {
            clear();
            int i = 0;
            while(i < ai.length && ai[i] >= 0) 
            {
                burger[i] = ai[i];
                length = 1 + length;
                i++;
            }
            rebuild();
            if(flag)
            {
                bounce();
            }
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

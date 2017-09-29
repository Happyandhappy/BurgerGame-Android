package com.magmamobile.game.Burger.ui;

import android.graphics.*;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;

public class Trainer
{

    public static final int DIR_DOWN = 0;
    public static final int DIR_LEFT = 1;
    public static final int DIR_RIGHT = 3;
    public static final int DIR_UP = 2;
    public static final int MAX_LEVEL_INDICATOR = 3;
    public static final int TRAINING_GOAL = 70;
    private final int DECAL = 14;
    private final int MAX_TRAINING = 6;
    private final int SEQUENCE[][][];
    private final int SIDE_ACCOMP = 1;
    private final int SIDE_BURGER = 0;
    public boolean activated;
    public boolean complete;
    public int count;
    public int direction;
    private int dx;
    private int dy;
    private float f;
    private int itemCount;
    public int itemTargeted;
    private Matrix mtx;
    private Paint p;
    private int phase;
    public int sideTargeted;
    public boolean visible;
    private int x;
    private int y;

    public Trainer()
    {
        int ai[][][] = new int[9][][];
        int ai1[][] = new int[2][];
        int ai2[] = new int[3];
        ai2[1] = 8;
        ai2[2] = 1;
        ai1[0] = ai2;
        ai[0] = ai1;
        int ai3[][] = new int[2][];
        int ai4[] = new int[4];
        ai4[1] = 2;
        ai4[2] = 8;
        ai4[3] = 1;
        ai3[0] = ai4;
        ai[1] = ai3;
        int ai5[][] = new int[2][];
        int ai6[] = new int[4];
        ai6[1] = 8;
        ai6[2] = 6;
        ai6[3] = 1;
        ai5[0] = ai6;
        ai[2] = ai5;
        int ai7[][] = new int[2][];
        int ai8[] = new int[5];
        ai8[1] = 6;
        ai8[2] = 8;
        ai8[3] = 2;
        ai8[4] = 1;
        ai7[0] = ai8;
        ai[3] = ai7;
        int ai9[][] = new int[2][];
        int ai10[] = new int[5];
        ai10[1] = 2;
        ai10[2] = 8;
        ai10[3] = 6;
        ai10[4] = 1;
        ai9[0] = ai10;
        ai[4] = ai9;
        int ai11[][] = new int[2][];
        int ai12[] = new int[5];
        ai12[1] = 2;
        ai12[2] = 6;
        ai12[3] = 8;
        ai12[4] = 1;
        ai11[0] = ai12;
        ai[5] = ai11;
        int ai13[][] = new int[2][];
        int ai14[] = new int[6];
        ai14[1] = 6;
        ai14[2] = 8;
        ai14[3] = 2;
        ai14[4] = 6;
        ai14[5] = 1;
        ai13[0] = ai14;
        ai[6] = ai13;
        int ai15[][] = new int[2][];
        int ai16[] = new int[5];
        ai16[1] = 8;
        ai16[2] = 2;
        ai16[3] = 8;
        ai16[4] = 1;
        ai15[0] = ai16;
        ai[7] = ai15;
        int ai17[][] = new int[2][];
        int ai18[] = new int[6];
        ai18[1] = 2;
        ai18[2] = 6;
        ai18[3] = 8;
        ai18[4] = 6;
        ai18[5] = 1;
        ai17[0] = ai18;
        ai[8] = ai17;
        SEQUENCE = ai;
        phase = 1;
        f = 0.0F;
        mtx = new Matrix();
        p = new Paint();
        p.setAntiAlias(true);
        setDirection(0);
    }

    public void animate()
    {
        boolean flag = true;
        switch(phase){
            default:
                break;
            case 1:
                f = f - 0.075F;
                if(f <= 0.0F)
                {
                    f = 0.0F;
                    phase = 2;
                }
                if(f < 0.05F)
                {
                    flag = false;
                }
                visible = flag;
                break;
            case 2:
                f = 0.075F + f;
                if(f >= 1.0F)
                {
                    f = 1.0F;
                    phase = ((flag) ? 1 : 0);
                }
                if(f > 0.05F)
                {
                    flag = false;
                }
                visible = flag;
                break;
        }
        if(PrefManager.configs[3]) {
            switch (direction) {
                default:
                    break;
                case 0:
                    mtx.setTranslate(x, MathUtils.lerpAccelerate(y - dy, y, f));
                    break;
                case 1:
                    mtx.setTranslate(MathUtils.lerpAccelerate(x - dx, x, f), y);
                    break;
                case 2:
                    mtx.setTranslate(x, MathUtils.lerpAccelerate(y, y + dy, f));
                    break;
                case 3:
                    mtx.setTranslate(MathUtils.lerpAccelerate(x, x + dx, f), y);
                    break;
            }
        }
    }

    public void draw()
    {
        switch(direction) {
            default:
                if(!PrefManager.configs[3]) {
                    if(visible)
                    {
                        Game.drawBitmap(BitmapManager.arrowD, x, y);
                        return;
                    }
                }
                Game.drawBitmap(BitmapManager.arrowD, mtx, p);
                break;
            case 3:
                if(PrefManager.configs[3])
                {
                    Game.drawBitmap(BitmapManager.arrowR, mtx, p);
                    return;
                }
                if(visible)
                {
                    Game.drawBitmap(BitmapManager.arrowR, x, y);
                    return;
                }
                break;
        }
    }

    public int[][] getMenu()
    {
        boolean flag = true;
        boolean flag1;
        int ai[][][];
        int i;
        int ai1[][];
        if(count < 6)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        activated = flag1;
        itemCount = 0;
        if(count >= -1 + SEQUENCE.length)
        {
            complete = flag;
        }
        ai = SEQUENCE;
        i = count;
        count = i + 1;
        ai1 = ai[i];
        if(ai1[0] != null)
        {
            flag = false;
        }
        sideTargeted = ((flag) ? 1 : 0);
        itemTargeted = ai1[sideTargeted][0];
        return ai1;
    }

    public void init()
    {
        count = 0;
        itemCount = 0;
        complete = false;
        activated = true;
    }

    public void nextItem()
    {
        itemCount = 1 + itemCount;
        int ai[][] = SEQUENCE[-1 + count];
        switch (sideTargeted) {
            default:
                break;
            case 0:
                if(itemCount < ai[0].length)
                {
                    itemTargeted = ai[0][itemCount];
                    return;
                }
                try
                {
                    if(ai[1] != null)
                    {
                        itemCount = 0;
                        sideTargeted = 1;
                        itemTargeted = ai[1][0];
                        return;
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                    return;
                }
                break;
            case 1:
                if(itemCount < ai[1].length)
                {
                    itemTargeted = ai[1][itemCount];
                }
                break;
        }
    }

    public void offset(int i, int j)
    {
        switch(direction){
            default:
                x = i - dx;
                y = (j - BitmapManager.arrowD.getWidth()) + Game.scalei(10);
                break;
            case 3:
                x = i;
                y = j;
                break;
        }
        mtx.setTranslate(x, y);
    }

    public void setDirection(int i)
    {
        direction = i;
        switch(direction)
        {
        case 2: // '\002'
        default:
            dx = (BitmapManager.boardItems[0].getWidth() - BitmapManager.arrowD.getWidth()) / 2;
            dy = Game.scalei(14);
            return;

        case 1: // '\001'
        case 3: // '\003'
            dx = Game.scalei(14);
            break;
        }
        dy = (BitmapManager.boardItems[0].getHeight() - BitmapManager.arrowD.getWidth()) / 2;
    }
}

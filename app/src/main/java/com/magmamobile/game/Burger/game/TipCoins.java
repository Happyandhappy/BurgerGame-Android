package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.engine.Game;
import java.util.*;

public class TipCoins
{

    private final int ANIM[][];
    private final float FRICTION = 0.99F;
    private final float GRAVITY = 1.5F;
    public final int MAX_COINS = 32;
    private final float PI2 = 1.570796F;
    private final float PI6 = 0.5235988F;
    private final int TOTAL_FRAME = 4;
    public boolean active[];
    public int anim[];
    private Comparator cmp;
    private int coinHeight;
    private int coinWidth;
    private int count;
    public float cycle[];
    public Integer depth[];
    public float dx[];
    public float dy[];
    public float dz[];
    private float e;
    public int face[];
    private int frame;
    public int height[];
    private int i;
    private int id;
    public int length;
    private Random random;
    private int rndX;
    private int rndY;
    public float scale[];
    private int startX;
    private int startY;
    public int step[];
    public int width[];
    public float x[];
    public float y[];

    public TipCoins()
    {
        startX = 360;
        startY = 104;
        rndX = 112;
        rndY = 50;
        int ai[][] = new int[5][];
        int ai1[] = new int[4];
        ai1[1] = 4;
        ai1[2] = 12;
        ai1[3] = 8;
        ai[0] = ai1;
        ai[1] = (new int[] {
            3, 11, 12, 7
        });
        ai[2] = (new int[] {
            1, 5, 12, 9
        });
        ai[3] = (new int[] {
            2, 10, 12, 6
        });
        ai[4] = (new int[] {
            2, 6, 12, 10
        });
        ANIM = ai;
        cmp = new Comparator() {
            public int compare(Integer integer, Integer integer1)
            {
                return Math.round(scale[integer.intValue()] - scale[integer1.intValue()]);
            }

            public int compare(Object obj, Object obj1)
            {
                return compare((Integer)obj, (Integer)obj1);
            }

        };
        random = new Random();
        length = 0;
        x = new float[32];
        y = new float[32];
        width = new int[32];
        height = new int[32];
        scale = new float[32];
        dx = new float[32];
        dy = new float[32];
        dz = new float[32];
        face = new int[32];
        anim = new int[32];
        step = new int[32];
        cycle = new float[32];
        active = new boolean[32];
        startX = Game.scalei(startX);
        startY = Game.scalei(startY);
        rndX = Game.scalei(rndX);
        rndY = Game.scalei(rndY);
        coinWidth = BitmapManager.coin[0].getWidth();
        coinHeight = BitmapManager.coin[0].getHeight();
        depth = new Integer[32];
        i = 0;
        do
        {
            if(i >= 32)
            {
                return;
            }
            depth[i] = Integer.valueOf(i);
            i = 1 + i;
        } while(true);
    }

    public void addCoins(int j)
    {
        count = 0;
        length = j + length;
        int k;
        int l;
        int i1;
        if(length > 32)
        {
            k = 32;
        } else
        {
            k = length;
        }
        length = k;
        i = 0;
        try {
            while (i < j) {
                l = i;
                i1 = count;
                if (l + i1 >= 32) {
                    break;
                }
                while (active[i + count]) {
                    count = 1 + count;
                    if (i + count >= 32) {
                        Arrays.sort(depth, cmp);
                        return;
                    }
                }
                id = i + count;
                active[id] = true;
                x[id] = startX + random.nextInt(rndX);
                y[id] = startY + random.nextInt(rndY);
                scale[id] = 0.5F + random.nextFloat() / 4F;
                width[id] = Math.round(scale[id] * (float) coinWidth);
                height[id] = Math.round(scale[id] * (float) coinHeight);
                cycle[id] = 1.0F + 2.0F * random.nextFloat();
                e = 1.570796F * random.nextFloat();
                dx[id] = 12F * -(float) Math.cos(e);
                dy[id] = 24F * random.nextFloat() - 20F;
                dz[id] = (float) Math.sin(e) / 30F;
                if (e < 0.5235988F)
                    anim[id] = 3;
                else if (e >= 1.047198F)
                    anim[id] = 0;
                else
                    anim[id] = 1;
                step[id] = random.nextInt(4);
                face[id] = ANIM[anim[id]][step[id]];
                i = 1 + i;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        Arrays.sort(depth, cmp);
    }

    public void animate()
    {
        frame = 1 + frame;
        i = 0;
        while(i < 32)
        {
            try
            {
                if(active[i])
                {
                    float af[] = dz;
                    int j = i;
                    af[j] = 0.99F * af[j];
                    float af1[] = scale;
                    int k = i;
                    af1[k] = af1[k] + dz[i];
                    float af2[] = dx;
                    int l = i;
                    af2[l] = 0.99F * af2[l];
                    float af3[] = dy;
                    int i1 = i;
                    af3[i1] = af3[i1] + 1.5F * scale[i];
                    float af4[] = x;
                    int j1 = i;
                    af4[j1] = af4[j1] + dx[i];
                    float af5[] = y;
                    int k1 = i;
                    af5[k1] = af5[k1] + dy[i];
                    width[i] = Math.round((float)coinWidth * scale[i]);
                    height[i] = Math.round((float)coinHeight * scale[i]);
                    float af6[] = cycle;
                    int l1 = i;
                    af6[l1] = 1.001F * af6[l1];
                    if(frame % Math.round(cycle[i]) == 0)
                    {
                        int ai[] = step;
                        int i2 = i;
                        ai[i2] = 1 + ai[i2];
                        int ai1[] = step;
                        int j2 = i;
                        ai1[j2] = ai1[j2] % 4;
                        face[i] = ANIM[anim[i]][step[i]];
                    }
                    if(x[i] > (float)Game.getBufferWidth() || y[i] > (float)Game.getBufferHeight())
                    {
                        active[i] = false;
                        length = -1 + length;
                    }
                }
                i = 1 + i;
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                return;
            }
        }
        Arrays.sort(depth, cmp);
    }

    public boolean hasCoins()
    {
        return length > 0;
    }
}

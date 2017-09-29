package com.magmamobile.game.Burger.game;

import android.graphics.*;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.GameManager;
import com.magmamobile.game.engine.Game;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MenuItem
{

    private float ac;
    public int accompCoord[][];
    private int accompHeight;
    private float accompScale;
    private int accompWidth;
    private float ah;
    private float aw;
    private float ax;
    private float ay;
    private float bh;
    public int burgerAW;
    public int burgerCoord[];
    private float burgerScale;
    private int burgerWidth;
    private float bw;
    private float bx;
    private float by;
    private Canvas canvas;
    private Rect dst;
    public int height;
    public int index;
    public int maxSpacing;
    public int menuHeight;
    public int menuWidth;
    public int menuX;
    public int menuY;
    private Paint paint;
    private float sh;
    private float sp;
    private float sw;
    public int timedX;
    public int timer;
    public boolean validated;
    public int width;
    public int x;
    public int y;

    public MenuItem(int i)
    {
        index = i;
        burgerCoord = new int[15];
        int ai[] = {
            6, 4
        };
        accompCoord = (int[][])Array.newInstance(Integer.TYPE, ai);
        burgerWidth = BitmapManager.menuItems[0].getWidth();
        accompWidth = BitmapManager.menuItems[12].getWidth();
        accompHeight = BitmapManager.menuItems[12].getHeight();
        canvas = new Canvas(BitmapManager.menus[i]);
        paint = new Paint();
        paint.setFilterBitmap(true);
    }

    private void calculateAccomp(int ai[], boolean flag)
    {
        int i = 2;
        int j = ai.length;
        if(flag)
        {
            bx = menuX;
            switch(j)
            {
            default:
                accompScale = 0.61F;
                aw = (float)accompWidth * accompScale;
                ah = (float)accompHeight * accompScale;
                sw = ((float)menuWidth - (bw + aw + aw)) / 2.0F;
                sh = ((float)menuHeight - 3F * ah) / 4F;
                ax = bx + bw + sw;
                ay = (float)menuY + sh;
                ac = ax + (aw + sw) / 2.0F;
                return;

            case 1: // '\001'
            case 2: // '\002'
                accompScale = 0.88F;
                aw = (float)accompWidth * accompScale;
                ah = (float)accompHeight * accompScale;
                if(burgerScale > 0.5F)
                {
                    sw = (float)menuWidth - (bw + aw);
                } else
                {
                    sw = ((float)menuWidth - (bw + aw)) / 3F;
                    bx = bx + sw;
                }
                sh = ((float)menuHeight - ah * (float)j) / (float)(j + 1);
                ax = bx + bw + sw;
                ay = (float)menuY + sh;
                return;

            case 3: // '\003'
                accompScale = 0.68F;
                aw = (float)accompWidth * accompScale;
                ah = (float)accompHeight * accompScale;
                sw = ((float)menuWidth - (bw + aw)) / 3F;
                break;
            }
            if(burgerScale == 0.5F)
            {
                bx = bx + sw;
            }
            sh = ((float)menuHeight - 3F * ah) / 2.0F;
            ax = bx + bw + sw;
            ay = menuY;
            return;
        }
        int k;
        float f;
        float f1;
        float f2;
        float f3;
        if(j < i)
        {
            k = 1;
        } else
        if(j > 4)
        {
            k = 3;
        } else
        {
            k = i;
        }
        if(j >= i && j <= 4)
        {
            i = 3;
        }
        if(j < 3)
        {
            f = 1.0F;
        } else
        if(j > 4)
        {
            f = 0.68F;
        } else
        {
            f = 0.92F;
        }
        accompScale = f;
        aw = (float)accompWidth * accompScale;
        ah = (float)accompHeight * accompScale;
        if(j < 3)
        {
            f1 = ((float)menuWidth - aw) / 2.0F;
        } else
        {
            f1 = ((float)menuWidth - (aw + aw)) / 3F;
        }
        sw = f1;
        sh = ((float)menuHeight - ah * (float)k) / (float)i;
        ax = (float)menuX + sw;
        f2 = menuY;
        if(j < 5)
        {
            f3 = sh;
        } else
        {
            f3 = 0.0F;
        }
        ay = f3 + f2;
        ac = ax + (aw + sw) / 2.0F;
    }

    private void calculateBurger(int ai[], int i)
    {
        float f;
        int j;
        if(i == 0 && ai.length <= 5)
        {
            burgerScale = 1.0F;
        } else
        if(i <= 3 && ai.length < 9)
        {
            burgerScale = 0.75F;
        } else
        {
            burgerScale = 0.5F;
        }
        bw = (float)burgerWidth * burgerScale;
        f = 0.0F;
        j = 0;
        do
        {
            if(j >= ai.length)
            {
                sp = ((float)menuHeight - f) / (float)(-1 + ai.length);
                if(sp > (float)maxSpacing * burgerScale)
                {
                    sp = (float)maxSpacing * burgerScale;
                    bh = f + (float)(-1 + ai.length) * sp;
                    by = (float)menuY + ((float)menuHeight - bh) / 2.0F;
                } else
                {
                    bh = f + (float)(-1 + ai.length) * sp;
                    by = menuY;
                }
                burgerAW = Math.round((float)Game.scalei(32) * burgerScale);
                return;
            }
            f += (float)BitmapManager.menuItems[ai[j]].getHeight() * burgerScale;
            j++;
        } while(true);
    }

    private void drawAccomp(int ai[], boolean flag)
    {
        float f1;
        int ai1[];
        float f2;
        float f3;
        int k;
        int i;
        float f;
        int j;
        i = ai.length;
        f = ax;
        //ay;
        j = 0;
        while(j < i)
        {
            if(!flag) {
                if(i <= 2)
                {
                    f1 = ay + (float)j * (ah + sh);
                } else
                {
                    if(i % 2 == 1 && j == i - 1)
                    {
                        f = ac;
                    } else
                    {
                        f = ax + (float)(j % 2) * (aw + sw);
                    }
                    f1 = (float)((double)ay + Math.floor(j / 2) * (double)(ah + sh));
                }
            } else {

                if (i <= 3) {
                    f1 = ay + (float) j * (ah + sh);
                } else if (i == 4) {
                    if (j == 0 || j == 3) {
                        f = ac;
                    } else {
                        f = ax + (float) ((j + 1) % 2) * (aw + sw);
                    }
                    f1 = (float) ((double) ay + Math.floor((j + 1) / 2) * (double) (ah + sh));
                } else {
                    if (i % 2 == 1 && j == i - 1) {
                        f = ac;
                    } else {
                        f = ax + (float) (j % 2) * (aw + sw);
                    }
                    f1 = (float) ((double) ay + Math.floor(j / 2) * (double) (ah + sh));
                }
            }
            dst = new Rect(Math.round(f), Math.round(f1), Math.round(f + aw), Math.round(f1 + ah));
            canvas.drawBitmap(BitmapManager.menuItems[ai[j]], null, dst, paint);
            accompCoord[j][2] = Math.round(aw);
            accompCoord[j][3] = Math.round(aw);
            accompCoord[j][0] = Math.round(f + (float)x);
            ai1 = accompCoord[j];
            f2 = f1 + (float)y;
            f3 = ah - aw;
            if(ai[j] == 13)
            {
                k = 1;
            } else
            {
                k = 2;
            }
            ai1[1] = Math.round(f2 + f3 / (float)k);
            MenuInfo.aCoche[j][0] = accompCoord[j][0] + Math.round(aw / 2.0F);
            MenuInfo.aCoche[j][1] = accompCoord[j][1] + Math.round(aw / 2.0F);
            MenuInfo.aScaleMin = aw / (float)BitmapManager.menuCoche.getWidth();
            MenuInfo.aScaleMax = 2.0F * MenuInfo.aScaleMin;
            j++;
        }
    }

    private void drawBurger(int ai[])
    {
        float f = by + bh;
        if(GameManager.menuHelp)
        {
            Arrays.fill(burgerCoord, 0);
        }
        int i = 0;
        do
        {
            if(i >= ai.length)
            {
                return;
            }
            Bitmap bitmap = BitmapManager.menuItems[ai[i]];
            float f1 = (float)bitmap.getHeight() * burgerScale;
            float f2 = f - f1;
            if(burgerScale == 1.0F)
            {
                canvas.drawBitmap(bitmap, bx, f2, null);
            } else
            {
                dst = new Rect(Math.round(bx), Math.round(f2), Math.round(bx + bw), Math.round(f2 + f1));
                canvas.drawBitmap(bitmap, null, dst, paint);
            }
            f -= f1 + sp;
            burgerCoord[i] = Math.round(f2 - (f1 - (float)burgerAW) / 2.0F);
            i++;
        } while(true);
    }

    private void drawCoche()
    {
        int i = (width - BitmapManager.menuCoche.getWidth()) / 2;
        int j = (height - BitmapManager.menuCoche.getHeight()) / 2;
        canvas.drawBitmap(BitmapManager.menuCoche, i, j, null);
    }

    public void setMenu(int ai[], int ai1[])
    {
        validated = false;
        canvas.drawBitmap(BitmapManager.menu, 0.0F, 0.0F, null);
        int i;
        int j;
        int k;
        if(ai1 == null)
        {
            calculateBurger(ai, 0);
            bx = (float)menuX + ((float)menuWidth - bw) / 2.0F;
            drawBurger(ai);
        } else
        if(ai == null)
        {
            calculateAccomp(ai1, false);
            drawAccomp(ai1, false);
        } else
        {
            calculateBurger(ai, ai1.length);
            calculateAccomp(ai1, true);
            drawBurger(ai);
            drawAccomp(ai1, true);
        }
        i = ((x + Math.round(bx)) - burgerAW) + Game.scalei(4);
        MenuInfo.baseX = i;
        MenuInfo.x = i;
        j = burgerCoord[0];
        MenuInfo.baseY = j;
        MenuInfo.y = j;
        k = burgerAW;
        MenuInfo.height = k;
        MenuInfo.width = k;
        MenuInfo.bw = (int)bw;
        MenuInfo.bh = (int)bw;
        MenuInfo.bx = (int)((float)x + bx);
        MenuInfo.by = y + Math.round(((float)height - bw) / 2.0F);
        MenuInfo.bcx = MenuInfo.bx + (int)(bw / 2.0F);
        MenuInfo.bcy = MenuInfo.by + (int)(bw / 2.0F);
        MenuInfo.bScaleMin = bw / (float)BitmapManager.menuCoche.getWidth();
        MenuInfo.bScaleMax = 2.0F * MenuInfo.bScaleMin;
        MenuInfo.reinit();
    }

    public void validate()
    {
        validated = true;
        drawCoche();
    }
}

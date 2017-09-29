package com.magmamobile.game.Burger.managers;

import android.graphics.Paint;

import com.magmamobile.game.Burger.R;
import com.magmamobile.game.engine.Game;

public final class UIManager
{

    public static final int DAYS_ITEM[] = {
        2, 7, 21, 45, 66, 90, 120, 151, 181, 212, 
        243, 273, 304, 365
    };
    public static final int DAYS_TOTAL[];
    public static final int FREE_UNLOCK_DAYS = 3;
    public static final int ITEMS_NAME[] = {
        R.string.res_bun, R.string.res_bun, R.string.res_ketchup, R.string.res_mayo, R.string.res_tomato,
		R.string.res_pickle, R.string.res_letuce, R.string.res_onion, R.string.res_beef, R.string.res_bacon, 
        R.string.res_cheese, R.string.res_fish, R.string.res_icecream, R.string.res_muffin, R.string.res_cola, 
		R.string.res_orange, R.string.res_fries, R.string.res_potato
    };
    public static final int MAX_DAYS = 7;
    public static final int MAX_MONTHS = 11;
    public static final int TIME_UNLOCK_DAYS = 3;
    public static final int TOTAL_OPTIONS = 7;
    public static boolean backButtonActive = true;
    public static int barH;
    public static Paint barP;
    public static int barW;
    public static int barY;
    public static int configIsFrom;
    public static boolean fromNewItem = false;
    public static boolean fromPause = false;
    public static int galleryIsFrom;
    public static int quitIsFrom;

    public UIManager()
    {
    }

    public static int[] getDayByLevel(int i)
    {
        int j;
        int k;
        j = 11;
        if(i >= 365)
        {
            i = 364;
        }
        k = j;
        while(k >= 0) {
            if(i >= DAYS_TOTAL[k])
            {
                j = k;
                break;
            }
            k--;

        }
        int l = i - DAYS_TOTAL[j];
        return (new int[] {
            j, l % 7, l / 7
        });
    }

    public static int getItemsByLevel(int i)
    {
        int j = 0;
        do
        {
            if(j >= DAYS_ITEM.length)
            {
                return -1;
            }
            if(DAYS_ITEM[j] > i)
            {
                return j - 1;
            }
            j++;
        } while(true);
    }

    public static int getLevelByDay(int i, int j, int k)
    {
        return j + (DAYS_TOTAL[i] + k * 7);
    }

    public static int getNewItemByLevel(int i)
    {
        int j = 0;
        while(j < DAYS_ITEM.length) {
            if(DAYS_ITEM[j] == i)
                return j;
            j++;
        }
        j = -1;
        return j;
    }

    public static void init()
    {
        barH = Game.scalei(40);
        barW = Game.mBufferWidth;
        barY = Game.mBufferHeight - barH;
        barP = new Paint();
        configIsFrom = 3;
        quitIsFrom = 12;
        galleryIsFrom = 4;
    }

    public static int nextLevel(int i)
    {
        if(getDayByLevel(i)[1] == 5)
        {
            return i + 2;
        } else
        {
            return i + 1;
        }
    }

    static 
    {
        int ai[] = new int[13];
        ai[1] = 31;
        ai[2] = 59;
        ai[3] = 90;
        ai[4] = 120;
        ai[5] = 151;
        ai[6] = 181;
        ai[7] = 212;
        ai[8] = 243;
        ai[9] = 273;
        ai[10] = 304;
        ai[11] = 334;
        ai[12] = 365;
        DAYS_TOTAL = ai;
    }
}

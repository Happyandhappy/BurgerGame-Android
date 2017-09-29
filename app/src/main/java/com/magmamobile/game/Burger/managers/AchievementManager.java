package com.magmamobile.game.Burger.managers;

import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import java.lang.reflect.Array;

public final class AchievementManager
{

    public static final int A000 = 0;
    public static final int A001 = 1;
    public static final int A002 = 2;
    public static final int A003 = 3;
    public static final int A010 = 4;
    public static final int A011 = 5;
    public static final int A012 = 6;
    public static final int A013 = 7;
    public static final int A020 = 8;
    public static final int A021 = 9;
    public static final int A022 = 10;
    public static final int A023 = 11;
    public static final int A030 = 12;
    public static final int A031 = 13;
    public static final int A032 = 14;
    public static final int A033 = 15;
    public static final int A040 = 16;
    public static final int A041 = 17;
    public static final int A042 = 18;
    public static final int A043 = 19;
    public static final int A050 = 20;
    public static final int A051 = 21;
    public static final int A052 = 22;
    public static final int A053 = 23;
    public static final int A060 = 24;
    public static final int A061 = 25;
    public static final int A062 = 26;
    public static final int A063 = 27;
    public static final int A070 = 28;
    public static final int A071 = 29;
    public static final int A072 = 30;
    public static final int A073 = 31;
    public static final int A080 = 32;
    public static final int A090 = 33;
    public static final int A091 = 34;
    public static final int A092 = 35;
    public static final int A093 = 36;
    public static final int A100 = 37;
    public static final int A101 = 38;
    public static final int A102 = 39;
    public static final int A103 = 40;
    public static final int A200 = 41;
    public static final int ACH_LOCKED = 0;
    public static final int ACH_PROGRESS = 2;
    public static final int ACH_UNLOCKED = 1;
    public static final int LABEL[] = {
        R.string.res_labelorder, R.string.res_labelorder, R.string.res_labelorder, R.string.res_labelorder, R.string.res_labelburger, 
		R.string.res_labelburger, R.string.res_labelburger, R.string.res_labelburger, R.string.res_labeldish, R.string.res_labeldish, 
        R.string.res_labeldish, R.string.res_labeldish, R.string.res_labeldrink, R.string.res_labeldrink, R.string.res_labeldrink, 
		R.string.res_labeldrink, R.string.res_labeldessert, R.string.res_labeldessert, R.string.res_labeldessert, R.string.res_labeldessert, 
        R.string.res_labeltip, R.string.res_labeltip, R.string.res_labeltip, R.string.res_labeltip, R.string.res_labelincome, 
		R.string.res_labelincome, R.string.res_labelincome, R.string.res_labelincome, R.string.res_labelcareer1, R.string.res_labelcareer2, 
        R.string.res_labelcareer3, R.string.res_labelcareer4, R.string.res_labelunlock, R.string.res_labelattack, R.string.res_labelattack, 
		R.string.res_labelattack, R.string.res_labelattack, R.string.res_labelcompl1, R.string.res_labelcompl2, R.string.res_labelcompl3, 
        R.string.res_labelcompl4, R.string.res_labeltotal
    };
    public static final int TITLE[] = {
        R.string.res_titleorder1, R.string.res_titleorder2, R.string.res_titleorder3, R.string.res_titleorder4, R.string.res_titleburger1,
		R.string.res_titleburger2, R.string.res_titleburger3, R.string.res_titleburger4, R.string.res_titledish1, R.string.res_titledish2, 
        R.string.res_titledish3, R.string.res_titledish4, R.string.res_titledrink1, R.string.res_titledrink2, R.string.res_titledrink3, 
		R.string.res_titledrink4, R.string.res_titledessert1, R.string.res_titledessert2, R.string.res_titledessert3, R.string.res_titledessert4, 
        R.string.res_titletip1, R.string.res_titletip2, R.string.res_titletip3, R.string.res_titletip4, R.string.res_titleincome1, 
		R.string.res_titleincome2, R.string.res_titleincome3, R.string.res_titleincome4, R.string.res_titlecareer1, R.string.res_titlecareer2, 
        R.string.res_titlecareer3, R.string.res_titlecareer4, R.string.res_titleunlock, R.string.res_titleattack1, R.string.res_titleattack2, 
		R.string.res_titleattack3, R.string.res_titleattack4, R.string.res_titlecompl1, R.string.res_titlecompl2, R.string.res_titlecompl3, 
        R.string.res_titlecompl4, R.string.res_titletotal
    };
    public static final int TOTAL_ACHIEVEMENT = 42;
    private static int begin;
    private static final int collection[];
    public static int count[][];
    private static int data;
    private static boolean savedState[];
    public static final int scoreValues[][] = {
        {
            500, 1000, 5000, 10000
        }, {
            500, 1000, 5000, 10000
        }, {
            1000, 2500, 10000, 25000
        }, {
            1000, 2500, 10000, 25000
        }, {
            1000, 2500, 10000, 25000
        }, {
            10000, 25000, 50000, 0x186a0
        }, {
            50000, 0x124f8, 0x186a0, 0x30d40
        }, {
            5000, 10000, 25000, 50000
        }
    };
    public static boolean state[];
    private static final boolean stateTab[][];
    private static int values[];

    public AchievementManager()
    {
    }

    public static void compareDay()
    {
        computeDays();
        computeUnlockMode();
        compareItems(28, 5);
        compareTrophy();
    }

    private static void compareItem(int i)
    {
        if(state[i] && !savedState[i])
        {
            savedState[i] = true;
            Common.analytics((new StringBuilder("success/unlock/")).append(i).toString());
            App.trophy.show(i);
        }
    }

    private static void compareItems(int i, int j)
    {
        int k = 0;
        do
        {
            if(k >= j)
            {
                return;
            }
            compareItem(i + k);
            k++;
        } while(true);
    }

    public static void compareMoney()
    {
        computeTip();
        computeIncome();
        compareItems(20, 8);
        computeAttack();
        compareItems(33, 4);
        compareTrophy();
    }

    public static void compareTray()
    {
        computeOrder();
        computeBurger();
        computeDish();
        computeDrink();
        computeDessert();
        compareItems(0, 20);
        compareTrophy();
    }

    private static void compareTrophy()
    {
        computeTrophy();
        computeFinal();
        compareItems(37, 5);
    }

    public static void compute()
    {
        state = new boolean[42];
        int ai[] = {
            42, 2
        };
        count = (int[][])Array.newInstance(Integer.TYPE, ai);
        computeOrder();
        computeBurger();
        computeDish();
        computeDrink();
        computeDessert();
        computeTip();
        computeIncome();
        computeDays();
        computeUnlockMode();
        computeAttack();
        computeTrophy();
        computeFinal();
    }

    public static void computeAttack()
    {
        computeItem(PrefManager.totalAttack, 33, scoreValues[7]);
    }

    public static void computeBurger()
    {
        computeItem(PrefManager.totalBurger, 4, scoreValues[1]);
    }

    private static boolean computeCollection(int i, int j)
    {
        int k = 0;
        int l = collection.length;
        int i1 = 0;
        do
        {
            if(i1 >= l)
            {
                if(i == 37)
                {
                    l++;
                    if(state[32])
                    {
                        k++;
                    }
                }
                count[i][0] = k;
                count[i][1] = l;
                return k == l;
            }
            if(state[j + collection[i1]])
            {
                k++;
            }
            i1++;
        } while(true);
    }

    private static void computeDay(int i, int j)
    {
        if(PrefManager.higherLevel >= j)
        {
            state[i] = true;
        }
        count[i][0] = PrefManager.higherLevel;
        count[i][1] = j;
    }

    public static void computeDays()
    {
        computeDay(28, 1);
        computeDay(29, 7);
        computeDay(30, UIManager.DAYS_TOTAL[1]);
        computeDay(31, 365);
    }

    public static void computeDessert()
    {
        computeItem(PrefManager.totalDessert, 16, scoreValues[4]);
    }

    public static void computeDish()
    {
        computeItem(PrefManager.totalDish, 8, scoreValues[2]);
    }

    public static void computeDrink()
    {
        computeItem(PrefManager.totalDrink, 12, scoreValues[3]);
    }

    public static void computeFinal()
    {
        state[41] = computeTotal(41);
    }

    public static void computeIncome()
    {
        computeItem(PrefManager.totalIncome, 24, scoreValues[6]);
    }

    private static void computeItem(int i, int j, int ai[])
    {
        data = i;
        begin = j;
        values = ai;
        if(data >= values[3])
        {
            setValues(stateTab[4]);
            return;
        }
        if(data >= values[2])
        {
            setValues(stateTab[3]);
            return;
        }
        if(data >= values[1])
        {
            setValues(stateTab[2]);
            return;
        }
        if(data >= values[0])
        {
            setValues(stateTab[1]);
            return;
        } else
        {
            setValues(stateTab[0]);
            return;
        }
    }

    public static void computeOrder()
    {
        computeItem(PrefManager.totalMenu, 0, scoreValues[0]);
    }

    public static void computeTip()
    {
        computeItem(PrefManager.totalTips, 20, scoreValues[5]);
    }

    private static boolean computeTotal(int i)
    {
        int j = 0;
        int k = 0;
        do
        {
            if(k >= 41)
            {
                count[i][0] = j;
                count[i][1] = 42;
                if(j != 41)
                {
                    return false;
                } else
                {
                    int ai[] = count[i];
                    ai[0] = 1 + ai[0];
                    return true;
                }
            }
            if(state[k])
            {
                j++;
            }
            k++;
        } while(true);
    }

    public static void computeTrophy()
    {
        int i = 0;
        do
        {
            if(i >= 4)
            {
                return;
            }
            state[i + 37] = computeCollection(i + 37, i);
            i++;
        } while(true);
    }

    public static void computeUnlockMode()
    {
        if(PrefManager.higherLevel >= 3)
        {
            state[32] = true;
        }
    }

    public static void init()
    {
        compute();
        savedState = (boolean[])state.clone();
    }

    private static void setValues(boolean aflag[])
    {
        int i = 0;
        do
        {
            if(i >= aflag.length)
            {
                return;
            }
            state[i + begin] = aflag[i];
            count[i + begin][0] = data;
            count[i + begin][1] = values[i];
            i++;
        } while(true);
    }

    static 
    {
        boolean aflag[][] = new boolean[5][];
        aflag[0] = new boolean[4];
        boolean aflag1[] = new boolean[4];
        aflag1[0] = true;
        aflag[1] = aflag1;
        boolean aflag2[] = new boolean[4];
        aflag2[0] = true;
        aflag2[1] = true;
        aflag[2] = aflag2;
        boolean aflag3[] = new boolean[4];
        aflag3[0] = true;
        aflag3[1] = true;
        aflag3[2] = true;
        aflag[3] = aflag3;
        aflag[4] = (new boolean[] {
            true, true, true, true
        });
        stateTab = aflag;
        int ai[] = new int[9];
        ai[1] = 4;
        ai[2] = 8;
        ai[3] = 12;
        ai[4] = 16;
        ai[5] = 20;
        ai[6] = 24;
        ai[7] = 28;
        ai[8] = 33;
        collection = ai;
    }
}

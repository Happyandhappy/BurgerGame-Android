package com.magmamobile.game.Burger.game;

import com.magmamobile.game.Burger.managers.GameManager;
import java.util.Arrays;
import java.util.Random;

public class MenuGenerator
{

    private static Random Random = new Random();
    private static int accomp[];
    private static int burger[];
    private static int i;
    private static int max;
    private static int min;
    private static int oldAccomp[];
    private static int oldBurger[];
    private static int rnd;
    private static int size;
    private static int struct[];
    private static int tab[];
    private static int tmp;
    private static boolean valid;

    public MenuGenerator()
    {
    }

    private static int[] add2Niv(int j)
    {
        int k;
        int l;
        int ai[];
        if(j <= 10)
        {
            k = 2;
        } else
        {
            k = j - 8;
        }
        min = k;
        if(j <= 10)
        {
            l = j - 5;
        } else
        {
            l = 5;
        }
        max = l;
        rnd = random(min, max);
        ai = new int[2];
        ai[0] = rnd;
        ai[1] = j - (3 + rnd);
        return ai;
    }

    private static int[] add3Niv(int j)
    {
        min = 2;
        int k;
        int ai[];
        if(j <= 13)
        {
            k = j - 8;
        } else
        {
            k = 5;
        }
        max = k;
        tmp = random(min, max);
        tab = add2Niv(j - (1 + tmp));
        ai = new int[3];
        ai[0] = tmp;
        ai[1] = tab[0];
        ai[2] = tab[1];
        return ai;
    }

    private static void cloneMenu()
    {
        int ai[];
        int ai1[];
        if(burger != null)
        {
            ai = (int[])burger.clone();
        } else
        {
            ai = null;
        }
        oldBurger = ai;
        if(accomp != null)
        {
            ai1 = (int[])accomp.clone();
        } else
        {
            ai1 = null;
        }
        oldAccomp = ai1;
    }

    private static int[] getAccomp(int j)
    {
        int k = j + 1;
        min = k / 4;
        max = k / 2;
        rnd = limit(random(min, max), 1, 6);
        tab = new int[rnd];
        i = 0;
        do
        {
            if(i >= tab.length)
            {
                Arrays.sort(tab);
                return tab;
            }
            do
            {
                rnd = 12 + Random.nextInt(6);
            } while(!GameManager.isActive(rnd));
            tab[i] = rnd;
            i = 1 + i;
        } while(true);
    }

    private static int[] getBurger(int j)
    {
        size = getSize(j);
        struct = getStruct(size);
        return getCompo(size, struct);
    }

    private static int[] getCompo(int j, int ai[])
    {
        int k;
        int ai1[];
        tmp = 0;
        ai1 = new int[j];
        i = 0;
        while(i < ai.length)
        {
            tmp = 1 + tmp;
            k = ai[i];
            max = 2;
            min = 1;
            while(k > 0)
            {
                valid = true;
                rnd = random(2, 11);
                if(!(!GameManager.isActive(rnd) || rnd == ai1[-1 + tmp])) {
                    switch (rnd) {
                        default:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 10:
                            break;
                        case 2:
                        case 3:
                            if (min > 0 && (ai1[-1 + tmp] < 4 || ai1[-1 + tmp] > 7 || ai1.length <= 5)) {
                                min = -1 + min;
                            } else {
                                valid = false;
                            }
                            break;
                        case 8:
                        case 9:
                        case 11:
                            if (max > 0 && (ai1[-1 + tmp] != 0 || ai1.length <= 5)) {
                                max = -1 + max;
                            } else {
                                valid = false;
                            }
                            break;
                    }
                    if (valid) {
                        ai1[tmp] = rnd;
                        tmp = 1 + tmp;
                        k--;
                    }
                }
            }
            i = 1 + i;
        }
        ai1[tmp] = 1;
        return ai1;
    }

    public static int[][] getMenu(int j, int k, int l)
    {
        burger = null;
        accomp = null;
        int ai[][];
        while(true) {
            rnd = Random.nextInt(101);
            if (GameManager.hasAccomp) {
                if (rnd < k) {
                    accomp = getAccomp(j);
                } else if (rnd >= l) {
                    burger = getBurger(j);
                } else {
                    burger = getBurger(j);
                    accomp = getAccomp(j);
                }
            } else {
                burger = getBurger(j);
            }
            if (oldBurger == null && oldAccomp == null) {
                cloneMenu();
                break;
            }
            if (!isMenuEqual()) {
                cloneMenu();
                break;
            }
        }
        ai = new int[2][];
        ai[0] = burger;
        ai[1] = accomp;
        return ai;
    }

    private static int getSize(int j)
    {
        tmp = j / 3;
        int k = 3 + tmp;
        int l;
        if(j >= 9)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        min = l + k;
        max = 5 + 2 * tmp + 2 * (j % 3);
        return random(min, max);
    }

    private static int[] getStruct(int j)
    {
        if(j <= 6)
        {
            int ai[] = new int[1];
            ai[0] = j - 2;
            return ai;
        }
        if(j == 7)
        {
            if(Random.nextBoolean())
            {
                return (new int[] {
                    5
                });
            } else
            {
                return add2Niv(j);
            }
        }
        if(j <= 9)
        {
            return add2Niv(j);
        }
        if(j <= 13)
        {
            if(Random.nextBoolean())
            {
                return add2Niv(j);
            } else
            {
                return add3Niv(j);
            }
        } else
        {
            return add3Niv(j);
        }
    }

    private static boolean isMenuEqual()
    {
        while(!Arrays.equals(burger, oldBurger) || !Arrays.equals(accomp, oldAccomp)) 
        {
            return false;
        }
        return true;
    }

    private static int limit(int j, int k, int l)
    {
        if(j < k)
        {
            return k;
        }
        if(j > l)
        {
            return l;
        } else
        {
            return j;
        }
    }

    private static int random(int j, int k)
    {
        return j + Random.nextInt(1 + (k - j));
    }

    public static void reinit()
    {
        oldBurger = null;
        oldAccomp = null;
    }

}

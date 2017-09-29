package com.magmamobile.game.Burger.managers;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public final class GameManager
{

    public static final int ACCOMP_COLA = 14;
    public static final int ACCOMP_FRITES = 16;
    public static final int ACCOMP_GLACE = 12;
    public static final int ACCOMP_MUFFIN = 13;
    public static final int ACCOMP_ORANGE = 15;
    public static final int ACCOMP_POTATO = 17;
    public static final int ANIM_BOING = 3;
    public static final int ANIM_COUNT = 6;
    public static final int ANIM_FINISH = 5;
    public static final int ANIM_IN = 1;
    public static final int ANIM_NONE = 0;
    public static final int ANIM_OUT = 2;
    public static final int ANIM_WAIT = 4;
    public static final int ATTACK_TIME = 45;
    public static final int CALL_TOAST = 0;
    public static final int CAREER_TIME = 60;
    public static final int MAGMA_DISH = 2;
    public static final int MAGMA_ORDER = 3;
    public static final int MAGMA_PART = 1;
    public static final int MAX_ACCOMP = 6;
    public static final int MAX_PARTS = 15;
    public static final int MODE_CAREER = 0;
    public static final int MODE_FREEPLAY = 2;
    public static final int MODE_TIMEATTACK = 1;
    public static final int MONTH_ITEM[] = {
        16, 14, 10, 13, 4, 3, 17, 5, 12, 9, 
        7, 15, 11
    };
    public static final int PART_BACON = 9;
    public static final int PART_CORNICHON = 5;
    public static final int PART_FROMAGE = 10;
    public static final int PART_KETCHUP = 2;
    public static final int PART_MAYONNAISE = 3;
    public static final int PART_OIGNON = 7;
    public static final int PART_PAIN1 = 0;
    public static final int PART_PAIN2 = 1;
    public static final int PART_POISSON = 11;
    public static final int PART_SALADE = 6;
    public static final int PART_STEAK = 8;
    public static final int PART_TOMATE = 4;
    public static final int TIME_OUT = 0;
    public static final int TIME_OVER = 2;
    public static final int TIME_RUN = 1;
    public static final int TIP_PER_MAGMA = 2;
    public static final int TOTAL_ACCOMP = 6;
    public static final int TOTAL_ITEMS = 18;
    public static final int TOTAL_MENUS = 4;
    public static final int TOTAL_PARTS = 12;
    public static final int TOTAL_PHOTOS = 3;
    public static int burgerSend;
    public static int currentLevel;
    public static int dayGoal;
    public static int dayIncome;
    public static int dayTips;
    public static int dayTotal;
    public static int dessertSend;
    public static int dishSend;
    public static int drinkSend;
    private static NumberFormat formater = new DecimalFormat("0000");
    public static int gameMode;
    public static int gameTime;
    public static boolean gameTimed = true;
    public static int hardness;
    public static boolean hasAccomp;
    public static int itemTime;
    public static boolean items[];
    public static boolean menuHelp = true;
    public static boolean menuTimed = true;
    public static boolean monoMenu = true;
    public static int orderSend;
    public static int orderTime;
    public static int pMenu1;
    public static int pMenu2;
    public static boolean trainingMode = false;

    public GameManager()
    {
    }

    private static boolean accompPresent()
    {
        int i = 12;
        do
        {
            if(i >= 18)
            {
                return false;
            }
            if(items[i])
            {
                return true;
            }
            i++;
        } while(true);
    }

    public static void activate(int i)
    {
        items[i] = true;
    }

    private static float easeInOutCubic(float f, float f1, float f2)
    {
        float f3 = f2 * 2.0F;
        if(f3 < 1.0F)
        {
            return f + ((f1 - f) / 2.0F) * (float)Math.pow(f3, 3D);
        } else
        {
            return f + ((f1 - f) / 2.0F) * (float)(2D + Math.pow(f3 - 2.0F, 3D));
        }
    }

    public static String getFormatedCurrentStage()
    {
        return formater.format(currentLevel);
    }

    private static void getHardnessByDay(int ai[])
    {
        float f = 0.1F * (float)ai[1];
        float f1;
        if(ai[2] < 3)
        {
            hardness = ai[0];
            f1 = f + 0.25F * (float)ai[2];
        } else
        {
            int i = ai[0];
            int j = ai[0];
            int k = 0;
            if(j < 11)
            {
                k = 1;
            }
            hardness = k + i;
            f1 = f + 0.25F * (float)(-2 + ai[2]);
        }
        if(f1 < 0.5F)
        {
            float f3 = f1 * 2.0F;
            pMenu1 = 25 + (int)(8.333333F * f3);
            pMenu2 = 25 + (int)(41.66666F * f3);
        } else
        {
            float f2 = 2.0F * (f1 - 0.5F);
            pMenu1 = (int)(33.33333F * (1.0F - f2));
            pMenu2 = (int)(66.66666F + 33.33333F * f2);
        }
        setGoalByDay(ai);
        setMenuTime(ai);
    }

    public static int getTimeToAdd(int i)
    {
        float f = (float)hardness / 11F;
        return (1500 - (int)(700F * f)) + i * (400 + (int)easeInOutCubic(400F, 0.0F, f));
    }

    public static void init()
    {
        currentLevel = -1;
        initItems();
    }

    public static void initDayStat()
    {
        orderSend = 0;
        burgerSend = 0;
        dessertSend = 0;
        drinkSend = 0;
        dishSend = 0;
        dayTips = 0;
        dayIncome = 0;
        dayTotal = 0;
        if(gameMode == 1)
        {
            dayGoal = PrefManager.scoreAttack;
        }
    }

    private static void initItems()
    {
        if(gameMode == 0)
        {
            boolean aflag[] = new boolean[18];
            aflag[0] = true;
            aflag[1] = true;
            aflag[2] = true;
            aflag[6] = true;
            aflag[8] = true;
            items = aflag;
            return;
        } else
        {
            items = (new boolean[] {
                true, true, true, true, true, true, true, true, true, true,
                    true, true, true, true, true, true, true, true
            });
            return;
        }
    }

    public static boolean isActive(int i)
    {
        return items[i];
    }

    public static boolean isFreeplay()
    {
        return gameMode == 2;
    }

    public static void nextLevel()
    {
        currentLevel = UIManager.nextLevel(currentLevel);
    }

    private static void setGameTime()
    {
        if(hardness < 8)
        {
            gameTime = 60 + 10 * hardness;
            return;
        } else
        {
            gameTime = 140 + 10 * ((-8 + hardness) / 2);
            return;
        }
    }

    public static void setGoalByDay(int ai[])
    {
        dayGoal = 100 + 10 * (ai[0] / 2) + 10 * ai[2];
        if(ai[0] == 11)
        {
            dayGoal = 10 + dayGoal;
        } else
        if(ai[0] == 0 && ai[1] == 0 && ai[2] == 0)
        {
            dayGoal = 70;
            return;
        }
    }

    public static void setHarnessByLevel(int i)
    {
        initItems();
        switch (gameMode) {
            default:
                break;
            case 0:
                if(i == 0)
                {
                    trainingMode = true;
                    menuTimed = false;
                    dayGoal = 70;
                } else
                {
                    int ai[] = UIManager.getDayByLevel(i);
                    trainingMode = false;
                    menuTimed = true;
                    getHardnessByDay(ai);
                    setGameTime();
                    int j = UIManager.getItemsByLevel(currentLevel);
                    int k = 0;
                    while(k <= j)
                    {
                        items[MONTH_ITEM[k]] = true;
                        k++;
                    }
                }
                break;
            case 1:
                hardness = 0;
                pMenu1 = 33;
                pMenu2 = 66;
                break;
        }
        hasAccomp = accompPresent();
    }

    public static void setHarnessByOrders()
    {
        int i = 11;
        hardness = orderSend / 6;
        if(hardness <= i)
        {
            i = hardness;
        }
        hardness = i;
        pMenu1 = 33;
        pMenu2 = 66;
    }

    public static void setMenuTime(int ai[])
    {
        orderTime = (int)(5000F - 3000F * (0.1818182F * (float)ai[0]));
        itemTime = 3000 - 181 * ai[0];
    }

    public static void setMode(int i)
    {
        gameMode = i;
        initItems();
        switch(gameMode)
        {
        default:
            return;

        case 0: // '\0'
            gameTimed = true;
            menuTimed = true;
            menuHelp = true;
            trainingMode = false;
            return;

        case 1: // '\001'
            gameTimed = true;
            menuTimed = false;
            menuHelp = false;
            trainingMode = false;
            return;

        case 2: // '\002'
            gameTimed = false;
            break;
        }
        menuTimed = false;
        menuHelp = false;
        trainingMode = false;
    }

}

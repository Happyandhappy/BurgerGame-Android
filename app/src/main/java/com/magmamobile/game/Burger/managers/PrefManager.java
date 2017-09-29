package com.magmamobile.game.Burger.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.magmamobile.game.engine.Game;

public final class PrefManager
{

    public static final int CONF_ANIMATE = 3;
    public static final int CONF_MUSIC = 1;
    public static final int CONF_SOUND = 0;
    public static final int CONF_VIBRATE = 2;
    public static final int TOTAL_CONFIG = 4;
    public static boolean configs[];
    public static int higherLevel;
    public static int lastAsk4Rate;
    public static boolean noAsk4Rate;
    public static int playCount;
    public static String prefLastVersion = "";
    public static int scoreAttack;
    public static int totalAttack;
    public static int totalBurger;
    public static int totalDessert;
    public static int totalDish;
    public static int totalDrink;
    public static int totalIncome;
    public static int totalMenu;
    public static int totalMoney;
    public static int totalTips;

    public PrefManager()
    {
    }

    public static void LoadPreferences(Context context)
    {
        prefLastVersion = PreferenceManager.getDefaultSharedPreferences(context).getString("LastVersion", "");
        playCount = Game.getPrefInt("playCount", 0);
        lastAsk4Rate = Game.getPrefInt("lastAsk4Rate", 0);
        noAsk4Rate = Game.getPrefBoolean("noAsk4Rate", false);
    }

    public static void addPlayCount()
    {
        int i = 1 + playCount;
        playCount = i;
        Game.setPrefInt("playCount", i);
    }

    public static void disableAsk4Rate()
    {
        Game.setPrefBoolean("noAsk4Rate", true);
    }

    public static void init()
    {
        configs = new boolean[4];
        updateConfig();
        updateGameData();
    }

    public static void saveGameData()
    {
        saveTrayData();
        saveMoneyData();
    }

    public static void saveHigherLevel()
    {
        Game.setPrefInt("higherLevel", higherLevel);
    }

    public static void saveMoneyData()
    {
        Game.setPrefInt("totalTips", totalTips);
        Game.setPrefInt("totalIncome", totalIncome);
    }

    public static void saveTimeAttack()
    {
        Game.setPrefInt("attackIncome", totalAttack);
        Game.setPrefInt("attackScore", scoreAttack);
    }

    public static void saveTrayData()
    {
        Game.setPrefInt("totalMenu", totalMenu);
        Game.setPrefInt("totalBurger", totalBurger);
        Game.setPrefInt("totalDish", totalDish);
        Game.setPrefInt("totalDrink", totalDrink);
        Game.setPrefInt("totalDessert", totalDessert);
    }

    public static void updateConfig()
    {
        configs[0] = Game.getSoundEnable();
        configs[1] = Game.getMusicEnable();
        configs[2] = Game.getVibrateEnable();
        configs[3] = Game.getPrefBoolean("prefAnimate", true);
    }

    public static void updateGameData()
    {
        higherLevel = Game.getPrefInt("higherLevel", 0);
        totalMenu = Game.getPrefInt("totalMenu", 0);
        totalBurger = Game.getPrefInt("totalBurger", 0);
        totalDish = Game.getPrefInt("totalDish", 0);
        totalDrink = Game.getPrefInt("totalDrink", 0);
        totalDessert = Game.getPrefInt("totalDessert", 0);
        totalTips = Game.getPrefInt("totalTips", 0);
        totalIncome = Game.getPrefInt("totalIncome", 0);
        totalMoney = totalTips + totalIncome;
        totalAttack = Game.getPrefInt("attackIncome", 0);
        scoreAttack = Game.getPrefInt("attackScore", 0);
    }

    public static void updatelastAsk4Rate(int i)
    {
        lastAsk4Rate = i;
        Game.setPrefInt("lastAsk4Rate", lastAsk4Rate);
    }

}

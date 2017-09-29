package com.magmamobile.game.Burger.managers;

import com.magmamobile.game.engine.*;
import java.io.*;

public final class DataManager
{

    public static int dayBurgers;
    public static int dayDesserts;
    public static int dayDishes;
    public static int dayDrinks;
    public static int dayId = -1;
    public static int dayIncomes;
    public static int dayOrders;
    public static int dayTips;
    public static int dayTotal;
    private static File file;
    private static InputStreamEx streamExIn;
    private static OutputStreamEx streamExOut;
    private static FileInputStream streamIn;
    private static OutputStream streamOut;

    public DataManager()
    {
    }

    public static int getDay()
    {
        return dayId;
    }

    public static boolean loadDayData()
    {
        return loadDayData(dayId);
    }

    public static boolean loadDayData(int i)
    {
        try {
            file = Game.getFile((new StringBuilder("day")).append(i).toString());
            if (!file.exists()) {
                reinit();
                return false;
            }
            streamIn = new FileInputStream(file);
            streamExIn = new InputStreamEx(streamIn);
            dayId = streamExIn.readInt();
            dayOrders = streamExIn.readInt();
            dayBurgers = streamExIn.readInt();
            dayDishes = streamExIn.readInt();
            dayDrinks = streamExIn.readInt();
            dayDesserts = streamExIn.readInt();
            dayTips = streamExIn.readInt();
            dayIncomes = streamExIn.readInt();
            dayTotal = streamExIn.readInt();
            streamExIn.close();
            streamIn.close();
            dayId = i;
            return true;
        }catch(Exception e) {
            reinit();
            return false;
        }
    }

    public static void reinit()
    {
        dayId = -1;
        dayOrders = 0;
        dayBurgers = 0;
        dayDishes = 0;
        dayDrinks = 0;
        dayDesserts = 0;
        dayTips = 0;
        dayIncomes = 0;
        dayTotal = 0;
    }

    public static boolean saveDayData()
    {
        return saveDayData(dayId);
    }

    public static boolean saveDayData(int i)
    {
        try
        {
            file = Game.getFile((new StringBuilder("day")).append(i).toString());
            streamOut = new FileOutputStream(file);
            streamExOut = new OutputStreamEx(streamOut);
            dayId = i;
            streamExOut.writeInt(dayId);
            streamExOut.writeInt(dayOrders);
            streamExOut.writeInt(dayBurgers);
            streamExOut.writeInt(dayDishes);
            streamExOut.writeInt(dayDrinks);
            streamExOut.writeInt(dayDesserts);
            streamExOut.writeInt(dayTips);
            streamExOut.writeInt(dayIncomes);
            streamExOut.writeInt(dayTotal);
            streamExOut.close();
            streamOut.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public static void setDay(int i)
    {
        dayId = i;
    }

    public static void updateDayData()
    {
        if(dayId != GameManager.currentLevel)
        {
            dayOrders = GameManager.orderSend;
            dayBurgers = GameManager.burgerSend;
            dayDishes = GameManager.dishSend;
            dayDrinks = GameManager.drinkSend;
            dayDesserts = GameManager.dessertSend;
            dayTips = GameManager.dayTips;
            dayIncomes = GameManager.dayIncome;
            dayTotal = GameManager.dayTotal;
        }
    }

}

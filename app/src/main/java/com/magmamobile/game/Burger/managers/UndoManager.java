package com.magmamobile.game.Burger.managers;

import java.util.Arrays;

public class UndoManager
{

    private static int _menu[][];
    private static int _queue[];
    public static int length;
    public static boolean restore;

    public UndoManager()
    {
    }

    public static void addItem(int i)
    {
        if(GameManager.isFreeplay())
        {
            if(restore)
            {
                reset();
                restore = false;
            }
            if(length >= 0 && length < _queue.length)
            {
                _queue[length] = i;
                length = 1 + length;
            }
        }
    }

    public static int[] getAccomp()
    {
        return _menu[1];
    }

    public static int[] getBurger()
    {
        return _menu[0];
    }

    public static int getLastItem()
    {
        return _queue[-1 + length];
    }

    public static int[] getPlaces()
    {
        return _menu[2];
    }

    public static void init()
    {
        _queue = new int[21];
        _menu = new int[3][];
        reset();
    }

    public static void reset()
    {
        Arrays.fill(_queue, -1);
        length = 0;
        restore = false;
    }

    public static void saveMenu(int ai[], int ai1[], int ai2[])
    {
        _menu[0] = (int[])ai.clone();
        _menu[1] = (int[])ai1.clone();
        _menu[2] = (int[])ai2.clone();
        restore = true;
    }

    public static void undo()
    {
        _queue[-1 + length] = -1;
        length = -1 + length;
    }
}

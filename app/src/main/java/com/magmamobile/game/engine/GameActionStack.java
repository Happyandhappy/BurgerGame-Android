package com.magmamobile.game.engine;


public final class GameActionStack
{

    private static final int MAX_ACTION = 16;
    private static int pointer = 0;
    private static int stack[] = new int[16];

    public GameActionStack()
    {
    }

    public static boolean hasData()
    {
        return pointer > 0;
    }

    public static int pop()
    {
        if(pointer <= 0)
        {
            return 0;
        } else
        {
            pointer = -1 + pointer;
            return stack[pointer];
        }
    }

    public static void push(int i)
    {
        if(pointer >= 16)
        {
            return;
        } else
        {
            stack[pointer] = i;
            pointer = 1 + pointer;
            return;
        }
    }

}

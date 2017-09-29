package com.magmamobile.game.engine;

import java.util.Arrays;

public final class Keyboard
{

    public static final byte KEY_BACK = 0;
    public static final byte KEY_DOWN = -1;
    public static final byte KEY_NONE = -1;
    public static final byte KEY_UP = 0;
    public static final byte KEY_VOLUMEDW = 2;
    public static final byte KEY_VOLUMEUP = 1;
    public static final byte STATE_DOWN = -1;
    public static final byte STATE_NONE = 0;
    public static final byte STATE_UP = 1;
    public static byte keys[] = new byte[3];
    public static byte state[] = new byte[3];

    public Keyboard()
    {
    }

    public static void clear()
    {
        Arrays.fill(state, (byte)0);
    }

    public static boolean isDown(int i)
    {
        return state[i] == 1;
    }

    public static boolean isKeyBack()
    {
        byte byte0 = state[0];
        boolean flag = false;
        if(byte0 == -1)
        {
            flag = true;
        }
        return flag;
    }

    public static boolean isUp(int i)
    {
        return state[i] == -1;
    }

    public static void reset()
    {
        Arrays.fill(keys, (byte)0);
        Arrays.fill(state, (byte)0);
    }

    static 
    {
        reset();
    }
}

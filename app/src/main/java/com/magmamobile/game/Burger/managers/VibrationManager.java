package com.magmamobile.game.Burger.managers;

import com.magmamobile.game.engine.Game;

public final class VibrationManager
{

    private static final int items[] = {
        25, 25, 10, 10, 30, 30, 10, 25, 50, 25, 
        10, 75, 50, 25, 50, 50, 25, 25
    };

    public VibrationManager()
    {
    }

    public static final void vibrate()
    {
        Game.vibrate(50L);
    }

    public static final void vibrate(int i)
    {
        Game.vibrate(i);
    }

    public static final void vibrateItem(int i)
    {
        Game.vibrate(items[i]);
    }

}

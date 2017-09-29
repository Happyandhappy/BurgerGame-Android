package com.magmamobile.game.Burger.game;

import android.graphics.Color;
import com.magmamobile.game.Burger.managers.SoundManager;
import com.magmamobile.game.engine.Game;

public class PhotoFlash
{

    private static final float SPEED = 0.075F;
    private static boolean _actif;
    private static float _f;
    private static int color;

    public PhotoFlash()
    {
    }

    public static void animate()
    {
        if(_actif)
        {
            _f -= 0.075F;
            if(_f <= 0.0F)
            {
                _f = 0.0F;
                _actif = false;
            }
            color = Color.argb((int)(255F * _f), 255, 255, 255);
        }
    }

    public static void draw()
    {
        if(_actif)
        {
            Game.drawColor(color);
        }
    }

    public static void flash()
    {
        SoundManager.playSound(37);
        _actif = true;
        _f = 1.0F;
    }

    public static void init()
    {
        _actif = false;
        _f = 0.0F;
        color = -1;
    }
}

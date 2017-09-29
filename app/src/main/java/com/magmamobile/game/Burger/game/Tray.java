package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.PrefManager;

public class Tray
{

    public int cx;
    public int cy;
    public int height;
    public boolean visible;
    public int width;
    public int x;
    public int y;

    public Tray()
    {
        width = BitmapManager.tray.getWidth();
        height = BitmapManager.tray.getHeight();
        visible = true;
    }

    private void center()
    {
        x = cx - width / 2;
        y = cy - height / 2;
    }

    public void setCenter(int i, int j)
    {
        cx = i;
        cy = j;
        center();
    }

    public void setScale(float f)
    {
        visible = true;
        if(PrefManager.configs[3])
        {
            width = (int)(f * (float)BitmapManager.tray.getWidth());
            height = (int)(f * (float)BitmapManager.tray.getHeight());
            center();
        } else
        if(f < 1.0F)
        {
            visible = false;
            return;
        }
    }
}

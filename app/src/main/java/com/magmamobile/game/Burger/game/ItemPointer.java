package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ItemPointer
{

    public boolean added;
    public Bitmap bmp;
    public int count;
    public Matrix mtx;

    public ItemPointer()
    {
        count = -1;
        init();
    }

    public void init()
    {
        count = -1;
        added = false;
    }
}

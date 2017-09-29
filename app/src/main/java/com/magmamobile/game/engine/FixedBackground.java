package com.magmamobile.game.engine;

import android.graphics.Bitmap;

// Referenced classes of package com.magmamobile.game.engine:
//            Game

public final class FixedBackground
{

    private static Bitmap _bm;
    private static int _res = -1;

    public FixedBackground()
    {
    }

    public static void freeBitmap()
    {
        while(_res == -1 || _bm == null) 
        {
            return;
        }
        if(!_bm.isRecycled())
        {
            _bm.recycle();
        }
        _bm = null;
        if(_res >= 0)
        {
            Game.freeBitmap(_res);
        }
        _res = -1;
        System.gc();
    }

    public static Bitmap getBitmap()
    {
        return _bm;
    }

    public static int getRes()
    {
        return _res;
    }

    public static void onAction()
    {
    }

    public static void onInitialize(int i)
    {
        if(i != _res)
        {
            freeBitmap();
            _res = i;
            _bm = Game.getBitmap(_res);
        }
    }

    public static void onRender()
    {
        while(_bm == null || _bm.isRecycled()) 
        {
            return;
        }
        Game.drawBitmap(_bm);
    }

    public static void onTerminate()
    {
        freeBitmap();
    }

    public static void setBitmap(Bitmap bitmap)
    {
        freeBitmap();
        _bm = bitmap;
    }

}

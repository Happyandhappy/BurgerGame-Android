package com.magmamobile.game.engine.tmp;

import android.graphics.Bitmap;
import com.magmamobile.game.engine.Game;

public final class ManagedBitmap
{

    private Bitmap bitmap;
    private int key;

    public ManagedBitmap(int i)
    {
        key = i;
    }

    public void free()
    {
    }

    public Bitmap getBitmap()
    {
        if(bitmap == null || bitmap.isRecycled())
        {
            bitmap = Game.getBitmap(key);
        }
        return bitmap;
    }

    public void release()
    {
        if(bitmap != null)
        {
            Game.freeBitmap(key);
            if(!bitmap.isRecycled())
            {
                bitmap.recycle();
            }
            bitmap = null;
        }
    }
}

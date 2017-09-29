package com.magmamobile.game.engine;


public final class BitmapUtils_API3
{

    public BitmapUtils_API3()
    {
    }

    public static final android.graphics.BitmapFactory.Options createOption()
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
        return options;
    }
}

package com.magmamobile.game.engine;

import android.os.Build;

public final class BitmapUtils_API4
{

    public BitmapUtils_API4()
    {
    }

    public static final android.graphics.BitmapFactory.Options createOption()
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inPurgeable = false;
        options.inDither = false;
        options.inScaled = false;
        options.inInputShareable = true;
        options.inScreenDensity = 0;
        options.inTargetDensity = 0;
        options.inDensity = 0;
        int i = Game.getColorMode();
        if(i == 2)
        {
            options.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;
            return options;
        }
        if(i == 1)
        {
            options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
            return options;
        }
        if("GT-I9000".equals(Build.MODEL) || "SGH-T959".equals(Build.MODEL) || "SCH-I500".equals(Build.MODEL) || "SAMSUNG-SGH-I897".equals(Build.MODEL) || "Droid".equals(Build.MODEL) || "Milestone".equals(Build.MODEL) || "Archos5".equals(Build.MODEL))
        {
            options.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;
            return options;
        } else
        {
            options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
            return options;
        }
    }
}

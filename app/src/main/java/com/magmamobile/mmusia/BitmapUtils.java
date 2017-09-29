package com.magmamobile.mmusia;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.IOException;

public final class BitmapUtils
{

    public BitmapUtils()
    {
    }

    public static final Drawable getAssetDrawableResize(Context context, Drawable drawable, int i, int j)
    {
        if(drawable == null)
        {
            return null;
        }
        BitmapDrawable bitmapdrawable;
        try
        {
            bitmapdrawable = new BitmapDrawable(Bitmap.createScaledBitmap(((BitmapDrawable)drawable).getBitmap(), i, j, false));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return bitmapdrawable;
    }

    public static BitmapDrawable loadDrawable(Activity activity, String s)
        throws IOException
    {
        if(Integer.valueOf(android.os.Build.VERSION.SDK).intValue() <= 3)
        {
            return new BitmapDrawable(activity.getAssets().open(s));
        } else
        {
            return BitmapUtils16.loadDrawable(activity, s);
        }
    }
}

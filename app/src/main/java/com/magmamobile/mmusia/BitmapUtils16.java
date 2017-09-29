package com.magmamobile.mmusia;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.IOException;

public final class BitmapUtils16
{

    public BitmapUtils16()
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
            bitmapdrawable = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(((BitmapDrawable)drawable).getBitmap(), i, j, false));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return bitmapdrawable;
    }

    public static final BitmapDrawable loadDrawable(Activity activity, String s)
        throws IOException
    {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        display.getMetrics(displaymetrics);
        options.inScaled = true;
        options.inDensity = 160;
        options.inTargetDensity = displaymetrics.densityDpi;
        return new BitmapDrawable(BitmapFactory.decodeStream(activity.getAssets().open(s), null, options));
    }
}

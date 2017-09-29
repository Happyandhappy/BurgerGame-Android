package com.magmamobile.mmusia.image;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageSetterSDK4
{

    public ImageSetterSDK4()
    {
    }

    public static void setImage(Drawable drawable, ImageView imageview)
    {
        try
        {
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            bitmap.setDensity(160);
            imageview.setImageBitmap(bitmap);
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}

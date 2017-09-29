package com.magmamobile.mmusia.image;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageSetterSDK3
{

    public ImageSetterSDK3()
    {
    }

    public static void setImage(Drawable drawable, ImageView imageview)
    {
        imageview.setImageDrawable(drawable);
    }
}

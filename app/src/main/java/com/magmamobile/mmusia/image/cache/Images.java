package com.magmamobile.mmusia.image.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.magmamobile.mmusia.MCommon;
import java.io.File;
import java.io.FileOutputStream;

public class Images
{

    private static final String TAG = "PodKast";

    public Images()
    {
    }

    private static File getCacheFile(Context context, String s)
    {
        File file;
        try
        {
            file = new File((new StringBuilder(String.valueOf(context.getCacheDir().getAbsolutePath()))).append("/").append(s).toString());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return file;
    }

    private static File getDataFile(Context context, String s)
    {
        File file;
        try
        {
            file = new File((new StringBuilder(String.valueOf(context.getFilesDir().getAbsolutePath()))).append("/").append(s).toString());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return file;
    }

    public static boolean isExist(Context context, String s)
    {
label0:
        {
            boolean flag;
            try
            {
                if(getCacheFile(context, s).exists())
                {
                    break label0;
                }
                flag = getDataFile(context, s).exists();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                return false;
            }
            if(!flag)
            {
                return false;
            }
        }
        return true;
    }

    public static Drawable loadImage(Context context, String s)
    {
        File file = getCacheFile(context, s);
        if(file == null || !file.exists())
        {
            return null;
        }
        Drawable drawable;
        try
        {
            drawable = BitmapDrawable.createFromPath(file.getPath());
        }
        catch(Exception exception)
        {
            MCommon.Log_e("PodKast", (new StringBuilder("Image Cache not Loaded :: ")).append(exception.getMessage()).toString());
            exception.printStackTrace();
            return null;
        }
        return drawable;
    }

    public static void saveImage(Context context, Drawable drawable, String s)
    {
        MCommon.Log_d("PodKast", "Save data to cache");
        if(drawable == null)
        {
            return;
        }
        File file = getCacheFile(context, s);
        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            ((BitmapDrawable)drawable).getBitmap().compress(android.graphics.Bitmap.CompressFormat.PNG, 10, fileoutputstream);
            fileoutputstream.close();
            MCommon.Log_d("PodKast", "Image Saved");
            return;
        }
        catch(Exception exception)
        {
            MCommon.Log_e("PodKast", (new StringBuilder("Image Cache not Saved :: ")).append(exception.getMessage()).toString());
            exception.printStackTrace();
            return;
        }
    }
}

package com.magmamobile.game.engine;

import android.graphics.*;

public final class BitmapUtils
{

    public BitmapUtils()
    {
    }

    public static final Bitmap createBitmap(Bitmap bitmap, int i)
    {
        switch(i)
        {
        case 0: // '\0'
        default:
            return bitmap;

        case 1: // '\001'
            return createBitmap90(bitmap);

        case 2: // '\002'
            return createBitmap180(bitmap);

        case 3: // '\003'
            return createBitmap270(bitmap);

        case 4: // '\004'
            return createBitmapFlipH(bitmap);
        }
    }

    public static final Bitmap createBitmap180(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        matrix.postRotate(180F);
        matrix.preTranslate(-bitmap.getWidth(), -bitmap.getHeight());
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        canvas.drawBitmap(bitmap, matrix, paint);
        return bitmap1;
    }

    public static final Bitmap createBitmap270(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        matrix.postRotate(270F);
        matrix.preTranslate(-bitmap.getWidth(), 0.0F);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        canvas.drawBitmap(bitmap, matrix, paint);
        return bitmap1;
    }

    public static final Bitmap createBitmap90(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        matrix.postRotate(90F);
        matrix.preTranslate(0.0F, -bitmap.getHeight());
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        canvas.drawBitmap(bitmap, matrix, paint);
        return bitmap1;
    }

    public static final Bitmap createBitmapFlipH(Bitmap bitmap)
    {
        Matrix matrix = new Matrix();
        matrix.setValues(new float[] {
            -1F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F
        });
        Matrix matrix1 = new Matrix();
        matrix1.postConcat(matrix);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix1, true);
    }
}

package com.magmamobile.game.engine;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.SoundPool;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

public final class GamePak
{

    private static String _asset;
    protected static Object _cache[];
    private static String _defaultAsset;

    public GamePak()
    {
    }

    public static String getAssetName(int i)
    {
        if(i == 32 || i > 236)
        {
            return _defaultAsset;
        } else
        {
            return _asset;
        }
    }

    public static Bitmap getBitmap(int i)
    {
        byte[] arr = getBytes(i);
        return BitmapFactory.decodeByteArray(arr, 0, arr.length);
    }

    public static Bitmap getBitmap(int i, android.graphics.BitmapFactory.Options options)
    {
        byte[] arr = getBytes(i);
        return BitmapFactory.decodeByteArray(arr, 0, arr.length, options);
    }

    public static byte[] getBytes(int i)
    {
        byte abyte0[];
        try
        {
            InputStream inputstream = Game.getContext().getAssets().open(getAssetName(i)+"/"+i+".png");
            int filesize = inputstream.available();
            abyte0 = new byte[filesize];
            inputstream.read(abyte0);
            inputstream.close();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
            return null;
        }
        return abyte0;
    }

    public static int getSound(SoundPool soundpool, int i)
    {
        int j;
        try
        {
            AssetFileDescriptor assetfiledescriptor = Game.getContext().getAssets().openFd(getAssetName(i)+"/"+i+".ogg");
            j = soundpool.load(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength(), 1);
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException();
        }
        return j;
    }

    public static String getText(int i)
    {
        String s;
        try
        {
            InputStream inputstream = Game.getContext().getAssets().open(getAssetName(i)+"/"+i+".txt");
            byte abyte0[] = new byte[inputstream.available()];
            inputstream.read(abyte0);
            inputstream.close();
            s = new String(abyte0, "utf-8");
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException();
        }
        return s;
    }

    public static void initialize(String s)
    {
        try
        {
            _defaultAsset = "K";
            _cache = new Object[278];
        }
        catch(Exception exception)
        {
            throw new RuntimeException("not found");
        }
        setPack(s);
    }

    public static InputStreamEx getStreamEx(int i)
            throws IOException
    {
        return new InputStreamEx(getStream(i));
    }

    public static InputStream getStream(int i)
            throws IOException
    {
        InputStream inputstream = Game.getContext().getAssets().open(getAssetName(i)+"/"+i+".txt");
        return inputstream;
    }

    public static void setPack(String s)
    {
        try
        {
            _asset = "K".concat(s);
        }
        catch(Exception exception)
        {
            throw new RuntimeException("not found");
        }
    }

    public static void terminate()
    {
    }
}

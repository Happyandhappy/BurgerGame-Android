package com.magmamobile.mmusia.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.parser.data.ApiBase;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ImageCache
{

    public ImageCache()
    {
    }

    private static final Bitmap bytes2Bitmap(byte abyte0[])
    {
        return BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
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

    private static final byte[] readCachedBitmap(Context context, String s)
    {
label0:
        {
            byte abyte0[];
            try
            {
                File file = getCacheFile(context, s);
                if(!file.exists())
                {
                    break label0;
                }
                FileInputStream fileinputstream = new FileInputStream(file);
                abyte0 = readStream(fileinputstream);
                fileinputstream.close();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                return null;
            }
            return abyte0;
        }
        return null;
    }

    private static final byte[] readStream(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
        do
        {
            int i = inputstream.read(abyte0);
            if(i == -1)
            {
                return bytearrayoutputstream.toByteArray();
            }
            bytearrayoutputstream.write(abyte0, 0, i);
        } while(true);
    }

    public static final Bitmap requestAppOfTheDayBitmap(Context context)
    {
        String s = MMUSIA.api.appodayIconUrl;
        String s1 = (new StringBuilder("appoday_")).append(MMUSIA.api.appodayId).toString();
        byte abyte0[] = readCachedBitmap(context, s1);
        if(abyte0 != null)
        {
            return bytes2Bitmap(abyte0);
        }
        byte abyte1[] = requestWebBitmap(s);
        if(abyte1 != null)
        {
            writeCachedBitmap(context, abyte1, s1);
            return bytes2Bitmap(abyte1);
        } else
        {
            return null;
        }
    }

    private static final byte[] requestWebBitmap(String s)
    {
        byte abyte0[];
        try
        {
            HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpurlconnection.setConnectTimeout(3000);
            httpurlconnection.setReadTimeout(3000);
            httpurlconnection.connect();
            InputStream inputstream = httpurlconnection.getInputStream();
            abyte0 = readStream(inputstream);
            inputstream.close();
            httpurlconnection.disconnect();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return abyte0;
    }

    private static final void writeCachedBitmap(Context context, byte abyte0[], String s)
    {
        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(getCacheFile(context, s));
            fileoutputstream.write(abyte0);
            fileoutputstream.close();
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}

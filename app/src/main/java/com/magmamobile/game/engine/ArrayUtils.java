package com.magmamobile.game.engine;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public final class ArrayUtils
{

    private static final char HEXDIGITS[] = "0123456789abcdef".toCharArray();

    public ArrayUtils()
    {
    }

    public static final boolean isNullOrEmpty(Object aobj[])
    {
        while(aobj == null || aobj.length <= 0) 
        {
            return true;
        }
        return false;
    }

    public static final String md5(Context context)
    {
        String s;
        try
        {
            FileInputStream fileinputstream = new FileInputStream(context.getApplicationInfo().sourceDir);
            s = md5(((InputStream) (fileinputstream)));
            fileinputstream.close();
        }
        catch(Exception exception)
        {
            return null;
        }
        return s;
    }

    public static final String md5(InputStream inputstream)
    {
        int i = 0;
        byte abyte0[];
        byte abyte1[];
        StringBuilder stringbuilder;
        int k;
        byte byte0;
        MessageDigest messagedigest;
        abyte0 = new byte[4096];
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
            while (true) {
                int j = inputstream.read(abyte0);
                if (j == -1)
                    break;
                messagedigest.update(abyte0, 0, j);
            }
            abyte1 = messagedigest.digest();
            stringbuilder = new StringBuilder(32);
            k = abyte1.length;
        }
        catch(Exception exception)
        {
            return null;
        }
        while(i < k)
        {
            byte0 = abyte1[i];
            stringbuilder.append(HEXDIGITS[0xf & byte0 >> 4]);
            stringbuilder.append(HEXDIGITS[byte0 & 0xf]);
            i++;
        }
        return stringbuilder.toString();
    }

}

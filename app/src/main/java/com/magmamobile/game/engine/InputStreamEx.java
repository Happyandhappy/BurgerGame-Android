package com.magmamobile.game.engine;

import java.io.*;

public class InputStreamEx extends DataInputStream
{

    public InputStreamEx(File file)
        throws FileNotFoundException
    {
        super(new FileInputStream(file));
    }

    public InputStreamEx(InputStream inputstream)
    {
        super(inputstream);
    }

    public InputStreamEx(InputStream inputstream, int i)
        throws IOException
    {
        super(inputstream);
        skip(i);
    }

    public final byte[] readBytes(int i)
        throws IOException
    {
        byte abyte0[] = new byte[i];
        read(abyte0, 0, i);
        return abyte0;
    }

    public final int[] readInt(int i)
        throws IOException
    {
        int ai[] = new int[i];
        int j = 0;
        do
        {
            if(j >= i)
            {
                return ai;
            }
            ai[j] = readInt();
            j++;
        } while(true);
    }

    public final String readLString()
        throws IOException
    {
        short word0 = readShort();
        if(word0 == 0)
        {
            return "";
        } else
        {
            byte abyte0[] = new byte[word0];
            read(abyte0, 0, word0);
            return new String(abyte0);
        }
    }

    public final String readString(int i)
        throws IOException
    {
        byte abyte0[] = new byte[i];
        read(abyte0, 0, i);
        return new String(abyte0);
    }
}

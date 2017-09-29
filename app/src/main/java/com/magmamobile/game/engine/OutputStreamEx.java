package com.magmamobile.game.engine;

import java.io.*;

public class OutputStreamEx extends DataOutputStream
{

    public OutputStreamEx(File file)
        throws FileNotFoundException
    {
        super(new FileOutputStream(file));
    }

    public OutputStreamEx(OutputStream outputstream)
    {
        super(outputstream);
    }

    public final void writeLString(String s)
        throws IOException
    {
        if(s == null)
        {
            writeShort(0);
            return;
        } else
        {
            byte abyte0[] = s.getBytes();
            writeShort(abyte0.length);
            write(abyte0);
            return;
        }
    }
}

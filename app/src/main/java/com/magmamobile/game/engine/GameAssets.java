package com.magmamobile.game.engine;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

public final class GameAssets
{

    public GameAssets()
    {
    }

    public static String getText(String s)
    {
        String s1;
        try
        {
            InputStream inputstream = Game.getContext().getAssets().open(s);
            byte abyte0[] = new byte[inputstream.available()];
            inputstream.read(abyte0);
            inputstream.close();
            s1 = new String(abyte0, "utf-8");
        }
        catch(IOException ioexception)
        {
            throw new RuntimeException();
        }
        return s1;
    }
}

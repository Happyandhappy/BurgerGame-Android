package com.magmamobile.game.engine.tmp;

import com.magmamobile.game.engine.*;
import java.io.IOException;

public final class AnimPackage
{

    public AnimBag bags[];

    public AnimPackage()
    {
    }

    public static final AnimPackage loadFromRes(int i)
    {
        int k;
        int l;
        int i1;
        AnimPackage animpackage;
        InputStreamEx inputstreamex;
        int j;
        AnimBag animbag;
        AnimFrame animframe;
        try
        {
            animpackage = new AnimPackage();
            inputstreamex = GamePak.getStreamEx(i);
            j = inputstreamex.readInt();
            animpackage.bags = new AnimBag[j];
            k = 0;
            while(k < j)
            {
                animbag = new AnimBag();
                animpackage.bags[k] = animbag;
                animbag.name = inputstreamex.readLString();
                l = inputstreamex.readInt();
                animbag.frames = new AnimFrame[l];
                i1 = 0;
                while(i1 < l) {
                    animframe = new AnimFrame();
                    animbag.frames[i1] = animframe;
                    animframe.time = inputstreamex.readInt();
                    animframe.image = Game.createBitmap(inputstreamex.readBytes(inputstreamex.readInt()));
                    i1++;
                }
                k++;
            }
            inputstreamex.close();
            return animpackage;
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
            return null;
        }
    }

    public AnimBag get(int i)
    {
        return bags[i];
    }

    public int getIndexOf(String s)
    {
        int i = 0;
        do
        {
            if(i >= bags.length)
            {
                throw new RuntimeException(s);
            }
            if(s.equals(bags[i].name))
            {
                return i;
            }
            i++;
        } while(true);
    }
}

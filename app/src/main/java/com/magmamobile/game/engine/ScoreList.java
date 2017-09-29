package com.magmamobile.game.engine;

import java.util.ArrayList;

public final class ScoreList extends ArrayList
{

    private static final long serialVersionUID = 1L;

    public ScoreList()
    {
    }

    public ScoreItem[] toArray()
    {
        ScoreItem ascoreitem[] = new ScoreItem[size()];
        int i = 0;
        int j = size();
        do
        {
            if(i >= j)
            {
                return ascoreitem;
            }
            ascoreitem[i] = (ScoreItem)get(i);
            i++;
        } while(true);
    }
}

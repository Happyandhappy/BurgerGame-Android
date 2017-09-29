package com.magmamobile.game.engine;

import java.util.ArrayList;
import java.util.Collections;

public class GameList extends ArrayList
    implements IGameEvents
{

    public GameList()
    {
    }

    public void clean()
    {
        int i = -1 + size();
        do
        {
            if(i < 0)
            {
                return;
            }
            if(!((GameObject)get(i)).enabled)
            {
                remove(i);
            }
            i--;
        } while(true);
    }

    public void onAction()
    {
        int i = 0;
        int j = size();
        do
        {
            if(i >= j)
            {
                return;
            }
            ((GameObject)get(i)).onAction();
            i++;
        } while(true);
    }

    public void onActionEx()
    {
        onAction();
        clean();
    }

    public void onRender()
    {
        int i = 0;
        int j = size();
        do
        {
            if(i >= j)
            {
                return;
            }
            ((GameObject)get(i)).onRender();
            i++;
        } while(true);
    }

    public void onSort()
    {
        Collections.sort(this);
    }
}

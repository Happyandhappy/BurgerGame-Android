package com.magmamobile.game.engine;

import java.util.*;

public abstract class GameArrayDyn
    implements IGameEvents
{
    private class ZComparator
        implements Comparator
    {
        public int compare(GameObject gameobject, GameObject gameobject1)
        {
            return gameobject.z <= gameobject1.z ? -1 : 1;
        }

        public int compare(Object obj, Object obj1)
        {
            return compare((GameObject)obj, (GameObject)obj1);
        }

        private ZComparator()
        {
            super();
        }

        ZComparator(ZComparator zcomparator1)
        {
            this();
        }
    }


    private ArrayList ar;
    private ZComparator zcomparator;

    public GameArrayDyn()
    {
        zcomparator = new ZComparator(null);
        ar = new ArrayList();
    }

    public final GameObject allocate()
    {
        GameObject gameobject = createObject();
        ar.add(gameobject);
        gameobject.onReset();
        return gameobject;
    }

    public final boolean canAllocate()
    {
        return true;
    }

    public final void clear()
    {
        ar.clear();
    }

    public abstract GameObject createObject();

    public final GameObject get(int i)
    {
        return (GameObject)ar.get(i);
    }

    public final int getSize()
    {
        return ar.size();
    }

    public final void onAction()
    {
        int i;
        int j;
        i = 0;
        j = ar.size();
        while(i < j){
            GameObject gameobject = (GameObject)ar.get(i);
            if(gameobject.enabled)
            {
                gameobject.onAction();
            }
            i++;
        }
        int k = -1 + ar.size();
        while(k >= 0)
        {
            if(!((GameObject)ar.get(k)).enabled)
            {
                ar.remove(k);
            }
            k--;
        }
    }

    public final void onPause()
    {
        int i = 0;
        int j = ar.size();
        do
        {
            if(i >= j)
            {
                return;
            }
            GameObject gameobject = (GameObject)ar.get(i);
            if(gameobject.enabled)
            {
                gameobject.onPause();
            }
            i++;
        } while(true);
    }

    public final void onRender()
    {
        int i = 0;
        int j = ar.size();
        do
        {
            if(i >= j)
            {
                return;
            }
            GameObject gameobject = (GameObject)ar.get(i);
            if(gameobject.visible && gameobject.enabled)
            {
                gameobject.onRender();
            }
            i++;
        } while(true);
    }

    public final void onResume()
    {
        int i = 0;
        int j = ar.size();
        do
        {
            if(i >= j)
            {
                return;
            }
            GameObject gameobject = (GameObject)ar.get(i);
            if(gameobject.enabled)
            {
                gameobject.onResume();
            }
            i++;
        } while(true);
    }

    public final void sortByZ()
    {
        Collections.sort(ar, zcomparator);
    }
}

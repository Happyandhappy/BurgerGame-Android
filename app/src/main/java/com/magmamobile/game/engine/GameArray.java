package com.magmamobile.game.engine;


public abstract class GameArray
    implements IGameEvents
{

    public GameObject array[];
    private int size;
    public int total;

    public GameArray(int i)
    {
        total = 0;
        size = i;
        array = createArray(i);
        int j = 0;
        do
        {
            if(j >= i)
            {
                return;
            }
            array[j] = createObject();
            j++;
        } while(true);
    }

    public final GameObject allocate()
    {
        if(total < size)
        {
            GameObject gameobject = array[total];
            gameobject.index = total;
            gameobject.selected = false;
            gameobject.visible = true;
            gameobject.enabled = true;
            gameobject.onReset();
            total = 1 + total;
            return gameobject;
        } else
        {
            return null;
        }
    }

    public final boolean canAllocate()
    {
        return total < size;
    }

    public final void clear()
    {
        int i = 0;
        do
        {
            if(i >= total)
            {
                total = 0;
                return;
            }
            array[i].enabled = false;
            i++;
        } while(true);
    }

    public abstract GameObject[] createArray(int i);

    public abstract GameObject createObject();

    public final int getSize()
    {
        return size;
    }

    public final boolean isFull()
    {
        return total == size;
    }

    public final void onAction()
    {
        int i = 0;
        while(i < total){
            GameObject gameobject = array[i];
            if(gameobject.enabled)
            {
                gameobject.onAction();
            }
            i++;
        }
        int j = -1 + total;
        while(j >= 0)
        {
            GameObject gameobject1 = array[j];
            if(!gameobject1.enabled)
            {
                total = -1 + total;
                array[j] = array[total];
                array[total] = gameobject1;
            }
            j--;

        }
    }

    public final void onPause()
    {
        int i = 0;
        do
        {
            if(i >= total)
            {
                return;
            }
            GameObject gameobject = array[i];
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
        do
        {
            if(i >= total)
            {
                return;
            }
            GameObject gameobject = array[i];
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
        do
        {
            if(i >= total)
            {
                return;
            }
            GameObject gameobject = array[i];
            if(gameobject.enabled)
            {
                gameobject.onResume();
            }
            i++;
        } while(true);
    }
}

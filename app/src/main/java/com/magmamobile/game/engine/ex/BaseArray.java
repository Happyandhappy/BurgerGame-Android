package com.magmamobile.game.engine.ex;

import java.util.ArrayList;

public class BaseArray extends BaseObject
{

    protected ArrayList array;
    protected Class type;

    public BaseArray(Class class1)
    {
        array = new ArrayList();
        visible = true;
        enabled = true;
        type = class1;
    }

    public BaseObject allocate()
    {
        BaseObject baseobject;
        try
        {
            baseobject = (BaseObject)type.newInstance();
            array.add(baseobject);
        }
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
        return baseobject;
    }

    public void clear()
    {
        array.clear();
    }

    public BaseObject get(int i)
    {
        return (BaseObject)array.get(i);
    }

    public int getSize()
    {
        return array.size();
    }

    public Class getType()
    {
        return type;
    }

    public void onAction()
    {
        if(!enabled)
            return;
        ArrayList arraylist = array;
        try {
            synchronized (arraylist) {
                int i = array.size();
                int j = 0;
                while (j < i) {
                    BaseObject baseobject = (BaseObject) array.get(j);
                    if (baseobject.enabled) {
                        baseobject.onAction();
                    }
                    j++;
                }
            }
            ArrayList arraylist1 = array;
            synchronized (arraylist1) {
                int k = -1 + array.size();
                while (k >= 0) {
                    if (!((BaseObject) array.get(k)).enabled) {
                        array.remove(k);
                    }
                    k--;
                }
            }
        }catch(Exception e){}
    }

    public void onRender()
    {
        if(!visible)
            return;
        ArrayList arraylist = array;
        try {
            synchronized (arraylist) {
                int i = array.size();
                int j = 0;
                while (j < i) {
                    BaseObject baseobject = (BaseObject) array.get(j);
                    if (baseobject.visible) {
                        baseobject.onRender();
                    }
                    j++;
                }
            }
        }catch(Exception e){}
    }
}

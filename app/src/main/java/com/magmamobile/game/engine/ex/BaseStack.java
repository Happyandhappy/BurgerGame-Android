package com.magmamobile.game.engine.ex;

import java.util.Stack;

public class BaseStack extends BaseObject
{

    protected Stack array;
    protected Class type;

    public BaseStack(Class class1)
    {
        array = new Stack();
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
            array.push(baseobject);
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
        {
            return;
        }
        Stack stack = array;
        try {
            synchronized (stack) {
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
            Stack stack1 = array;
            synchronized (stack1) {
                int k = -1 + array.size();
                while (k >= 0) {
                    if (!((BaseObject) array.get(k)).enabled) {
                        array.remove(k);
                    }
                    k--;
                }
            }
        }catch (Exception e) {}
    }

    public void onRender()
    {
        if(!visible)
            return;
        Stack stack = array;
        try {
            synchronized (stack) {
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
        }catch(Exception e) {}
    }

    public BaseObject pop()
    {
        return (BaseObject)array.pop();
    }

    public void push(BaseObject baseobject)
    {
        array.push(baseobject);
    }
}

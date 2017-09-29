package com.magmamobile.game.engine.ex;

import com.magmamobile.game.engine.IGameEvents;

public class BaseObject
    implements IGameEvents
{

    public boolean enabled;
    public boolean visible;

    public BaseObject()
    {
    }

    public boolean hide()
    {
        visible = false;
        return true;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void onAction()
    {
    }

    public void onRender()
    {
    }

    public void setEnabled(boolean flag)
    {
        enabled = flag;
    }

    public void setVisible(boolean flag)
    {
        visible = flag;
    }

    public boolean show()
    {
        visible = true;
        return true;
    }
}

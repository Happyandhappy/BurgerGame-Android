package com.magmamobile.game.engine.menus;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.GameApplication;

public final class MenuItemEx
{

    protected int _groupId;
    protected Drawable _icon;
    protected int _itemId;
    protected int _order;
    protected CharSequence _title;
    protected boolean _visible;

    protected MenuItemEx()
    {
        _icon = null;
        _visible = true;
        _groupId = 0;
        _order = 0;
        _itemId = 0;
    }

    public int getGroupId()
    {
        return _groupId;
    }

    public Drawable getIcon()
    {
        return _icon;
    }

    public int getItemId()
    {
        return _itemId;
    }

    public int getOrder()
    {
        return _order;
    }

    public CharSequence getTitle()
    {
        return _title;
    }

    public boolean isVisible()
    {
        return _visible;
    }

    public MenuItemEx setIcon(int i)
    {
        _icon = Game.getApplication().getResources().getDrawable(i);
        return this;
    }

    public MenuItemEx setIcon(Drawable drawable)
    {
        _icon = drawable;
        return this;
    }

    public MenuItemEx setTitle(int i)
    {
        _title = Game.getApplication().getResources().getString(i);
        return this;
    }

    public MenuItemEx setTitle(CharSequence charsequence)
    {
        _title = charsequence;
        return this;
    }

    public MenuItemEx setVisible(boolean flag)
    {
        _visible = flag;
        return this;
    }
}

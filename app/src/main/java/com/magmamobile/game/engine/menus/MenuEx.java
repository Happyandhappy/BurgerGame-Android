package com.magmamobile.game.engine.menus;

import android.content.res.Resources;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.GameActivity;
import java.util.ArrayList;

public final class MenuEx
{

    protected ArrayList _items;

    public MenuEx()
    {
        _items = new ArrayList();
    }

    public MenuItemEx add(int i, int j, int k, int l)
    {
        return add(i, j, k, ((CharSequence) (Game.getContext().getResources().getString(l))));
    }

    public MenuItemEx add(int i, int j, int k, CharSequence charsequence)
    {
        MenuItemEx menuitemex = new MenuItemEx();
        menuitemex._groupId = i;
        menuitemex._itemId = j;
        menuitemex._order = k;
        menuitemex._title = charsequence;
        _items.add(menuitemex);
        return menuitemex;
    }

    public MenuItemEx findItem(int i)
    {
        MenuItemEx menuitemex = null;
        int j = 0;
        while(j < _items.size()) {
            menuitemex = (MenuItemEx)_items.get(j);
            if(menuitemex._itemId == i) {
                return menuitemex;
            }
            j++;
        }
        return null;
    }

    public MenuItemEx getItem(int i)
    {
        return (MenuItemEx)_items.get(i);
    }

    public boolean hasVisibleItems()
    {
        int i = 0;
        do
        {
            if(i >= _items.size())
            {
                return false;
            }
            if(((MenuItemEx)_items.get(i)).isVisible())
            {
                return true;
            }
            i++;
        } while(true);
    }

    public void setGroupVisible(int i, boolean flag)
    {
        int j = 0;
        do
        {
            if(j >= _items.size())
            {
                return;
            }
            MenuItemEx menuitemex = (MenuItemEx)_items.get(j);
            if(menuitemex.getGroupId() == i)
            {
                menuitemex.setVisible(flag);
            }
            j++;
        } while(true);
    }

    public int size()
    {
        return _items.size();
    }
}

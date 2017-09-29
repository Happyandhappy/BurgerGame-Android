package com.magmamobile.game.engine;

import java.util.ArrayList;

public class FocusBag
{

    private int _index;
    private ArrayList _table;

    public FocusBag()
    {
        clear();
    }

    public void addObject(GameObject gameobject)
    {
        _table.add(gameobject);
    }

    public void clear()
    {
        _table = new ArrayList();
        _index = -1;
    }

    public GameObject getFocus()
    {
        if(_table != null && _index >= 0 && _index < _table.size())
        {
            return (GameObject)_table.get(_index);
        } else
        {
            return null;
        }
    }

    public void goFirst()
    {
        _index = 0;
        if(_index >= 0 && _index < _table.size())
        {
            ((GameObject)_table.get(_index)).selected = true;
        }
    }

    public void goNext()
    {
        if(_index >= 0 && _index < _table.size())
        {
            ((GameObject)_table.get(_index)).selected = false;
        }
        _index = 1 + _index;
        if(_index >= _table.size())
        {
            _index = 0;
        }
        if(_index >= 0 && _index < _table.size())
        {
            ((GameObject)_table.get(_index)).selected = true;
        }
    }

    public void goPrevious()
    {
        if(_index >= 0 && _index < _table.size())
        {
            ((GameObject)_table.get(_index)).selected = false;
        }
        _index = -1 + _index;
        if(_index < 0)
        {
            _index = -1 + _table.size();
        }
        if(_index >= 0 && _index < _table.size())
        {
            ((GameObject)_table.get(_index)).selected = true;
        }
    }

    public void removeObject(GameObject gameobject)
    {
        _table.remove(gameobject);
    }
}

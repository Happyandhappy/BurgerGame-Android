package com.magmamobile.game.engine;

import java.util.ArrayList;

public final class StageManager
{

    private static boolean _clear;
    public static IGameStage _currentStage;
    private static int _index;
    private static boolean _loaded;
    private static boolean _once;
    private static ArrayList _stages;

    public StageManager()
    {
    }

    public static GameStage addStage(GameStage gamestage)
    {
        if(_once)
        {
            return gamestage;
        } else
        {
            reloadIfNull();
            gamestage.setId(_stages.size());
            _stages.add(gamestage);
            return gamestage;
        }
    }

    public static void addStage(Class class1)
    {
        if(_once)
        {
            return;
        } else
        {
            reloadIfNull();
            GameStage gamestage = createStage(class1);
            gamestage.setId(_stages.size());
            _stages.add(gamestage);
            return;
        }
    }

    private static void clear()
    {
        if(_clear)
        {
            clearNow();
        }
    }

    public static void clearNow()
    {
        _once = false;
        _clear = false;
        _loaded = false;
        _currentStage = null;
        _stages = null;
        _index = 0;
    }

    public static void clearStage()
    {
        if(_stages == null)
        {
            return;
        } else
        {
            clearNow();
            initializeStages();
            return;
        }
    }

    private static GameStage createStage(Class class1)
    {
        GameStage gamestage;
        try
        {
            gamestage = (GameStage)class1.newInstance();
        }
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
        return gamestage;
    }

    public static void free()
    {
        _clear = true;
    }

    public static IGameStage getCurrentStage()
    {
        return _currentStage;
    }

    public static int getStage()
    {
        return _index;
    }

    public static GameStage getStage(int i)
    {
        return (GameStage)_stages.get(i);
    }

    public static int getStageCount()
    {
        if(_stages != null)
        {
            return _stages.size();
        } else
        {
            return 0;
        }
    }

    public static void initializeStages()
    {
        reloadIfNull();
        if(_once)
            return;
        int i;
        _once = true;
        i = _stages.size();
        if(i > 0) {
            int k = 0;
            while (k < i) {
                ((GameStage) _stages.get(k)).onInitialize();
                k++;
            }
        }
        _loaded = true;
        _currentStage = null;
        int j = _index;
        _index = -1;
        setStage(j);
    }

    public static boolean isQuit()
    {
        return _index == -1;
    }

    public static void onTerminate()
    {
        if(_currentStage != null)
        {
            _currentStage.onLeave();
        }
        terminateStages();
    }

    private static void reloadIfNull()
    {
        if(_stages == null)
        {
            _loaded = false;
            _currentStage = null;
            _index = 0;
            _stages = new ArrayList();
        }
    }

    public static final void reset()
    {
        if(_stages == null)
            return;
        int i = 0;
        while(i < _stages.size())
        {
            ((GameStage)_stages.get(i)).onInitialize();
            i++;
        }
        if(_currentStage != null)
        {
            _currentStage.onEnter();
        }
    }

    public static void setFirstStage(int i)
    {
        if(!_once)
        {
            setStage(i);
            return;
        } else
        {
            Game.invalidate();
            return;
        }
    }

    public static void setStage(int i)
    {
        if(!_loaded)
        {
            _index = i;
        } else
        if(_index != i)
        {
            if(_currentStage != null)
            {
                _currentStage.onLeave();
                _currentStage.setActive(false);
            }
            _index = i;
            if(_index != -1)
            {
                if(_index <= 0)
                {
                    if(_stages != null && _stages.size() > 0)
                    {
                        _index = 1;
                        _currentStage = (IGameStage)_stages.get(-1 + _index);
                    } else
                    {
                        _currentStage = null;
                    }
                } else
                {
                    _currentStage = (IGameStage)_stages.get(-1 + _index);
                }
                if(_currentStage != null)
                {
                    _currentStage.onEnter();
                    _currentStage.setActive(true);
                    return;
                }
            } else
            {
                _clear = true;
                _currentStage = null;
                Game.running = false;
                return;
            }
        }
    }

    public static void terminateStages()
    {
        if(_stages != null) {
            int i = _stages.size();
            if (i <= 0) {
                int j = 0;
                while (j < i) {
                    ((GameStage) _stages.get(j)).onTerminate();
                    j++;
                }
            }
        }
        clear();
    }
}

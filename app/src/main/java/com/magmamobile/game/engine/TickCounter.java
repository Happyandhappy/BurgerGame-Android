package com.magmamobile.game.engine;

public final class TickCounter
{

    private int _modulo;
    private long _tick;

    public TickCounter(int i)
    {
        _modulo = i;
        _tick = Game.tick;
    }

    public int getModulo()
    {
        return _modulo;
    }

    public int getValue()
    {
        return (int)((Game.tick - _tick) % (long)_modulo);
    }

    public void reset()
    {
        _tick = Game.tick;
    }

    public void setModulo(int i)
    {
        _modulo = i;
    }
}

package com.magmamobile.game.engine;

public class FrameCounter
{

    private int _frame;
    private int _modulo;
    private long _tick;

    public FrameCounter(int i, int j)
    {
        _frame = i;
        _modulo = j;
        _tick = Game.tick;
    }

    public int getModulo()
    {
        return _modulo;
    }

    public int getValue()
    {
        return (int)(((Game.tick - _tick) / (long)_frame) % (long)_modulo);
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

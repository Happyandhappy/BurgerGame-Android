package com.magmamobile.game.engine;


enum GameRenderMode
{
    Realtime("Realtime", 0),
    OnDemand("OnDemand", 1);

    private GameRenderMode(String s, int i)
    {
    }

    static
    {
        GameRenderMode agamerendermode[] = new GameRenderMode[2];
        agamerendermode[0] = Realtime;
        agamerendermode[1] = OnDemand;
    }
}

package com.magmamobile.game.engine;

enum GameRate
{
    fastest("fastest", 0),
    faster("faster", 1),
    fast("fast", 2),
    normal("normal", 3),
    slow("slow", 4),
    slowest("slowest", 5);

    private static final float FPS[] = {
        0.0F, 62.5F, 30.30303F, 24.39024F, 12.5F, 4.166667F
    };
    private static final int TIMES[];

    private GameRate(String s, int i)
    {
    }

    public final float getFps()
    {
        return FPS[ordinal()];
    }

    public final long getTime()
    {
        return (long)TIMES[ordinal()];
    }

    static 
    {
        GameRate agamerate[] = new GameRate[6];
        agamerate[0] = fastest;
        agamerate[1] = faster;
        agamerate[2] = fast;
        agamerate[3] = normal;
        agamerate[4] = slow;
        agamerate[5] = slowest;
        int ai[] = new int[6];
        ai[1] = 16;
        ai[2] = 33;
        ai[3] = 41;
        ai[4] = 80;
        ai[5] = 240;
        TIMES = ai;
    }
}

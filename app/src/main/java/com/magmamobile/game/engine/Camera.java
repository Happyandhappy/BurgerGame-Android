package com.magmamobile.game.engine;

public final class Camera
{

    public static boolean d;
    public static int maxX;
    public static int maxY;
    public static int tgx;
    public static int tgy;
    public static boolean tsf;
    public static float tsp;
    public static int tsx;
    public static int tsy;
    public static int tx;
    public static int ty;
    public static int x;
    public static int y;

    public Camera()
    {
    }

    public static void anim()
    {
        if(!tsf)
        {
            return;
        }
        tsp = (float)(0.10000000000000001D + (double)tsp);
        if(tsp >= 1.0F)
        {
            tsf = false;
        }
        x = (int)MathUtils.lerpDecelerate(tsx, tgx, tsp);
        y = (int)MathUtils.lerpDecelerate(tsy, tgy, tsp);
    }

    public static void follow()
    {
        x += tx - TouchScreen.x;
        y += ty - TouchScreen.y;
        tx = TouchScreen.x;
        ty = TouchScreen.y;
        tsf = false;
    }

    public static void follow(int i, int j)
    {
        tsf = false;
        x = i - Game.mBufferCW;
        y = j - Game.mBufferCH;
        if(x < 0)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = 0;
        }
        int k = maxX - Game.mBufferCW;
        if(x > k)
        {
            x = k;
        }
        int l = maxY - Game.mBufferCH;
        if(y > l)
        {
            y = l;
        }
    }

    public static void follow(GameObject gameobject)
    {
        tsf = false;
        x = (int)gameobject.x - Game.mBufferCW;
        y = (int)gameobject.y - Game.mBufferCH;
        if(x < 0)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = 0;
        }
        int i = maxX - Game.mBufferCW;
        if(x > i)
        {
            x = i;
        }
        int j = maxY - Game.mBufferCH;
        if(y > j)
        {
            y = j;
        }
    }

    public static void followNoLimit(int i, int j)
    {
        x = i - Game.mBufferCW;
        y = j - Game.mBufferCH;
        tsf = false;
    }

    public static void setTarget(int i, int j)
    {
        tgx = i - Game.mBufferCW;
        tgy = j - Game.mBufferCH;
        tsx = x;
        tsy = y;
        tsp = 0.0F;
        tsf = true;
    }
}

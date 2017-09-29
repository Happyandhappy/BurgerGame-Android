package com.magmamobile.game.engine.ex;


public class Vector2D
{

    public float x;
    public float y;

    public Vector2D()
    {
    }

    public Vector2D(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public void add(Vector2D vector2d)
    {
        x = x + vector2d.x;
        y = y + vector2d.y;
    }

    public void div(Vector2D vector2d)
    {
        x = x / vector2d.x;
        y = y / vector2d.y;
    }

    public float dot()
    {
        return x * x + y * y;
    }

    public float dot(Vector2D vector2d)
    {
        return x * vector2d.x + y * vector2d.y;
    }

    public float length()
    {
        return (float)Math.sqrt(x * x + y * y);
    }

    public void mul(Vector2D vector2d)
    {
        x = x * vector2d.x;
        y = y * vector2d.y;
    }

    public void normalize()
    {
        float f = (float)Math.sqrt(x * x + y * y);
        if(f != 0.0F)
        {
            x = x / f;
            y = y / f;
        }
    }

    public void set(float f, float f1)
    {
        x = f;
        y = f1;
    }

    public void sub(Vector2D vector2d)
    {
        x = x - vector2d.x;
        y = y - vector2d.y;
    }
}

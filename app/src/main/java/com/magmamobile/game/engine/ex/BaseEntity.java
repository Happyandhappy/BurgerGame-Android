package com.magmamobile.game.engine.ex;

public class BaseEntity extends BaseObject
{

    public Vector2D pos;
    public Vector2D size;

    public BaseEntity()
    {
        size = new Vector2D();
        pos = new Vector2D();
    }
}

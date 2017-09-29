package com.magmamobile.game.engine;

public final class Flash
{

    private static int alpha;
    private static float factor;
    private static boolean visible;

    public Flash()
    {
    }

    public static void flash()
    {
        visible = true;
        alpha = 255;
        factor = 0.0F;
    }

    public static void onAction()
    {
        if(visible)
        {
            alpha = (int)MathUtils.lerpDecelerate(255F, 0.0F, factor);
            factor = 0.1F + factor;
            if(factor > 1.0F)
            {
                visible = false;
                return;
            }
        }
    }

    public static void onRender()
    {
        if(!visible)
        {
            return;
        } else
        {
            Game.drawColor(0xffffff | alpha << 24);
            return;
        }
    }

    public static void reset()
    {
        visible = false;
    }
}

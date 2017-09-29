package com.magmamobile.game.Burger.ui;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.engine.*;

public class GalleryButton
{

    public static final int TYPE_LEFT = 1;
    public static final int TYPE_RIGHT = 2;
    private final int DECAL = Game.scalei(8);
    private final int DECAL_OUT = Game.scalei(64);
    private final int MOVE_ANIM = 3;
    private final int MOVE_IN = 4;
    private final int MOVE_NONE = 6;
    private final int MOVE_OUT = 5;
    private final float SPEED = 0.075F;
    private boolean activated;
    private int dx;
    private float f;
    private int phase;
    private Rect rect;
    public boolean selected;
    private int type;
    public boolean visible;
    private int x;
    private int y;

    public GalleryButton(int i)
    {
        type = i;
        rect = new Rect(0, 0, (int)(1.5F * (float)BitmapManager.galleryButtons[0].getWidth()), BitmapManager.galleryButtons[0].getHeight());
        init();
    }

    public void animate(float f1)
    {
        int i;
label0:
        {
label1:
            {
                if(visible)
                {
                    i = (int)(Math.sin(3.1415926535897931D * (double)f1) * (double)DECAL);
                    switch(phase)
                    {
                    default:
                        dx = i;
                        break;

                    case 4: // '\004'
                        break label1;

                    case 5: // '\005'
                        break label0;
                    }
                }
                return;
            }
            f = 0.075F + f;
            if(f > 1.0F)
            {
                f = 1.0F;
                activated = true;
                phase = 3;
            }
            dx = (int)MathUtils.lerpDecelerate(DECAL_OUT, i, f);
            return;
        }
        f = 0.075F + f;
        if(f > 1.0F)
        {
            f = 1.0F;
            visible = false;
            phase = 6;
        }
        dx = (int)MathUtils.lerpAccelerate(i, DECAL_OUT, f);
    }

    public void draw()
    {
        byte byte0 = 2;
        if(visible)
        {
            boolean flag;
            int i;
            int j;
            int k;
            if(type != byte0)
            {
                byte0 = 0;
            }
            flag = selected;
            i = 0;
            if(flag)
            {
                boolean flag1 = activated;
                i = 0;
                if(flag1)
                {
                    i = 1;
                }
            }
            j = byte0 + i;
            if(type == 1)
            {
                k = -1 * dx;
            } else
            {
                k = dx;
            }
            Game.drawBitmap(BitmapManager.galleryButtons[j], k + x, y);
        }
    }

    public int getX()
    {
        return x;
    }

    public void init()
    {
        selected = false;
        visible = true;
        activated = true;
        f = 0.0F;
        phase = 3;
    }

    public boolean isInRect()
    {
        return activated && TouchScreen.isInRect(rect.left, rect.top, rect.width(), rect.height());
    }

    public void moveIn()
    {
        f = 0.0F;
        phase = 4;
        visible = true;
        selected = false;
        activated = false;
    }

    public void moveOut()
    {
        f = 0.0F;
        phase = 5;
        selected = false;
        activated = false;
    }

    public void setX(int i)
    {
        x = i;
        int j;
        if(type == 1)
        {
            j = i - rect.width() / 3;
        } else
        {
            j = i;
        }
        rect.offsetTo(j, y);
    }

    public void setY(int i)
    {
        y = i;
        rect.offsetTo(x, i);
    }
}

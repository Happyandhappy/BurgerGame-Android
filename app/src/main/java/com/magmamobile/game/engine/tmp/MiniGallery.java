package com.magmamobile.game.engine.tmp;

import com.magmamobile.game.engine.*;

public abstract class MiniGallery
{

    private int currX;
    private int delta;
    private float factor;
    private boolean hook;
    private int index;
    private int lastPos;
    private int lastX;
    private boolean moving;
    private int pageTopPadding;
    private int top;

    public MiniGallery()
    {
        pageTopPadding = 10;
    }

    public int getFriction()
    {
        return 16;
    }

    protected abstract int getItemCount();

    protected abstract int getItemHeight();

    protected abstract int getItemWidth();

    public int getPage()
    {
        return index;
    }

    public int getPageTopPadding()
    {
        return pageTopPadding;
    }

    protected abstract int getSelectorWidth();

    public int getTop()
    {
        return top;
    }

    public void onAction()
    {
        if(!TouchScreen.eventDown) {
            if(TouchScreen.eventMoving)
            {
                if(hook)
                {
                    if(moving)
                    {
                        currX = currX + (TouchScreen.x - lastX);
                        index = MathUtils.minmaxi(-MathUtils.Int((float)(currX + (getItemWidth() >> 1)) / (float)getItemWidth()), 0, -1 + getItemCount());
                        lastX = TouchScreen.x;
                        delta = currX;
                    } else
                    if(Math.abs(TouchScreen.x - lastX) > getFriction())
                    {
                        moving = true;
                    }
                }
            } else
            if(TouchScreen.eventUp && hook)
            {
                lastPos = -index * getItemWidth();
                hook = false;
                factor = 0.0F;
                if(!moving)
                {
                    onItemSelected(index);
                    delta = lastPos;
                } else
                {
                    moving = false;
                }
            }
        }else {
            if (TouchScreen.isInRect(0, getTop(), Game.mBufferWidth, getItemHeight() + getPageTopPadding())) {
                hook = true;
                lastX = TouchScreen.x;
            }
        }
        if(!hook && factor < 1.0F)
        {
            factor = 0.1F + factor;
            currX = (int)MathUtils.lerpDecelerate(delta, lastPos, factor);
        }
    }

    protected abstract void onDrawItem(int i, int j, int k, boolean flag);

    protected abstract void onDrawSelector(int i, int j, int k, boolean flag);

    protected abstract void onItemSelected(int i);

    public void onRender()
    {
        int i = getTop();
        int j = getTop() + getItemHeight() + getPageTopPadding();
        int k = 0;
        do
        {
            if(k >= getItemCount())
            {
                return;
            }
            int l = (160 - getItemWidth() / 2) + k * getItemWidth() + currX;
            boolean flag;
            int i1;
            boolean flag1;
            if(k == index)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            onDrawItem(k, l, i, flag);
            i1 = (160 - (getItemCount() * getSelectorWidth()) / 2) + k * getSelectorWidth();
            if(k == index)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            onDrawSelector(k, i1, j, flag1);
            k++;
        } while(true);
    }

    public void reset()
    {
        hook = false;
        factor = 0.0F;
        lastPos = 0;
        index = 0;
        lastX = 0;
        delta = 0;
        currX = 0;
    }

    public void setPage(int i)
    {
        delta = 0;
        lastPos = -MathUtils.minmaxi(i, 0, -1 + getItemCount()) * getItemWidth();
    }

    public void setPageTopPadding(int i)
    {
        pageTopPadding = i;
    }

    public void setTop(int i)
    {
        top = i;
    }
}

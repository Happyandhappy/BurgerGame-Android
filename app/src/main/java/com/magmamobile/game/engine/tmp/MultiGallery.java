package com.magmamobile.game.engine.tmp;

import com.magmamobile.game.engine.*;

public abstract class MultiGallery
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

    public MultiGallery()
    {
        pageTopPadding = 10;
    }

    protected abstract int getColCount();

    public int getFriction()
    {
        return 16;
    }

    protected abstract int getItemCount();

    protected abstract int getItemHeight();

    protected abstract int getItemWidth();

    public int getItemsPerPage()
    {
        return getColCount() * getRowCount();
    }

    public int getPageCount()
    {
        return 1 + MathUtils.maxi((-1 + getItemCount()) / getItemsPerPage(), 0);
    }

    public int getPageHeight()
    {
        return getRowCount() * getItemHeight();
    }

    public int getPageTopPadding()
    {
        return pageTopPadding;
    }

    public int getPageWidth()
    {
        return getColCount() * getItemWidth();
    }

    protected abstract int getRowCount();

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
                        index = MathUtils.minmaxi(-MathUtils.Int((float)(currX + (getPageWidth() >> 1)) / (float)getPageWidth()), 0, -1 + getPageCount());
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
                lastPos = -index * getPageWidth();
                hook = false;
                factor = 0.0F;
                if(!moving)
                {
                    if(Math.abs(lastPos - delta) < 10 && TouchScreen.isIn(160, 240, getPageWidth() >> 1, getPageHeight() >> 1))
                    {
                        onItemSelected(MathUtils.minmaxi(MathUtils.minmaxi((TouchScreen.x - (320 - getPageWidth()) / 2) / getItemWidth(), 0, -1 + getColCount()) + MathUtils.minmaxi((TouchScreen.y - (480 - getPageHeight()) / 2) / getItemHeight(), 0, -1 + getRowCount()) * getColCount() + index * getItemsPerPage(), 0, -1 + getItemCount()));
                    }
                    delta = lastPos;
                } else
                {
                    moving = false;
                }
            }
        }else {
            if (TouchScreen.isInRect(0, getTop(), Game.mBufferWidth, getPageHeight() + getPageTopPadding())) {
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
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int l1;
        boolean flag;
        i = 0;
        j = getPageCount();
        k = (320 - getPageWidth()) / 2;
        l = getTop();
        i1 = getTop() + getPageHeight() + getPageTopPadding();
        j1 = 0;
        int k1;
        while(j1 < j) {
            k1 = 0;
            while (k1 < getRowCount()) {
                l1 = 0;
                while (l1 < getColCount()) {
                    if (i < getItemCount()) {
                        onDrawItem(i, k + (l1 * getItemWidth() + (getItemWidth() >> 1)) + currX + j1 * getPageWidth(), l + (k1 * getItemHeight() + (getItemHeight() >> 1)), false);
                    }
                    i++;
                    l1++;
                }
                k1++;
            }
            int i2 = (160 - (j * getSelectorWidth()) / 2) + j1 * getSelectorWidth();
            if (j1 == index) {
                flag = true;
            } else {
                flag = false;
            }
            onDrawSelector(j1, i2, i1, flag);
            j1++;
        }
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

    public void setPageTopPadding(int i)
    {
        pageTopPadding = i;
    }

    public void setTop(int i)
    {
        top = i;
    }
}

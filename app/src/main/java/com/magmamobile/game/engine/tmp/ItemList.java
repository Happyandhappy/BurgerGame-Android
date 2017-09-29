package com.magmamobile.game.engine.tmp;

import com.magmamobile.game.engine.TouchScreen;
import com.magmamobile.game.engine.VelocityTracker;

public abstract class ItemList
{

    private BufferCache cacheBottom;
    private BufferCache cacheTop;
    private float delta;
    private float ly;
    private boolean moving;
    private float scrollY;
    private VelocityTracker tracker;
    private float velocity;

    public ItemList()
    {
        tracker = VelocityTracker.obtain();
        cacheTop = new BufferCache(getLeft(), getTop() - getItemHeight(), getLeft() + getWidth(), getTop());
        cacheBottom = new BufferCache(getLeft(), getTop() + getHeight(), getLeft() + getWidth(), getTop() + getHeight() + getItemHeight());
    }

    public abstract int getHeight();

    public abstract int getItemCount();

    public abstract int getItemHeight();

    public abstract int getLeft();

    public abstract int getTop();

    public abstract int getWidth();

    public void onAction()
    {
        if(!TouchScreen.eventUp) {
            if(TouchScreen.eventDown)
            {
                if(TouchScreen.isInRect(getLeft(), getTop(), getLeft() + getWidth(), getTop() + getHeight()))
                {
                    tracker.addMovement(TouchScreen.x, TouchScreen.y);
                    ly = TouchScreen.y;
                    delta = 0.0F;
                    velocity = 0.0F;
                    moving = true;
                }
            } else if(TouchScreen.eventMoving && moving) {
                tracker.addMovement(TouchScreen.x, TouchScreen.y);
                scrollY = scrollY + ((float)TouchScreen.y - ly);
                delta = delta + (ly - (float)TouchScreen.y);
                ly = TouchScreen.y;
            }
        }else {
            if (moving) {
                moving = false;
                tracker.addMovement(TouchScreen.x, TouchScreen.y);
                tracker.computeCurrentVelocity(100);
                velocity = tracker.getYVelocity();
                if (Math.abs(delta) < 2.0F) {
                    int i = -1 + (int) (((float) TouchScreen.y - scrollY) / (float) getItemHeight());
                    if (i >= 0 && i < getItemCount()) {
                        onItemClick(i);
                    }
                }
            }
        }
        scrollY = scrollY + velocity;
        if(velocity != 0.0F)
        {
            velocity = 0.9F * velocity;
            if(Math.abs(velocity) < 1.0F)
            {
                velocity = 0.0F;
            }
        }
    }

    public abstract void onDrawItem(int i, int j, int k);

    protected void onItemClick(int i)
    {
    }

    public void onRender()
    {
        int i = getItemCount();
        if(i == 0)
            return;
        int j;
        int l;
        int i1;
        cacheTop.capture();
        cacheBottom.capture();
        j = getTop() + getHeight();
        int k = i * getItemHeight() - getHeight();
        if(-scrollY > (float)k)
        {
            scrollY = -k;
        }
        if(-scrollY < 0.0F)
        {
            scrollY = 0.0F;
        }
        l = getTop() + (int)scrollY;
        i1 = 0;
        while(i1 < i) {
            if(l > getTop() - getItemHeight() && l <= j)
            {
                onDrawItem(i1, getLeft(), l);
            }
            l += getItemHeight();
            i1++;
        }
        cacheTop.redraw();
        cacheBottom.redraw();
    }

    public void refresh()
    {
        moving = false;
        scrollY = 0.0F;
        delta = 0.0F;
        velocity = 0.0F;
        ly = 0.0F;
    }
}

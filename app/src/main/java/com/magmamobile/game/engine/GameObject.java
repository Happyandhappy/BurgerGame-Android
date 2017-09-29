package com.magmamobile.game.engine;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class GameObject
    implements IGameEvents, Comparable
{

    public static final int CENTER_CONTENT = -9999;
    public static final int FILL_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    private Animation _animation;
    public float angle;
    public int ch;
    public int cw;
    public boolean enabled;
    public boolean focusClick;
    public int h;
    public boolean hasMoved;
    public int index;
    public boolean isResized;
    public boolean selected;
    private Object tag;
    public boolean visible;
    public float vx;
    public float vy;
    public int w;
    public float x;
    public float y;
    public float z;

    public GameObject()
    {
    }

    protected void applyAnimation()
    {
        if(_animation != null && !_animation.hasFinish())
        {
            _animation.apply();
        }
    }

    public int compareTo(GameObject gameobject)
    {
        return 0;
    }

    public int compareTo(Object obj)
    {
        return compareTo((GameObject)obj);
    }

    public float distanceTo(float f, float f1)
    {
        float f2 = f - x;
        float f3 = f1 - y;
        return (float)Math.sqrt(f2 * f2 + f3 * f3);
    }

    public float distanceTo(GameObject gameobject)
    {
        float f = gameobject.x - x;
        float f1 = gameobject.y - y;
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    public Animation getAnimation()
    {
        return _animation;
    }

    public Bitmap getBitmap(int i)
    {
        return Game.getBitmap(i);
    }

    public int getH()
    {
        return h;
    }

    public int getHeight()
    {
        return h;
    }

    public Rect getRect()
    {
        return new Rect((int)x, (int)y, (int)(x + (float)w), (int)(y + (float)h));
    }

    public Object getTag()
    {
        return tag;
    }

    public int getW()
    {
        return w;
    }

    public int getWidth()
    {
        return w;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public boolean hasAnimation()
    {
        return _animation != null;
    }

    public void hide()
    {
        enabled = false;
        visible = false;
    }

    public boolean hit()
    {
        if(!TouchScreen.eventDown)
        {
            return false;
        } else
        {
            return intersectPoint(TouchScreen.x, TouchScreen.y);
        }
    }

    public boolean intersectLine(int i, int j, int k, int l)
    {
        return MathUtils.SegmentInRect((int)x, (int)y, (int)x + w, (int)y + h, i, j, k, l);
    }

    public boolean intersectMidPoint(int i, int j)
    {
        return MathUtils.PtInRect((int)x - cw, (int)y - ch, (int)x + cw, (int)y + ch, i, j);
    }

    public boolean intersectPoint(int i, int j)
    {
        return MathUtils.PtInRect((int)x, (int)y, (int)x + w, (int)y + h, i, j);
    }

    public boolean intersectRect(int i, int j, int k, int l)
    {
        return MathUtils.RectIntersect((int)x, (int)y, (int)x + w, (int)y + h, i, j, k, l);
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public boolean isTouch()
    {
        return TouchScreen.isInRect((int)getX(), (int)getY(), getWidth(), getHeight());
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void moveBy(float f, float f1)
    {
        if(f == 0.0F && f1 == 0.0F)
        {
            return;
        } else
        {
            hasMoved = true;
            x = f + x;
            y = f1 + y;
            return;
        }
    }

    public void moveTo(float f, float f1)
    {
        if(f == x && f1 == y)
        {
            return;
        } else
        {
            hasMoved = true;
            x = f;
            y = f1;
            return;
        }
    }

    public void onPause()
    {
    }

    public void onReset()
    {
    }

    public void onResume()
    {
    }

    public void setAnimation(Animation animation)
    {
        _animation = animation;
        _animation.setParent(this);
    }

    public void setEnabled(boolean flag)
    {
        enabled = flag;
    }

    public void setH(int i)
    {
        if(i == h)
        {
            return;
        } else
        {
            isResized = true;
            h = i;
            ch = h >> 1;
            return;
        }
    }

    public void setHeight(int i)
    {
        if(h != i)
        {
            h = i;
            ch = h / 2;
        }
    }

    public void setSize(int i, int j)
    {
        w = i;
        h = j;
        cw = w >> 1;
        ch = h >> 1;
    }

    public void setTag(Object obj)
    {
        tag = obj;
    }

    public void setVisible(boolean flag)
    {
        visible = flag;
    }

    public void setW(int i)
    {
        if(i == w)
        {
            return;
        } else
        {
            isResized = true;
            w = i;
            cw = w >> 1;
            return;
        }
    }

    public void setWidth(int i)
    {
        if(w != i)
        {
            w = i;
            cw = w / 2;
        }
    }

    public void setX(float f)
    {
        if(f == x)
        {
            return;
        } else
        {
            hasMoved = true;
            x = f;
            return;
        }
    }

    public void setY(float f)
    {
        if(f == y)
        {
            return;
        } else
        {
            hasMoved = true;
            y = f;
            return;
        }
    }

    public void show()
    {
        enabled = true;
        visible = true;
    }
}

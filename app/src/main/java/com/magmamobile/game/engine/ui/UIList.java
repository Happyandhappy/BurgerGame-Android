package com.magmamobile.game.engine.ui;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.SparseArray;
import com.magmamobile.game.engine.*;

public class UIList extends GameObject
{
    public static interface OnItemClickListener
    {

        public abstract void onItemClick(int i, UIList uilist);
    }

    public static interface OnScrollListener
    {

        public abstract void onScrollStateChanged(UIList uilist, int i);
    }

    public static interface UIAdapter
    {

        public abstract int getCount();

        public abstract Object getItem(int i);

        public abstract int getItemHeight(int i);

        public abstract long getItemId(int i);

        public abstract GameObject getItemUI(int i, GameObject gameobject, UIList uilist);

        public abstract int getItemWidth(int i);

        public abstract int getMaxHeight();
    }

    public static class UISlideBar extends GameObject
    {

        private int c;
        private Paint p;

        public int getColor()
        {
            return p.getColor();
        }

        public int getCorner()
        {
            return c;
        }

        public void onAction()
        {
        }

        public void onRender()
        {
            if(!visible)
            {
                return;
            }
            if(c != 0)
            {
                Game.drawRoundRect((int)x, (int)y, w, h, c, p);
                return;
            } else
            {
                Game.drawBox((int)x, (int)y, (int)x + w, (int)y + h, p);
                return;
            }
        }

        public void setColor(int i)
        {
            p.setColor(i);
        }

        public void setCorner(int i)
        {
            c = i;
        }

        public UISlideBar()
        {
            c = 0;
            p = new Paint();
            p.setColor(-1);
        }

        public UISlideBar(int i)
        {
            p = new Paint();
            p.setColor(0);
        }
    }

    public static class UISlideBarBitmap extends GameObject
    {

        private Bitmap b;

        public Bitmap getBitmap()
        {
            return b;
        }

        public void onAction()
        {
        }

        public void onRender()
        {
            if(!visible || b == null)
            {
                return;
            } else
            {
                Game.drawBitmap(b, x, y);
                return;
            }
        }

        public void setBitmap(Bitmap bitmap)
        {
            b = bitmap;
        }

        public UISlideBarBitmap()
        {
            b = null;
        }

        public UISlideBarBitmap(int i)
        {
            b = Game.getBitmap(i);
            if(b != null)
            {
                w = b.getWidth();
                h = b.getHeight();
            }
        }

        public UISlideBarBitmap(Bitmap bitmap)
        {
            b = bitmap;
            if(bitmap != null)
            {
                w = bitmap.getWidth();
                h = bitmap.getHeight();
            }
        }
    }


    private SparseArray _cache;
    private float _friction;
    private boolean _hook;
    private UIAdapter _list;
    private float _ly;
    private OnItemClickListener _onItemClicklistener;
    private OnScrollListener _onScrolllistener;
    private GameObject _scrollBar;
    private float _scrollY;
    private boolean _scrolling;
    private GameObject _selected;
    private VelocityTracker _tracker;
    private int _units;
    private float _velocity;

    public UIList()
    {
        _cache = new SparseArray();
        _tracker = VelocityTracker.obtain();
        _scrollBar = new UISlideBar();
        _scrollBar.setW(10);
        _scrollBar.setH(50);
        _friction = 0.9F;
        _units = 25;
        visible = true;
        enabled = true;
    }

    public void clearScroll()
    {
        _scrolling = false;
        _scrollY = 0.0F;
        _velocity = 0.0F;
        _ly = 0.0F;
    }

    public float getFriction()
    {
        return _friction;
    }

    public UIAdapter getList()
    {
        return _list;
    }

    public OnItemClickListener getOnItemClickListener()
    {
        return _onItemClicklistener;
    }

    public OnScrollListener getOnScrollListener()
    {
        return _onScrolllistener;
    }

    public float getScroll()
    {
        return _scrollY;
    }

    public GameObject getSlideBar()
    {
        return _scrollBar;
    }

    public int getUnits()
    {
        return _units;
    }

    public void onAction()
    {
        if(!enabled || _list == null)
            return;
        boolean flag;
        int l;
        int i1;
        GameObject gameobject;
        GameObject gameobject1;
        int i = _list.getMaxHeight();
        int j = getHeight();
        if(i > j)
            flag = true;
        else
            flag = false;
        if(!TouchScreen.eventDown) {
            if(!TouchScreen.eventUp)
            {
                if(TouchScreen.eventMoving && _hook)
                {
                    _tracker.addMovement(TouchScreen.x, TouchScreen.y);
                    if(Math.abs(_ly - (float)TouchScreen.y) > 10F && !_scrolling)
                    {
                        _ly = TouchScreen.y;
                        _scrolling = true;
                    }
                    if(_scrolling && flag)
                    {
                        _scrollY = _scrollY + (_ly - (float)TouchScreen.y);
                        _ly = TouchScreen.y;
                    }
                }
            }else {
                if (_hook) {
                    _tracker.addMovement(TouchScreen.x, TouchScreen.y);
                    _tracker.computeCurrentVelocity(_units);
                    _hook = false;
                    if (!_scrolling) {
                        if (_selected != null) {
                            _selected.selected = false;
                        }
                        l = _list.getCount();
                        i1 = 0;
                        while (i1 < l) {
                            gameobject = (GameObject) _cache.get(i1);
                            gameobject1 = _list.getItemUI(i1, gameobject, this);
                            if (TouchScreen.isInRect((int) gameobject1.getX(), (int) gameobject1.getY(), gameobject1.getWidth(), -1 + gameobject1.getHeight())) {
                                gameobject1.selected = true;
                                _selected = gameobject1;
                                if (_onScrolllistener != null) {
                                    _onScrolllistener.onScrollStateChanged(this, 1);
                                }
                                if (_onItemClicklistener != null) {
                                    _onItemClicklistener.onItemClick(i1, this);
                                }
                                break;
                            }
                            i1++;
                        }
                    } else {
                        _scrolling = false;
                        _velocity = -_tracker.getYVelocity();
                        if (_onScrolllistener != null && _velocity == 0.0F) {
                            _onScrolllistener.onScrollStateChanged(this, 0);
                        }
                    }
                }
            }
        }else {
            if (TouchScreen.isInRect((int) getX(), (int) getY(), getWidth(), getHeight())) {
                _tracker.addMovement(TouchScreen.x, TouchScreen.y);
                _ly = TouchScreen.y;
                _scrolling = false;
                _hook = true;
                _velocity = 0.0F;
            }
        }
        if(!flag)
        {
            if(_scrollBar != null)
                _scrollBar.setVisible(false);
            return;
        }
        if(_scrollBar != null)
        {
            _scrollBar.setVisible(true);
        }
        _scrollY = _scrollY + _velocity;
        int k = i - j;
        if(_scrollY < 0.0F)
        {
            _scrollY = 0.0F;
            if(_onScrolllistener != null)
            {
                _onScrolllistener.onScrollStateChanged(this, 0);
            }
        } else if(_scrollY > (float)k) {
            _scrollY = k;
            if(_onScrolllistener != null)
            {
                _onScrolllistener.onScrollStateChanged(this, 0);
            }
        }
        if(_velocity != 0.0F)
        {
            _velocity = _velocity * _friction;
            if(Math.abs(_velocity) < 1.0F)
            {
                _velocity = 0.0F;
                if(_onScrolllistener != null)
                {
                    _onScrolllistener.onScrollStateChanged(this, 0);
                    return;
                }
            }
        }
    }

    public void onRender()
    {
        if(!visible)
        {
            return;
        }
        Game.clipRect((int)x, (int)y, (int)x + w, (int)y + h);
        if(_list != null) {
            int i;
            int j;
            int k;
            int l;
            i = _list.getCount();
            j = (int) y + getHeight();
            k = (int) (y - _scrollY);
            l = 0;
            while (l < i) {
                GameObject gameobject = (GameObject) _cache.get(l);
                GameObject gameobject1;
                if (gameobject != null) {
                    gameobject1 = _list.getItemUI(l, gameobject, this);
                    if (gameobject1 != gameobject) {
                        _cache.setValueAt(_cache.indexOfKey(l), gameobject1);
                    }
                } else {
                    gameobject1 = _list.getItemUI(l, null, this);
                    _cache.put(l, gameobject1);
                }
                gameobject1.setX(x);
                gameobject1.setY(k);
                gameobject1.setWidth(_list.getItemWidth(l));
                gameobject1.setHeight(_list.getItemHeight(l));
                gameobject1.onRender();
                k += _list.getItemHeight(l);
                if (k > j)
                    break;
                l++;
            }
            if (_scrollBar != null) {
                int i1 = _list.getMaxHeight() - getHeight();
                int j1 = (int) ((_scrollY / (float) i1) * (float) (getHeight() - _scrollBar.getHeight()));
                _scrollBar.setX(((int) x + getWidth()) - _scrollBar.getWidth());
                _scrollBar.setY(j1 + (int) y);
                _scrollBar.onRender();
            }
        }
        Game.clipClear();
    }

    public void setFriction(float f)
    {
        _friction = f;
    }

    public void setList(UIAdapter uiadapter)
    {
        _list = uiadapter;
    }

    public void setOnItemClickListener(OnItemClickListener onitemclicklistener)
    {
        _onItemClicklistener = onitemclicklistener;
    }

    public void setOnScrollListener(OnScrollListener onscrolllistener)
    {
        _onScrolllistener = onscrolllistener;
    }

    public void setPosition(int i)
    {
        if(_selected != null)
        {
            _selected.selected = false;
        }
        if(_list == null)
            return;
        int j;
        int k;
        int l;
        j = _list.getCount();
        k = 0;
        l = 0;
        while(l < j){
            GameObject gameobject;
            gameobject = (GameObject)_cache.get(l);
            if(l != i)
            {
                if(gameobject != null)
                    gameobject.selected = false;
            }else {
                GameObject gameobject1;
                if (gameobject != null) {
                    gameobject1 = _list.getItemUI(l, gameobject, this);
                    if (gameobject1 != gameobject) {
                        _cache.setValueAt(_cache.indexOfKey(l), gameobject1);
                    }
                } else {
                    gameobject1 = _list.getItemUI(l, null, this);
                    _cache.put(l, gameobject1);
                }
                gameobject1.selected = true;
                _selected = gameobject1;
                _scrollY = k;
            }
            k += _list.getItemHeight(l);
            l++;
        }
    }

    public void setPosition(int i, boolean flag)
    {
        if(_list == null)
            return;
        int j;
        int k;
        int l;
        j = _list.getCount();
        k = 0;
        l = 0;
        while(l < j) {
            GameObject gameobject;
            gameobject = (GameObject)_cache.get(l);
            if(l != i)
            {
                if(gameobject != null) {
                    if (flag)
                        gameobject.selected = false;
                    else
                        gameobject.selected = false;
                }
            }else {
                GameObject gameobject1;
                if (gameobject != null) {
                    gameobject1 = _list.getItemUI(l, gameobject, this);
                    if (gameobject1 != gameobject) {
                        _cache.setValueAt(_cache.indexOfKey(l), gameobject1);
                    }
                } else {
                    gameobject1 = _list.getItemUI(l, null, this);
                    _cache.put(l, gameobject1);
                }
                if (flag) {
                    gameobject1.selected = true;
                } else {
                    gameobject1.selected = false;
                }
                _scrollY = k;
            }
            k += _list.getItemHeight(l);
            l++;
        }
    }

    public void setScroll(float f)
    {
        _velocity = 0.0F;
        _scrollY = f;
    }

    public void setSlideBar(GameObject gameobject)
    {
        _scrollBar = gameobject;
    }

    public void setUnits(int i)
    {
        _units = i;
    }

    public void stopScrolling()
    {
        _scrolling = false;
        _velocity = 0.0F;
    }
}

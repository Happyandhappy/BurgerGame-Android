package com.magmamobile.game.Burger.ui;

import android.graphics.*;
import com.magmamobile.game.Burger.game.*;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.GalleryManager;
import com.magmamobile.game.Burger.stages.Board;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;

public class GalleryPhoto
{

    private final int MARGIN_B = Game.scalei(4);
    private final int MARGIN_T = Game.scalei(4);
    private final int MARGIN_X = Game.scalei(4);
    private final float SPEED = 0.1F;
    private boolean _animated;
    private int _cw;
    private int _dx;
    private int _dy;
    private float _f;
    private boolean _finished;
    private int _h;
    private int _id;
    private int _index;
    private int _tx;
    private int _ty;
    private int _w;
    private int _x;
    private int _y;
    private Paint border;
    private Canvas canvas;
    private Paint card;
    private Rect dst;
    private Paint pAlias;
    private Rect src;
    public boolean visible;

    public GalleryPhoto(int i)
    {
        _id = i;
        _w = (int)(0.8F * (float)GalleryManager.PHOTO_W);
        _h = (int)(0.8F * (float)GalleryManager.PHOTO_H);
        _cw = _w / 2;
        _dx = Game.scalei(24);
        _dy = 0;
        _f = 0.0F;
        canvas = new Canvas();
        src = new Rect(_dx, 0, GalleryManager.PHOTO_W + _dx, GalleryManager.PHOTO_H);
        dst = new Rect(0, 0, _w, _h);
        pAlias = new Paint(7);
        card = new Paint(1);
        card.setColor(-1);
        border = new Paint(card);
        border.setColor(0xff888888);
        border.setStyle(android.graphics.Paint.Style.STROKE);
        _w = _w + 2 * MARGIN_X;
        _h = _h + (MARGIN_T + MARGIN_B);
        init();
    }

    public void animate()
    {
        if(visible && _animated)
        {
            _f = 0.1F + _f;
            if(_f >= 1.0F)
            {
                _f = 0.0F;
                _animated = false;
                _finished = true;
            }
            _dy = (int)MathUtils.lerpAccelerate(0.0F, Game.mBufferHeight - _y, _f);
        }
    }

    public void createPhoto(int ai[][])
    {
        android.graphics.Bitmap bitmap = BitmapManager.photos[_id];
        canvas.setBitmap(bitmap);
        canvas.save();
        canvas.drawBitmap(BitmapManager.background, src, dst, pAlias);
        canvas.scale(0.8F, 0.8F);
        canvas.translate(Game.scalei(8), Game.scalei(32));
        canvas.drawBitmap(BitmapManager.tray, Board.tray.x, Board.tray.y, pAlias);
        Board.accomp.restore(ai[1], ai[2]);
        AccompItem aaccompitem[] = Board.accomp.items;
        int i = aaccompitem.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                Board.burger.restore(ai[0]);
                if(Burger.length > 0)
                {
                    canvas.drawBitmap(BitmapManager.plate, Board.plate.x, Board.plate.y, pAlias);
                    canvas.drawBitmap(BitmapManager.burger, Board.burger.x, Board.burger.y, pAlias);
                }
                canvas.restore();
                return;
            }
            AccompItem accompitem = aaccompitem[j];
            if(accompitem.added)
            {
                canvas.drawBitmap(BitmapManager.accompItems[accompitem.type], accompitem.x, accompitem.y, pAlias);
            }
            j++;
        } while(true);
    }

    public void draw()
    {
        if(visible)
        {
            Game.drawRect(_x, _y + _dy, _w, _h, card);
            Game.drawRect(_x, _y + _dy, _w, _h, border);
            Game.drawBitmap(BitmapManager.photos[_id], _tx, _ty + _dy);
        }
    }

    public void fall()
    {
        _f = 0.0F;
        _dy = 0;
        _animated = true;
    }

    public int getCW()
    {
        return _cw;
    }

    public int getHeight()
    {
        return _h;
    }

    public int getIndex()
    {
        return _index;
    }

    public int getWidth()
    {
        return _w;
    }

    public int getX()
    {
        return _x;
    }

    public int getY()
    {
        return _y;
    }

    public void init()
    {
        visible = false;
        _index = -1;
        _animated = false;
    }

    public boolean isFinished()
    {
        boolean flag = _finished;
        boolean flag1 = false;
        if(flag)
        {
            _finished = false;
            flag1 = true;
        }
        return flag1;
    }

    public void setIndex(int i)
    {
        _index = i;
    }

    public void setX(int i)
    {
        _x = i;
        _tx = _x + MARGIN_X;
    }

    public void setY(int i)
    {
        _y = i;
        _ty = i + MARGIN_T;
    }
}

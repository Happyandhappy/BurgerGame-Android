package com.magmamobile.game.engine.tmp;

import android.graphics.Bitmap;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;

// Referenced classes of package com.magmamobile.game.engine.tmp:
//            AnimBag, AnimFrame, AnimPackage

public class BitmapAnimation
{

    private Bitmap _bitmap;
    private int _frame;
    private AnimBag _frames;
    private int _id;
    private boolean _loop;
    private AnimPackage _package;
    private boolean _playing;
    private long _tick;

    public BitmapAnimation(AnimPackage animpackage)
    {
        _package = animpackage;
    }

    public void dephase()
    {
        _frame = MathUtils.randomi(_frames.frames.length);
        _tick = (long)_frames.frames[_frame].time + Game.tick;
        _bitmap = _frames.frames[_frame].image;
    }

    public Bitmap getBitmap()
    {
        return _bitmap;
    }

    public int getCurrentFrame()
    {
        return _frame;
    }

    public int getSize()
    {
        return _frames.frames.length;
    }

    public boolean isLoop()
    {
        return _loop;
    }

    public boolean isPlaying()
    {
        return _playing;
    }

    public void onAction()
    {
        if(_playing && _tick <= Game.tick)
        {
            _frame = 1 + _frame;
            if(_frame >= _frames.frames.length)
            {
                if(_loop)
                {
                    _frame = 0;
                } else
                {
                    _frame = -1 + _frames.frames.length;
                    _playing = false;
                }
            }
            _tick = (long)_frames.frames[_frame].time + Game.tick;
            _bitmap = _frames.frames[_frame].image;
        }
    }

    public void play(String s)
    {
        _frame = 0;
        _loop = true;
        _playing = true;
        _id = _package.getIndexOf(s);
        _frames = _package.get(_id);
        _tick = (long)_frames.frames[_frame].time + Game.tick;
        _bitmap = _frames.frames[_frame].image;
    }

    public void playNoStop(String s)
    {
        if(!_playing)
        {
            _frame = 0;
            _loop = true;
            _playing = true;
        }
        int i = _package.getIndexOf(s);
        if(_id != i)
        {
            _id = i;
            _frames = _package.get(_id);
            if(_frame >= _frames.frames.length)
            {
                _frame = 0;
            }
            _tick = (long)_frames.frames[_frame].time + Game.tick;
            _bitmap = _frames.frames[_frame].image;
        }
    }

    public void playOnce(String s)
    {
        _frame = 0;
        _loop = false;
        _playing = true;
        _id = _package.getIndexOf(s);
        _frames = _package.get(_id);
        _tick = (long)_frames.frames[_frame].time + Game.tick;
        _bitmap = _frames.frames[_frame].image;
    }

    public void setLoop(boolean flag)
    {
        _loop = flag;
    }

    public void stop()
    {
        _frame = 0;
        _playing = false;
        _bitmap = _frames.frames[0].image;
    }
}

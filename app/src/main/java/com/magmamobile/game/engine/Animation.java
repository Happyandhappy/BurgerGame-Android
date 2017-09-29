package com.magmamobile.game.engine;

import com.magmamobile.game.engine.interpolation.Interpolator;

public class Animation
{

    private float _factor;
    private float _from;
    private Interpolator _interpolator;
    private GameObject _parent;
    private boolean _playing;
    private int _start;
    private int _startcount;
    private float _to;

    public Animation(float f, float f1, Interpolator interpolator, int i)
    {
        _interpolator = interpolator;
        _start = i;
        _from = f;
        _to = f1;
    }

    public void apply()
    {
        while(_parent == null || !_playing) 
        {
            return;
        }
        if(_startcount > 0)
            _startcount = -1 + _startcount;
        else {
            if(_factor >= 1.0F)
            {
                _playing = false;
                return;
            }
            _factor = (float)(0.050000000000000003D + (double)_factor);
            if(_factor > 1.0F)
            {
                _factor = 1.0F;
            }
        }
        _parent.setX(MathUtils.lerpLinear(_from, _to, _interpolator.getInterpolation(_factor)));
    }

    public boolean hasFinish()
    {
        return !_playing;
    }

    public void setParent(GameObject gameobject)
    {
        _parent = gameobject;
    }

    public void start()
    {
        _startcount = _start;
        _playing = true;
        _factor = 0.0F;
        _parent.setX(MathUtils.lerpLinear(_from, _to, _interpolator.getInterpolation(_factor)));
    }
}

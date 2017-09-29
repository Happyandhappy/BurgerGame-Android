package com.magmamobile.game.engine;

import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;

public abstract class GameStage
    implements IGameStage
{

    public static final int STATUS_CANCELED = 2;
    public static final int STATUS_DONE = 1;
    public static final int STATUS_RUNNING = 0;
    private boolean _active;
    private int _choice;
    private boolean _enabled;
    public FocusBag _foucs;
    private int _id;
    private boolean _initialized;
    private int _status;
    private Object _tag;
    private View _view;

    public GameStage()
    {
        _foucs = new FocusBag();
    }

    public void call(int i)
    {
        Game.mHandler.sendMessage(Game.mHandler.obtainMessage(3, i, 0, this));
    }

    public void cancel()
    {
        _status = 2;
    }

    public void done()
    {
        _status = 1;
    }

    public boolean enterOnResume()
    {
        return true;
    }

    public int getChoice()
    {
        return _choice;
    }

    public View getContentView()
    {
        return _view;
    }

    public int getId()
    {
        return _id;
    }

    public int getStatus()
    {
        return _status;
    }

    public Object getTag()
    {
        return _tag;
    }

    public boolean isActive()
    {
        return _active;
    }

    public boolean isEnabled()
    {
        return _enabled;
    }

    public boolean isInitialized()
    {
        return _initialized;
    }

    public void onBackButton()
    {
    }

    public void onCall(int i)
    {
    }

    public View onCreateView()
    {
        return null;
    }

    public void onEnter()
    {
        Game.invalidate();
        _status = 0;
        _enabled = true;
        _choice = 0;
        if(useView())
        {
            Game.mHandler.sendMessage(Game.mHandler.obtainMessage(5, 0, 0, this));
        }
    }

    public void onInitialize()
    {
        _initialized = true;
    }

    public void onLateResume()
    {
    }

    public void onLeave()
    {
        _enabled = false;
        if(useView())
        {
            Game.mHandler.sendMessage(Game.mHandler.obtainMessage(6, 0, 0, this));
        }
    }

    public void onSensorEvent(SensorEvent sensorevent)
    {
    }

    public void onShowView()
    {
    }

    public void onTerminate()
    {
        _initialized = false;
    }

    public void onTouchEvent(int i, int j, int k)
    {
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return false;
    }

    public void setActive(boolean flag)
    {
        _active = flag;
    }

    public void setChoice(int i)
    {
        _choice = i;
    }

    public void setContentView(View view)
    {
        _view = view;
    }

    public void setId(int i)
    {
        _id = i;
    }

    public void setRate(GameRate gamerate)
    {
        Game.setRate(gamerate);
    }

    public void setStatus(int i)
    {
        _status = i;
    }

    public void setTag(Object obj)
    {
        _tag = obj;
    }

    public void startAnimation(Animation animation, android.view.animation.Animation.AnimationListener animationlistener)
    {
        if(_view == null)
        {
            return;
        }
        if(animationlistener != null)
        {
            animation.setAnimationListener(animationlistener);
        }
        _view.setAnimation(animation);
        animation.startNow();
        Game.layout.invalidate();
    }

    public boolean useView()
    {
        return false;
    }
}

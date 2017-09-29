package com.magmamobile.game.engine;

import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public interface IGameStage
{

    public abstract boolean enterOnResume();

    public abstract View getContentView();

    public abstract boolean isActive();

    public abstract void onAction();

    public abstract void onBackButton();

    public abstract void onCall(int i);

    public abstract View onCreateView();

    public abstract void onEnter();

    public abstract void onInitialize();

    public abstract void onLateResume();

    public abstract void onLeave();

    public abstract void onRender();

    public abstract void onSensorEvent(SensorEvent sensorevent);

    public abstract void onShowView();

    public abstract void onTerminate();

    public abstract void onTouchEvent(int i, int j, int k);

    public abstract boolean onTouchEvent(MotionEvent motionevent);

    public abstract void setActive(boolean flag);

    public abstract void setContentView(View view);
}

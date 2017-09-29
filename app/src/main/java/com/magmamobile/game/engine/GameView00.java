package com.magmamobile.game.engine;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;

public final class GameView00 extends SurfaceView
{

    public GameView00(Context context)
    {
        super(context);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(Game.hideActionBar)
        {
            Game.setFullScreen();
        }
        if(Game._touchmode == 2)
        {
            IGameStage igamestage = StageManager._currentStage;
            if(igamestage != null && igamestage.isActive())
            {
                return igamestage.onTouchEvent(motionevent);
            } else
            {
                return true;
            }
        } else
        {
            return TouchScreen.ev(motionevent);
        }
    }
}

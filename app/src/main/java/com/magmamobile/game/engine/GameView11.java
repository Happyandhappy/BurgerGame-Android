package com.magmamobile.game.engine;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceView;

public final class GameView11 extends SurfaceView
{

    public GameView11(Context context)
    {
        super(context);
    }

    public void layout(int i, int j, int k, int l)
    {
        super.layout(0, 0, Game.displayWidth, Game.displayHeight);
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

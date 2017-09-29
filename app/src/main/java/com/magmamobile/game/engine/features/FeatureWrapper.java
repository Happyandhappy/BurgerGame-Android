package com.magmamobile.game.engine.features;

import android.content.Context;
import android.graphics.Point;
import android.view.*;
import com.magmamobile.game.engine.GameView00;

public abstract class FeatureWrapper
{

    public FeatureWrapper()
    {
    }

    public SurfaceView createGameView(Context context)
    {
        return new GameView00(context);
    }

    public Point getRealSize(Display display)
    {
        Point point = new Point();
        point.x = display.getWidth();
        point.y = display.getHeight();
        return point;
    }

    public boolean isGoogleTV()
    {
        return false;
    }

    public boolean isOrientationInverted()
    {
        return false;
    }

    public void setLayerTypeSofware(View view)
    {
    }

    public void setOnSystemUiVisibilityChangeListener(View view)
    {
    }

    public void setSystemUiVisibility(View view, int i)
    {
    }

    public boolean startFacebookShare(String s)
    {
        return false;
    }
}

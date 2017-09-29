package com.magmamobile.game.engine;

import android.view.SurfaceHolder;

public final class GameSurfaceCallback
    implements android.view.SurfaceHolder.Callback
{

    public GameSurfaceCallback()
    {
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        Game.onSurfaceChanged(surfaceholder, i, j, k);
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        Game.onSurfaceCreated(surfaceholder);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        Game.onSurfaceDestroyed(surfaceholder);
    }
}

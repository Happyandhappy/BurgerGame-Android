package com.magmamobile.game.engine;

import java.util.concurrent.Semaphore;

final class GameThreadTemporal extends Thread
{

    private static final Semaphore se = new Semaphore(1);

    GameThreadTemporal()
    {
    }

    public void run()
    {
        try
        {
            se.acquire();
        }
        catch(InterruptedException interruptedexception)
        {
            return;
        }
        if(Game.parameters.DEFAULT_RENDERER == 1)
        {
            Game.onTemporalCycleSoftware();
        } else
        {
            Game.onTemporalCycleHardware();
        }
        se.release();
    }

}

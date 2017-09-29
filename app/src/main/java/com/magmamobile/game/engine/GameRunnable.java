package com.magmamobile.game.engine;


// Referenced classes of package com.magmamobile.game.engine:
//            Game, GameMessageHandler

public class GameRunnable
    implements Runnable
{

    public GameRunnable()
    {
    }

    public void execute()
    {
        Game.mHandler.post(this);
    }

    public void run()
    {
    }
}

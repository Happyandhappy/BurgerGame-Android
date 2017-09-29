package com.magmamobile.game.engine;

import android.os.Handler;
import android.os.Message;

public final class GameMessageHandler extends Handler
{

    public GameMessageHandler()
    {
    }

    public void handleMessage(Message message)
    {
        Game.handleMessage(message);
    }

    public void pushToast(String s)
    {
        sendMessage(obtainMessage(7, 0, 0, s));
    }
}

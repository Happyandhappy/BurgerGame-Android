package com.magmamobile.game.engine;

import android.content.*;

final class DebugToolReceiver extends BroadcastReceiver
{

    DebugToolReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        while(Game.application == null || intent == null) 
        {
            return;
        }
        Game.application.onReceiveDebugMessage(intent);
    }
}

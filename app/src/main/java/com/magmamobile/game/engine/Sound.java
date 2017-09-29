package com.magmamobile.game.engine;

import android.media.SoundPool;
import android.util.Log;

// Referenced classes of package com.magmamobile.game.engine:
//            Game

public final class Sound
{

    protected int _soundID;
    protected int _streamID;

    protected Sound(int i)
    {
        _soundID = i;
    }

    public void pause()
    {
        while(!Game.opSound || Game.soundPool == null || _streamID == 0) 
        {
            return;
        }
        Game.soundPool.pause(_streamID);
    }

    public void play()
    {
        if(!Game.opSound || Game.soundPool == null)
        {
            return;
        }
        if(_streamID != 0)
        {
            Game.soundPool.stop(_streamID);
            _streamID = 0;
        }
        _streamID = Game.soundPool.play(_soundID, 1.0F, 1.0F, 0, 0, 1.0F);
    }

    public void playMulti()
    {
        if(!Game.opSound || Game.soundPool == null)
        {
            return;
        } else
        {
            _streamID = Game.soundPool.play(_soundID, 1.0F, 1.0F, 0, 0, 1.0F);
            return;
        }
    }

    public void resume()
    {
        while(!Game.opSound || Game.soundPool == null || _streamID == 0) 
        {
            return;
        }
        Game.soundPool.resume(_streamID);
    }
}

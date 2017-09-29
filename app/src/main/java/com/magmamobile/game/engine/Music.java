package com.magmamobile.game.engine;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

public final class Music
{

    private boolean _continuous;
    private MediaPlayer _mediaPlayer;
    private int _res;
    private float _volume;

    public Music()
    {
    }

    public Music(int i)
    {
        _continuous = true;
        _res = i;
        _volume = 2.0F;
    }

    public boolean getContinuous()
    {
        return _continuous;
    }

    public float getVolume()
    {
        return _volume;
    }

    public void initialize(Context context)
    {
    }

    public boolean isPlaying()
    {
        if(_mediaPlayer == null)
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = _mediaPlayer.isPlaying();
        }
        catch(Exception exception)
        {
            return false;
        }
        return flag;
    }

    public void pause()
    {
        if(_mediaPlayer == null)
            return;
        if(!_mediaPlayer.isPlaying())
            return;
        _mediaPlayer.pause();
    }

    public void play()
    {
        if(!Game.opMusic)
            return;
        if(_mediaPlayer != null)
        {
            terminate();
        }
        if(isPlaying())
            return;
        try {
            AssetFileDescriptor assetfiledescriptor = Game.getContext().getAssets().openFd(GamePak.getAssetName(_res)+"/"+_res+".ogg");
            _mediaPlayer = new MediaPlayer();
            _mediaPlayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
            _mediaPlayer.setAudioStreamType(3);
            _mediaPlayer.setVolume(_volume, _volume);
            _mediaPlayer.setLooping(_continuous);
            _mediaPlayer.prepare();
            _mediaPlayer.start();
        }catch (Exception e){}
    }

    public void play(int i)
    {
        _res = i;
        play();
    }

    public void restart()
    {
        if(_mediaPlayer == null)
        {
            return;
        }
        try
        {
            _mediaPlayer.seekTo(0);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public void resume()
    {
        while(!Game.opMusic || _mediaPlayer == null) 
        {
            return;
        }
        try
        {
            _mediaPlayer.start();
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public void seek(int i)
    {
        if(_mediaPlayer == null)
        {
            return;
        }
        try
        {
            _mediaPlayer.seekTo(i);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public void setContinuous(boolean flag)
    {
        _continuous = flag;
        if(_mediaPlayer == null)
        {
            return;
        }
        try
        {
            _mediaPlayer.setLooping(_continuous);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public void setVolume(float f)
    {
        _volume = f;
        if(_mediaPlayer == null)
        {
            return;
        }
        try
        {
            _mediaPlayer.setVolume(_volume, _volume);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public void stop()
    {
        if(_mediaPlayer == null)
        {
            return;
        }
        try
        {
            _mediaPlayer.stop();
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public void terminate()
    {
        if(_mediaPlayer == null)
        {
            return;
        }
        try
        {
            if(_mediaPlayer.isPlaying())
            {
                _mediaPlayer.stop();
            }
            _mediaPlayer.release();
            _mediaPlayer = null;
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }
}

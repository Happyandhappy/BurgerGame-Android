package com.magmamobile.game.Burger.managers;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import com.magmamobile.game.engine.*;

public final class SoundManager
{

    public static final int SND_ACCEPT = 28;
    public static final int SND_ACCEPT2 = 29;
    public static final int SND_ACCOMP = 10;
    public static final int SND_BELL = 34;
    public static final int SND_COUNTER = 31;
    public static final int SND_FADEIN = 26;
    public static final int SND_FADEOUT = 27;
    public static final int SND_LOCKED = 17;
    public static final int SND_LOOSE = 33;
    public static final int SND_MENUOK = 19;
    public static final int SND_PUSHDOWN = 35;
    public static final int SND_PUSHUP = 36;
    public static final int SND_QUACK = 18;
    public static final int SND_SEND = 21;
    public static final int SND_SNAP = 37;
    public static final int SND_TICKETIN = 23;
    public static final int SND_TICKETOUT = 24;
    public static final int SND_TIMEOVER = 25;
    public static final int SND_TRASH = 16;
    public static final int SND_TRAY = 22;
    public static final int SND_TROPHY = 30;
    public static final int SND_WIN = 32;
    public static final int SND_WRONG = 20;
    public static final int ZIC_MUSIC = 0;
    private static boolean _clockPaused;
    public static boolean bgMusicIsPlaying;
    public static MediaPlayer clock;
    public static boolean fadding;
    public static Music musics[];
    public static Sound sounds[];

    public SoundManager()
    {
    }

    public static int getClockDuration()
    {
        return clock.getDuration();
    }

    public static void init()
    {
        _clockPaused = false;
        Sound asound[] = new Sound[38];
        asound[0] = Game.getSound(260);
        asound[1] = Game.getSound(266);
        asound[2] = Game.getSound(272);
        asound[3] = Game.getSound(244);
        asound[4] = Game.getSound(265);
        asound[5] = Game.getSound(258);
        asound[6] = Game.getSound(269);
        asound[7] = Game.getSound(240);
        asound[8] = Game.getSound(250);
        asound[9] = Game.getSound(261);
        asound[10] = Game.getSound(251);
        asound[11] = Game.getSound(256);
        asound[12] = Game.getSound(243);
        asound[13] = Game.getSound(259);
        asound[14] = Game.getSound(249);
        asound[15] = Game.getSound(260);
        asound[16] = Game.getSound(273);
        asound[17] = Game.getSound(253);
        asound[18] = Game.getSound(264);
        asound[19] = Game.getSound(255);
        asound[20] = Game.getSound(277);
        asound[21] = Game.getSound(267);
        asound[22] = Game.getSound(274);
        asound[23] = Game.getSound(270);
        asound[24] = Game.getSound(271);
        asound[25] = Game.getSound(246);
        asound[26] = Game.getSound(247);
        asound[27] = Game.getSound(248);
        asound[28] = Game.getSound(238);
        asound[29] = Game.getSound(239);
        asound[30] = Game.getSound(275);
        asound[31] = Game.getSound(245);
        asound[32] = Game.getSound(276);
        asound[33] = Game.getSound(254);
        asound[34] = Game.getSound(241);
        asound[35] = Game.getSound(262);
        asound[36] = Game.getSound(263);
        asound[37] = Game.getSound(268);
        sounds = asound;
        Music amusic[] = new Music[1];
        amusic[0] = Game.getMusic(257);
        musics = amusic;
        BackgroundMusic.play(252);
        BackgroundMusic.stop();
        try
        {
            AssetFileDescriptor assetfiledescriptor = Game.getContext().getAssets().openFd(GamePak.getAssetName(242)+"/242.ogg");
            clock = new MediaPlayer();
            clock.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
            clock.setAudioStreamType(3);
            clock.setLooping(false);
            clock.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mediaplayer)
                {
                    mediaplayer.seekTo(0);
                    mediaplayer.start();
                }

            });
            clock.setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mediaplayer)
                {
                    mediaplayer.stop();
                }

            });
        }
        catch(Exception exception)
        {
        }
    }

    public static boolean isClockPaused()
    {
        return clock != null && _clockPaused;
    }

    public static boolean isClockPlaying()
    {
        return clock != null && clock.isPlaying();
    }

    public static boolean isMusicPlaying(int i)
    {
        return musics[i].isPlaying();
    }

    public static void musicFadeOut(float f)
    {
        float f1 = BackgroundMusic.getVolume();
        if(f1 > 0.0F)
        {
            fadding = true;
            BackgroundMusic.setvolume(f1 - f);
        } else
        if(fadding)
        {
            fadding = false;
            stopBGMusic();
            return;
        }
    }

    public static void pauseClock()
    {
        if(clock != null && clock.isPlaying())
        {
            clock.pause();
            _clockPaused = true;
        }
    }

    public static void pauseMusic(int i)
    {
        musics[i].pause();
    }

    public static void playBGMusic()
    {
        BackgroundMusic.setvolume(15F);
        BackgroundMusic.play();
        fadding = false;
        bgMusicIsPlaying = true;
    }

    public static void playClock()
    {
        if(!Game.getSoundEnable() || clock == null || isClockPlaying())
        {
            return;
        }
        if(!_clockPaused)
        {
            clock.prepareAsync();
            return;
        }
        try
        {
            clock.start();
        }
        catch(Exception exception) { }
    }

    public static void playMusic(int i)
    {
        musics[i].setContinuous(false);
        musics[i].play();
    }

    public static void playMusicAt(int i, int j)
    {
        musics[i].seek(j);
        if(!musics[i].isPlaying())
        {
            musics[i].play();
        }
    }

    public static void playMusicLoop(int i)
    {
        musics[i].setContinuous(true);
        musics[i].play();
    }

    public static void playSound(int i)
    {
        sounds[i].play();
    }

    public static void resumeClock()
    {
        if(clock != null && _clockPaused)
        {
            playClock();
            _clockPaused = false;
        }
    }

    public static void resumeMusic(int i)
    {
        musics[i].resume();
    }

    public static void seekClockTo(int i)
    {
        try {
            if (!Game.getSoundEnable() || clock == null || !clock.isPlaying())
                return;
            clock.seekTo(i);
        }catch(Exception e) {}
    }

    public static void stopBGMusic()
    {
        BackgroundMusic.stop();
        BackgroundMusic.setvolume(15F);
        bgMusicIsPlaying = false;
    }

    public static void stopClock()
    {
        if(clock != null && clock.isPlaying())
        {
            try
            {
                clock.stop();
            }
            catch(Exception exception) { }
        }
        _clockPaused = false;
    }

    public static void stopMusic(int i)
    {
        musics[i].stop();
    }
}

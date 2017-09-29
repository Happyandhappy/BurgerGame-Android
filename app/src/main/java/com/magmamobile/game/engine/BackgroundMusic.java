package com.magmamobile.game.engine;

public final class BackgroundMusic
{

    private static int current = -1;
    private static boolean playing = false;
    private static float volume = 2.0F;

    public BackgroundMusic()
    {
    }

    public static Music getMusic()
    {
        if(current >= 0)
        {
            return Game.getMusic(current);
        } else
        {
            return null;
        }
    }

    public static float getVolume()
    {
        return volume;
    }

    public static void pause()
    {
        if(current != -1)
        {
            Game.getMusic(current).pause();
        }
    }

    public static void play()
    {
        play(current);
    }

    public static void play(int i)
    {
        if(i != -1)
        {
            Music music = Game.getMusic(i);
            if(music != null)
            {
                int j = current;
                current = i;
                if(current != i || !music.isPlaying())
                {
                    if(j != -1)
                    {
                        Music music1 = Game.getMusic(j);
                        if(music1 != null)
                        {
                            music1.stop();
                        }
                    }
                    playing = true;
                    music.setVolume(volume);
                    music.play();
                    return;
                }
            }
        }
    }

    protected static void reset()
    {
        current = -1;
        playing = false;
    }

    public static void resume()
    {
label0:
        {
            if(current != -1)
            {
                if(!playing)
                {
                    break label0;
                }
                Game.getMusic(current).resume();
            }
            return;
        }
        Game.getMusic(current).play();
    }

    public static void setvolume(float f)
    {
        volume = f;
        Music music = getMusic();
        if(music != null)
        {
            music.setVolume(f);
        }
    }

    public static void stop()
    {
        if(current != -1)
        {
            Game.getMusic(current).stop();
            playing = false;
        }
    }

}

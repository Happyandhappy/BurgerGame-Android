package com.magmamobile.game.engine;


public class AppParameters
{
    public static class AdWhirl
    {

        public static String makeJSON(String s, String s1)
        {
            return (new StringBuilder("{\"extra\":{\"location_on\":1,\"background_color_rgb\":{\"red\":255,\"green\":25" +
"5,\"blue\":255,\"alpha\":1},\"text_color_rgb\":{\"red\":0,\"green\":0,\"blue\":0" +
",\"alpha\":1},\"cycle_time\":60,\"transition\":0},\"rations\":[{\"nid\":\""
)).append(s).append("\",\"type\":1,\"nname\":\"admob\",\"weight\":100,\"priority\":1,\"key\":\"").append(s1).append("\"}]}").toString();
        }

        public AdWhirl()
        {
        }
    }

    public static class Renderer
    {

        public static final int HARDWARE = 0;
        public static final int SOFTWARE = 1;

        public Renderer()
        {
        }
    }


    public static final int FIX_GS3BLACKSCREEN_AUTO = 0;
    public static final int FIX_GS3BLACKSCREEN_OFF = 2;
    public static final int FIX_GS3BLACKSCREEN_ON = 1;
    public String ADMOB_BACKUP_ID;
    public String ADMOB_BACKUP_ID2;
    public String ADMOB_MEDIATION_ID;
    public String ADMOB_MEDIATION_ID2;
    public long ADMOB_MEDIATION_REFRESH_TIME;
    public int ADS_ALIGNEMENT;
    public String ADWHIRL_DEFAULT_ADMOBKEY;
    public String ADWHIRL_DEFAULT_NID;
    public int ADWHIRL_DIP_HEIGHT;
    public boolean ADWHIRL_ENABLED;
    public String ADWHIRL_ID;
    public String ADWHIRL_ID2;
    public boolean ADWHIRL_TESTING_MODE;
    public boolean ADWHIRL_VERBOSE_LOG;
    public String ANALYTICS_CHANNEL;
    public boolean ANALYTICS_ENABLED;
    public boolean ANALYTICS_LOGS_ENABLED;
    public int APP_ORIENTATION;
    public boolean BACKTRACK_ENABLED;
    public int CHANGELOG_ABOUT;
    public int CHANGELOG_CLOSE;
    public int CHANGELOG_ICON32;
    public int CHANGELOG_LOG;
    public int CHANGELOG_NAME;
    public int CHANGELOG_TITLE;
    public boolean DEBUG_MESSAGE_ENABLED;
    public boolean DEBUG_MMQI_BITMAP;
    public String DEBUG_MMQI_HOST;
    public int DEBUG_MMQI_PORT;
    public boolean DEFAULT_ALIASING_ENABLED;
    public int DEFAULT_BUTTON_SOUND;
    public int DEFAULT_BUTTON_TEXT_COLOR;
    public float DEFAULT_BUTTON_TEXT_SIZE;
    public int DEFAULT_ENGINE_MODE;
    public GameRate DEFAULT_GAMERATE;
    public boolean DEFAULT_HAPTIC_ENABLED;
    public boolean DEFAULT_KEEPSCREENON_ENABLED;
    public String DEFAULT_LABEL_TYPEFACE;
    public boolean DEFAULT_MUSIC_ENABLED;
    public int DEFAULT_RENDERER;
    public GameRenderMode DEFAULT_RENDERMODE;
    public boolean DEFAULT_SOUND_ENABLED;
    public boolean DEFAULT_STRETCH_ENABLED;
    public boolean DEFAULT_VIBRATE_ENABLED;
    public int DEFAULT_VOLUME;
    public int FIX_GS3BLACKSCREEN;
    public String GGADS_APP_NAME;
    public String GGADS_CHANNEL_ID;
    public String GGADS_COMPANY_NAME;
    public String GGADS_EXPAND_DIRECTION;
    public String GGADS_KEYWORDS;
    public boolean GGADS_TESTING;
    public String GOO_GL_LINK;
    public int KREACTIVE_APP_ID;
    public String LINK_MARKET_CUSTOM;
    public String MMUSIA_REF_COMPLEMENT;
    public boolean SCORELOOP_ENABLED;
    public String SCORELOOP_GAME_ID;
    public String SCORELOOP_GAME_SECRET;
    public int SOUND_MAX;
    public int SOUND_QUALITY;
    public boolean USE_ACTION_COUNTER;
    public boolean USE_DPAD_FOCUS;
    public boolean USE_ERROR_REPORTER;
    public boolean USE_RENDER_COUNTER;
    public boolean USE_SENSORS;

    public AppParameters()
    {
        FIX_GS3BLACKSCREEN = 0;
        APP_ORIENTATION = -1;
        ADS_ALIGNEMENT = 12;
        ADWHIRL_DIP_HEIGHT = 52;
        DEBUG_MESSAGE_ENABLED = false;
        BACKTRACK_ENABLED = false;
        DEFAULT_ENGINE_MODE = 0;
        DEFAULT_RENDERER = 0;
        DEFAULT_RENDERMODE = GameRenderMode.Realtime;
        DEFAULT_GAMERATE = GameRate.normal;
        DEFAULT_VOLUME = 15;
        DEFAULT_KEEPSCREENON_ENABLED = true;
        DEFAULT_STRETCH_ENABLED = true;
        DEFAULT_ALIASING_ENABLED = true;
        DEFAULT_VIBRATE_ENABLED = true;
        DEFAULT_SOUND_ENABLED = true;
        DEFAULT_MUSIC_ENABLED = true;
        USE_ACTION_COUNTER = true;
        USE_RENDER_COUNTER = true;
        USE_DPAD_FOCUS = false;
        USE_SENSORS = false;
        USE_ERROR_REPORTER = false;
        DEFAULT_BUTTON_TEXT_COLOR = -1;
        DEFAULT_BUTTON_TEXT_SIZE = 20F;
        DEFAULT_BUTTON_SOUND = 0;
        DEFAULT_LABEL_TYPEFACE = null;
        SOUND_MAX = 10;
        SOUND_QUALITY = 100;
    }

    public int getBufferHeight()
    {
        return Game.getOrientation() != 0 ? 320 : 480;
    }

    public int getBufferWidth()
    {
        return Game.getOrientation() != 0 ? 480 : 320;
    }

    public int getColorMode()
    {
        return 0;
    }

    public String getPack()
    {
        return "";
    }
}

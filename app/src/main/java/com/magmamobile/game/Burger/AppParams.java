package com.magmamobile.game.Burger;

import com.magmamobile.game.engine.AppParameters;
import com.magmamobile.game.engine.Game;

public final class AppParams extends AppParameters
{

    public AppParams()
    {
        GOO_GL_LINK = "http://goo.gl/ei6oT";
        ADMOB_MEDIATION_ID = "ef8a51d1439c4f0e";
        ADMOB_MEDIATION_ID2 = "5d53199561334586";
        ADWHIRL_ENABLED = false;
        ADWHIRL_VERBOSE_LOG = false;
        ANALYTICS_CHANNEL = "UA-11900364-100";
        ANALYTICS_ENABLED = true;
        ANALYTICS_LOGS_ENABLED = false;
        MMUSIA_REF_COMPLEMENT = "-burger";
        LINK_MARKET_CUSTOM = "-burger";
        if(Game.getAndroidSDKVersion() > 10)
        {
            DEFAULT_ENGINE_MODE = 1;
        } else
        {
            DEFAULT_ENGINE_MODE = 0;
        }
        if(Game.getAndroidSDKVersion() < 14)
        {
            APP_ORIENTATION = -1;
        } else
        {
            APP_ORIENTATION = 6;
        }
        FIX_GS3BLACKSCREEN = 2;
    }

    public int getBufferHeight()
    {
        return !App.hd() ? 320 : 480;
    }

    public int getBufferWidth()
    {
        return !App.hd() ? 480 : 720;
    }

    public int getColorMode()
    {
        return Game.getAndroidSDKVersion() <= 4 ? 1 : 2;
    }

    public String getPack()
    {
        if(App.hd())
        {
            return "HD";
        } else
        {
            return "";
        }
    }
}

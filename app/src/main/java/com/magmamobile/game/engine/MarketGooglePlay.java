package com.magmamobile.game.engine;


public final class MarketGooglePlay extends MarketWrapper
{

    public MarketGooglePlay()
    {
    }

    public String getCurrentGameUrl()
    {
        return (new StringBuilder("market://details?id=")).append(Game.getPackageName()).toString();
    }

    public String getGameUrl(String s)
    {
        return (new StringBuilder("market://details?id=")).append(s).toString();
    }

    public String getName()
    {
        return "Google Play";
    }

    public String getPublisherUrl()
    {
        return "market://search?q=pub:Magma%20Mobile";
    }
}

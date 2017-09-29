package com.magmamobile.game.engine;


public class MarketAmazon extends MarketWrapper
{

    public MarketAmazon()
    {
    }

    public String getCurrentGameUrl()
    {
        return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(Game.getPackageName()).toString();
    }

    public String getGameUrl(String s)
    {
        return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?p=")).append(s).toString();
    }

    public String getName()
    {
        return "Amazon";
    }

    public String getPublisherUrl()
    {
        return (new StringBuilder("http://www.amazon.com/gp/mas/dl/android?showAll=1&p=")).append(Game.getPackageName()).toString();
    }
}

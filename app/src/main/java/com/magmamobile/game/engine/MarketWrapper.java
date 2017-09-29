package com.magmamobile.game.engine;

import android.content.Intent;
import android.net.Uri;

public abstract class MarketWrapper
{

    public MarketWrapper()
    {
    }

    private boolean showBrowser(String s)
    {
        try
        {
            Game.getContext().startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(s)), -1);
        }
        catch(Exception exception)
        {
            return false;
        }
        return true;
    }

    public abstract String getCurrentGameUrl();

    public abstract String getGameUrl(String s);

    public abstract String getName();

    public abstract String getPublisherUrl();

    public boolean showCurrentGame()
    {
        return showCurrentGame(null);
    }

    public boolean showCurrentGame(String s)
    {
        StringBuilder stringbuilder = new StringBuilder(String.valueOf(getCurrentGameUrl()));
        if(s == null)
        {
            s = "";
        }
        return showBrowser(stringbuilder.append(s).toString());
    }

    public boolean showGame(String s)
    {
        return showGame(s, null);
    }

    public boolean showGame(String s, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder(String.valueOf(getGameUrl(s)));
        if(s1 == null)
        {
            s1 = "";
        }
        return showBrowser(stringbuilder.append(s1).toString());
    }

    public boolean showPublisher()
    {
        return showPublisher(null);
    }

    public boolean showPublisher(String s)
    {
        StringBuilder stringbuilder = new StringBuilder(String.valueOf(getPublisherUrl()));
        if(s == null)
        {
            s = "";
        }
        return showBrowser(stringbuilder.append(s).toString());
    }
}

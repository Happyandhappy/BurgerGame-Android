package com.magmamobile.game.engine.features;

import android.content.Intent;
import android.content.pm.PackageManager;
import com.magmamobile.game.engine.*;

public final class FeatureWrapper05 extends FeatureWrapper
{

    public FeatureWrapper05()
    {
    }

    public static final boolean _startFacebookShare(String s)
    {
        if(Game.HasFacebookApp())
        {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.setPackage("com.facebook.katana");
            intent.putExtra("android.intent.extra.TEXT", s);
            Game.getContext().startActivity(intent);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isGoogleTV()
    {
        return Game.getApplication().getPackageManager().hasSystemFeature("com.google.android.tv");
    }

    public boolean startFacebookShare(String s)
    {
        return _startFacebookShare(s);
    }
}

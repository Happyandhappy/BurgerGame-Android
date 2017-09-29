package com.magmamobile.game.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.*;
import android.preference.PreferenceManager;
import java.util.*;

public final class Utils
{

    public Utils()
    {
    }

    public static final String getAppVersion(Context context)
    {
        String s;
        try
        {
            s = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch(Exception exception)
        {
            return "";
        }
        return s;
    }

    public static final int getAppVersionCode(Context context)
    {
        int i;
        try
        {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    private static final String getDIDPref(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("DIDBIS", "");
    }

    public static final String getDeviceID()
    {
        String s;
        String s1;
        String s5;
        try
        {
            s = android.provider.Settings.System.getString(Game.application.getContentResolver(), "android_id");
            if(s != null)
            {
                if(!s.toLowerCase().equals("9774d56d682e549c"))
                {
                    if(!s.toLowerCase().equals("575c2ef207c21d5b"))
                    {
                        if(!s.toLowerCase().equals("22a000002457bbd5"))
                        {
                            if(!s.toLowerCase().equals("200146e3ff6bd264"))
                            {
                                if(!s.toLowerCase().equals("emulator"))
                                {
                                    return s;
                                }
                                s = getDIDPref(Game.application);
                                if(!s.equals("") && s != null)
                                {
                                    return s;
                                }
                                s = (new StringBuilder("Emulator/")).append(UUID.randomUUID().toString()).toString();
                                setDIDPref(Game.application, s);
                                return s;
                            }
                            s = getDIDPref(Game.application);
                            if(!s.equals("") && s != null)
                            {
                                return s;
                            }
                            String s2;
                            s2 = (new StringBuilder("200146e3ff6bd264/")).append(UUID.randomUUID().toString()).toString();
                            setDIDPref(Game.application, s2);
                            return s2;
                        }
                        s = getDIDPref(Game.application);
                        if(!s.equals("") && s != null)
                        {
                            return s;
                        }
                        String s3;
                        s3 = (new StringBuilder("22a000002457bbd5/")).append(UUID.randomUUID().toString()).toString();
                        setDIDPref(Game.application, s3);
                        return s3;
                    }
                    s = getDIDPref(Game.application);
                    if(!s.equals("") && s != null)
                    {
                        return s;
                    }
                    String s4;
                    s4 = (new StringBuilder("575c2ef207c21d5b/")).append(UUID.randomUUID().toString()).toString();
                    setDIDPref(Game.application, s4);
                    return s4;
                }
                s = getDIDPref(Game.application);
                if(!s.equals("") && s != null)
                {
                    return s;
                }
                s5 = (new StringBuilder("9774d56d682e549c/")).append(UUID.randomUUID().toString()).toString();
                setDIDPref(Game.application, s5);
                return s5;
            }
            s = getDIDPref(Game.application);
            if(!s.equals("") && s != null)
            {
                return s;
            }
            s1 = (new StringBuilder("emulator/")).append(UUID.randomUUID().toString()).toString();
            setDIDPref(Game.application, s1);
            return s1;
        }
        catch(Exception exception)
        {
            return "";
        }
    }

    public static final boolean isNullOrEmpty(Object aobj[])
    {
        while(aobj == null || aobj.length <= 0) 
        {
            return true;
        }
        return false;
    }

    public static final boolean isPackageExists(Context context, String s)
    {
        Iterator iterator = context.getPackageManager().getInstalledApplications(0).iterator();
        do
        {
            if(!iterator.hasNext())
            {
                return false;
            }
        } while(!((ApplicationInfo)iterator.next()).packageName.equals(s));
        return true;
    }

    private static final void setDIDPref(Context context, String s)
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("DIDBIS", s);
        editor.commit();
    }

    public static final void showCarValet(Context context)
    {
        Game.showMarket("com.magmamobile.game.CarValet");
    }

    public static final void showMouseTrap(Context context)
    {
        Game.showMarket("com.magmamobile.game.mousetrap");
    }
}

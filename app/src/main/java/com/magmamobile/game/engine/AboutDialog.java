package com.magmamobile.game.engine;

import android.content.*;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import java.lang.reflect.Field;

public final class AboutDialog
{

    private static int getResAbout;
    private static int getResChangeLog;
    private static int getResClose;
    private static int getResIcon32;
    private static int getResName;
    private static int getResTitle;
    private static String html;
    private static boolean loaded;
    private static boolean visible;

    private AboutDialog()
    {
    }

    private static void doShow(boolean flag)
    {
        final GameActivity context = Game.getContext();
        final String version = Utils.getAppVersion(context);
        if(!loaded)
        {
            initRessources(context);
            loaded = true;
        }
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        WebView webview = new WebView(context);
        String s = context.getString(getResTitle);
        Object aobj[] = new Object[1];
        aobj[0] = context.getString(getResName);
        String s1 = String.format(s, aobj);
        webview.loadDataWithBaseURL(null, html, "text/html", "utf-8", "about:blank");
        builder.setView(webview);
        builder.setTitle(s1);
        builder.setIcon(getResIcon32);
        builder.setCancelable(true);
        builder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialoginterface)
            {
                AboutDialog.visible = false;
            }

        });
        builder.setPositiveButton(context.getString(getResClose), new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i)
            {
                if(i == -1)
                {
                    android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                    editor.putString("lastversion", version);
                    editor.commit();
                }
                AboutDialog.visible = false;
            }

        });
        builder.show();
    }

    private static void initRessources(Context context)
    {
        AppParameters appparameters = Game.getParameters();
        getResTitle = appparameters.CHANGELOG_TITLE;
        getResName = appparameters.CHANGELOG_NAME;
        getResAbout = appparameters.CHANGELOG_ABOUT;
        getResIcon32 = appparameters.CHANGELOG_ICON32;
        getResClose = appparameters.CHANGELOG_CLOSE;
        getResChangeLog = appparameters.CHANGELOG_LOG;
        if(getResChangeLog == 0)
        {
            try
            {
                Class class1 = Class.forName(context.getPackageName().concat(".R$string"));
                Class class2 = Class.forName(context.getPackageName().concat(".R$drawable"));
                getResTitle = ((Integer)class1.getField("about_title").get(class1)).intValue();
                getResClose = ((Integer)class1.getField("close").get(class1)).intValue();
                getResName = ((Integer)class1.getField("app_name").get(class1)).intValue();
                getResAbout = ((Integer)class1.getField("about").get(class1)).intValue();
                getResIcon32 = ((Integer)class2.getField("icon32").get(class2)).intValue();
                getResChangeLog = -1;
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                throw new RuntimeException("Missing changelog res cf:AboutDialog.java");
            }
        }
        html = context.getString(getResAbout).replace("\2441\244", Utils.getAppVersion(context)).replace("\2442\244", "Burger Game").replace("\2443\244", Game.getMarket().getCurrentGameUrl()).replace("\2444\244", Game.getMarket().getPublisherUrl());
    }

    public static void show(final boolean force)
    {
        if(visible)
        {
            return;
        }
        GameActivity gameactivity = Game.getContext();
        String s = Utils.getAppVersion(gameactivity);
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(gameactivity);
        String s1 = sharedpreferences.getString("lastversion", "");
        if(!force && (s.equals("1.0.0") || s.equals(s1) || Game.isFirstUse()))
        {
            android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("lastversion", s);
            editor.commit();
            return;
        }
        visible = true;
        if(Game.isMainThread())
        {
            doShow(force);
            return;
        } else
        {
            Game.postRunnable(new Runnable() {
                public void run()
                {
                    AboutDialog.doShow(force);
                }
            });
            return;
        }
    }


}

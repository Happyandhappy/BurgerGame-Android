package com.magmamobile.game.engine;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;

public abstract class GameApplication extends Application
{

    public GameApplication()
    {
    }

    public GameStage addStage(GameStage gamestage)
    {
        return StageManager.addStage(gamestage);
    }

    public void addStage(Class class1)
    {
        StageManager.addStage(class1);
    }

    public void clearStages()
    {
        StageManager.clearStage();
    }

    public IGameStage getCurrentStage()
    {
        return StageManager.getCurrentStage();
    }

    public GameStage getStage(int i)
    {
        return StageManager.getStage(i);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        Game.onApplicationConfigurationChanged(configuration);
    }

    public void onCreate()
    {
        super.onCreate();
        Game.onApplicationCreate(this);
    }

    public AppParameters onCreateParameters()
    {
        return new AppParameters();
    }

    public View onCreateView()
    {
        return null;
    }

    public void onEngineInitialize()
    {
    }

    public void onEngineLanguageChanged(String s, String s1)
    {
    }

    public void onEngineTerminate()
    {
    }

    public void onFirstUse()
    {
    }

    public void onLowMemory()
    {
        super.onLowMemory();
        Game.onApplicationLowMemory();
    }

    public boolean onReceiveDebugMessage(Intent intent)
    {
        return false;
    }

    public void onTerminate()
    {
        super.onTerminate();
        Game.onApplicationTerminate();
    }

    public void setFirstStage(int i)
    {
        StageManager.setFirstStage(i);
    }
}

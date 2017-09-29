package com.magmamobile.game.Burger;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.magmamobile.game.Burger.game.ClockTimer;
import com.magmamobile.game.Burger.game.MenuTimer;
import com.magmamobile.game.Burger.managers.GalleryManager;
import com.magmamobile.game.Burger.managers.GameManager;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.Burger.managers.SoundManager;
import com.magmamobile.game.Burger.stages.Achievement;
import com.magmamobile.game.Burger.stages.Board;
import com.magmamobile.game.Burger.stages.Config;
import com.magmamobile.game.Burger.stages.EndGame;
import com.magmamobile.game.Burger.stages.Gallery;
import com.magmamobile.game.Burger.stages.GoodJob;
import com.magmamobile.game.Burger.stages.Home;
import com.magmamobile.game.Burger.stages.LevelSelect;
import com.magmamobile.game.Burger.stages.ModeSelect;
import com.magmamobile.game.Burger.stages.NewItem;
import com.magmamobile.game.Burger.stages.PauseGame;
import com.magmamobile.game.Burger.stages.QuitGame;
import com.magmamobile.game.engine.*;
import com.magmamobile.game.engine.menus.MenuEx;
import com.magmamobile.game.engine.menus.MenuItemEx;
import com.magmamobile.mmusia.MMUSIA;

public final class ActivityGame extends GameActivity
{

    public static final int MENU_ABOUT = 0;
    public static final int MENU_BACK = 7;
    public static final int MENU_CONFIG = 3;
    public static final int MENU_GALLERY = 2;
    public static final int MENU_MMUSIA = 1;
    public static final int MENU_POLICY = 4;
    public static final int MENU_QUIT = 5;
    public static final int MENU_QUITGAME = 6;

    public ActivityGame()
    {
    }

    private void navigateTo(int i)
    {
        int j = StageManager.getStage();
        com.magmamobile.game.engine.IGameStage igamestage = StageManager.getCurrentStage();
        switch(j)
        {
        default:
            return;

        case 1: // '\001'
            ((Home)igamestage).paramNavigate(i);
            return;

        case 2: // '\002'
            ((Achievement)igamestage).paramNavigate(i);
            return;

        case 3: // '\003'
            ((Config)igamestage).paramNavigate(i);
            return;

        case 5: // '\005'
            ((ModeSelect)igamestage).paramNavigate(i);
            return;

        case 6: // '\006'
            ((LevelSelect)igamestage).paramNavigate(i);
            return;

        case 7: // '\007'
            ((NewItem)igamestage).paramNavigate(i);
            return;

        case 8: // '\b'
            ((Board)igamestage).paramNavigate(i);
            return;

        case 9: // '\t'
            ((GoodJob)igamestage).paramNavigate(i);
            return;

        case 11: // '\013'
            ((EndGame)igamestage).paramNavigate(i);
            return;

        case 10: // '\n'
            ((PauseGame)igamestage).paramNavigate(i);
            return;

        case 12: // '\f'
            ((QuitGame)igamestage).paramNavigate(i);
            return;

        case 4: // '\004'
            ((Gallery)igamestage).paramNavigate(i);
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        PrefManager.LoadPreferences(this);
        if(!Game.getAppVersionName().equals(PrefManager.prefLastVersion))
        {
            if(!Game.isFirstUse())
            {
                Common.showAbout(this);
            }
            android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putString("LastVersion", Game.getAppVersionName());
            editor.commit();
        }
        int i = PrefManager.playCount / 10;
        if(!PrefManager.noAsk4Rate && i > 0 && i != PrefManager.lastAsk4Rate)
        {
            PrefManager.updatelastAsk4Rate(i);
            Common.showAsk4Rate(this);
        }
        if(!Game.isiDTouch())
        {
            (new MMUSIA()).Init(this, getString(R.string.mmusialng), Game.getParameters().MMUSIA_REF_COMPLEMENT);
        }
    }

    public boolean onCreateOptionsMenu(MenuEx menuex)
    {
        menuex.add(0, 0, 0, getString(R.string.res_about)).setIcon(R.drawable.icon);
        if(!Game.isiDTouch())
        {
            menuex.add(0, 1, 0, getString(R.string.res_news)).setIcon(R.drawable.mmusiaicon);
        }
        menuex.add(0, 2, 0, getString(R.string.res_gallery_title)).setIcon(android.R.drawable.ic_menu_gallery);
        menuex.add(0, 3, 0, getString(R.string.res_settings)).setIcon(android.R.drawable.ic_menu_preferences);
        menuex.add(0, 4, 0, getString(R.string.res_policy)).setIcon(android.R.drawable.ic_menu_info_details);
        menuex.add(0, 5, 0, getString(R.string.res_quit)).setIcon(android.R.drawable.ic_menu_revert);
        menuex.add(0, 6, 0, getString(R.string.res_quitgame)).setIcon(android.R.drawable.ic_menu_revert);
        menuex.add(0, 7, 0, getString(R.string.res_back)).setIcon(android.R.drawable.ic_menu_revert);
        return super.onCreateOptionsMenu(menuex);
    }

    public boolean onOptionsItemSelected(MenuItemEx menuitemex)
    {
        int i = menuitemex.getItemId();
        switch(i)
        {
        case 2: // '\002'
        case 3: // '\003'
        default:
            navigateTo(i);
            return true;

        case 5: // '\005'
        case 6: // '\006'
            if(StageManager.getStage() != 1)
            {
                navigateTo(5);
                return true;
            } else
            {
                App.Quit();
                return true;
            }

        case 1: // '\001'
            MMUSIA.launch(this, 0xf423f);
            return true;

        case 0: // '\0'
            Common.showAbout(this);
            return true;

        case 4: // '\004'
            Common.analytics("menu/privacy");
            Game.showPrivacyPolicy();
            return true;
        }
    }

    protected void onPause()
    {
        super.onPause();
        if(MenuTimer.isRunning())
        {
            MenuTimer.pause();
        }
        if(ClockTimer.isRunning())
        {
            ClockTimer.pause();
        }
        if(SoundManager.bgMusicIsPlaying)
        {
            BackgroundMusic.pause();
        }
    }

    public boolean onPrepareOptionsMenu(MenuEx menuex)
    {
        int i = StageManager.getStage();
        boolean flag;
        boolean flag1;
        boolean flag2;
        MenuItemEx menuitemex;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        MenuItemEx menuitemex1;
        MenuItemEx menuitemex2;
        MenuItemEx menuitemex3;
        MenuItemEx menuitemex4;
        if(i != 3 && i != 2)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        if(i != 8 && i != 9)
        {
            flag1 = false;
        } else
        {
            flag1 = true;
        }
        if(i != 12 && i != 10 && i != 9)
        {
            flag2 = false;
        } else
        {
            flag2 = true;
        }
        menuitemex = menuex.findItem(0);
        if(!flag1 && !flag2)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        menuitemex.setVisible(flag3);
        if(!Game.isiDTouch())
        {
            MenuItemEx menuitemex5 = menuex.findItem(1);
            boolean flag8;
            if(!flag1 && !flag2)
            {
                flag8 = true;
            } else
            {
                flag8 = false;
            }
            menuitemex5.setVisible(flag8);
        }
        menuitemex1 = menuex.findItem(2);
        if(i == 8 && GameManager.isFreeplay() && !GalleryManager.isEmpty())
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        menuitemex1.setVisible(flag4);
        menuitemex2 = menuex.findItem(3);
        if(i != 3 && i != 1 && !flag2)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        menuitemex2.setVisible(flag5);
        menuitemex3 = menuex.findItem(4);
        if(i == 1)
        {
            flag6 = true;
        } else
        {
            flag6 = false;
        }
        menuitemex3.setVisible(flag6);
        menuitemex4 = menuex.findItem(5);
        if(!flag1 && !flag && !flag2)
        {
            flag7 = true;
        } else
        {
            flag7 = false;
        }
        menuitemex4.setVisible(flag7);
        menuex.findItem(6).setVisible(flag1);
        menuex.findItem(7).setVisible(flag);
        if(Game.isiDTGV())
        {
            menuex.getItem(1).setVisible(false);
            menuex.getItem(4).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menuex);
    }

    protected void onResume()
    {
        super.onResume();
        if(StageManager.getStage() == 1)
        {
            ((Home)StageManager.getCurrentStage()).onResetAfterPause();
        }
        if(StageManager.getStage() == 8)
        {
            BackgroundMusic.stop();
            ((Board)StageManager.getCurrentStage()).pause(true);
            Game.setStage(10);
        } else
        if(SoundManager.bgMusicIsPlaying)
        {
            BackgroundMusic.resume();
            return;
        }
    }
}

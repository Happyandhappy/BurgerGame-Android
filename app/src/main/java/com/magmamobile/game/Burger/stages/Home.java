package com.magmamobile.game.Burger.stages;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.CartoonFader;
import com.magmamobile.game.Burger.display.ScrollBG;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.*;
import com.magmamobile.game.engine.*;
import com.magmamobile.game.engine.thirdparty.AppOfDayButton;
import java.util.Locale;

public final class Home extends GameStage
{

    public static final int BACK_BUTTON = 9;
    public static final int MENU_MOREGAME = 1;
    public static final int MENU_PLAY = 0;
    public static final int OPTION_APPOFTHEDAY = 2;
    public static final int OPTION_BIGFERNAND = 3;
    public static final int OPTION_CONFIG = 8;
    public static final int OPTION_FACEBOOK = 4;
    public static final int OPTION_GALLERY = 7;
    public static final int OPTION_SHARE = 5;
    public static final int OPTION_TROPHY = 6;
    private final int TOTAL_MENUS = 2;
    private boolean activated;
    private AppOfDayButton appOfTheDay;
    private int center;
    private Locale locale;
    private int logoX;
    private int logoY;
    private MenuButton menuBtn[];
    private OptionButton optionBtn[];
    public int selected;
    private boolean shortResume;

    public Home()
    {
    }

    private void changeStage()
    {
        switch(selected) {
            default:
                break;
            case 0:
                Game.setStage(5);
                Common.analytics("home/button/play");
                break;
            case 1:
                App.fader.keepClosed = true;
                Game.showMoreGames();
                Common.analytics("home/button/moregame");
                break;
            case 2:
                Common.analytics("home/button/appoftheday");
                break;
            case 3:
                if(!Game.isiDTouch())
                {
                    Game.showMarket("com.magmamobile.game.BigFernand");
                    shortResume = true;
                    Common.analytics("home/button/burgerbigfernand");
                    return;
                }
                break;
            case 4:
                Game.showFacebookPage();
                shortResume = true;
                Common.analytics("home/button/facebook");
                break;
            case 5:
                Common.share();
                Common.analytics("home/button/share");
                break;
            case 6:
                Game.setStage(2);
                Common.analytics("home/button/achievement");
                break;
            case 7:
                UIManager.galleryIsFrom = 1;
                Game.setStage(4);
                Common.analytics("home/button/gallery");
                break;
            case 8:
                UIManager.configIsFrom = 1;
                Game.setStage(3);
                Common.analytics("home/button/config");
                break;
            case 9:
                Game.setStage(12);
                Common.analytics("home/back");
                break;
        }
    }

    private boolean isQuickOption(int i)
    {
        return i == 2 || i == 3 || i == 4 || i == 5;
    }

    private void refreshOptions()
    {
        int i;
        int j;
        int i1;
        int j1;
        int k1;
        i = Game.scalei(10);
        j = BitmapManager.optionsUp[0].getWidth();
        if(!GalleryManager.isEmpty())
        {
            int k = j + (-1 + (Game.scalei(350) - 7 * j) / 7);
            int l = 0;
            while(l < 7)
            {
                optionBtn[l].setX(i + l * k);
                optionBtn[l].added = true;
                l++;
            }
        } else {
            i1 = j + (Game.scalei(334) - 6 * j) / 5;
            j1 = 0;
            k1 = 0;
            while (k1 < 7) {
                optionBtn[k1].setX(i + j1 * i1);
                if (k1 != 5) {
                    optionBtn[k1].added = true;
                    j1++;
                } else {
                    optionBtn[k1].added = false;
                }
                k1++;
            }
        }
        if(appOfTheDay != null)
        {
            appOfTheDay.setX(i);
        }
    }

    private void reinitOptions()
    {
        OptionButton aoptionbutton[] = optionBtn;
        int i = aoptionbutton.length;
        int j = 0;
        while(j < i)
        {
            aoptionbutton[j].init();
            j++;
        }
    }

    private boolean validLang()
    {
        String as[];
        String s;
        int i;
        int j;
        as = (new String[] {
            "el"
        });
        s = Game.getResString(R.string.lang);
        i = as.length;
        j = 0;
        while(j < i) {
            boolean flag;
            flag = as[j].equals(s);
            if(flag)
                return false;
            j++;
        }
        return true;
    }

    public boolean enterOnResume()
    {
        resetStage();
        return false;
    }

    public void navigate(int i)
    {
        selected = i;
        activated = false;
        UIManager.backButtonActive = false;
        if(selected < 2)
        {
            SoundManager.playSound(28);
            menuBtn[selected].play();
        } else
        if(selected < 9)
        {
            SoundManager.playSound(29);
            if(isQuickOption(selected))
            {
                shortResume = true;
                activated = true;
                changeStage();
            }
        } else
            App.fader.fadeOut();
    }

    public void onAction()
    {
        int i;
        int j;
        int k;
        App.scrollBG.scroll();
        if(selected == -1)
        {
            if(App.fader.isFinished())
            {
                App.fader.stop();
                UIManager.backButtonActive = true;
                activated = true;
            }
        } else if(selected <= 2 && menuBtn[selected].isPlaying() && !menuBtn[selected].isFinished()) {
            menuBtn[selected].animate();
        } else if(!isQuickOption(selected)) {
            if(selected <= 2 && menuBtn[selected].isFinished()) {
                menuBtn[selected].stop();
            } else if(!App.fader.isPlaying()) {
                UIManager.backButtonActive = false;
                App.fader.fadeOut();
            } else if(App.fader.isFinished()) {
                App.fader.stop();
                changeStage();
            }
        }
        if(App.fader.isPlaying())
        {
            App.fader.animate();
        }
        if(TouchScreen.eventDown && activated) {
	        j = 0;
	        while(j < 2)
	        {
	        	if(TouchScreen.isInRect(menuBtn[j].x, menuBtn[j].y, menuBtn[j].width, menuBtn[j].height))
	            {
	            	if(j != 1 || !Game.isiDTouch() && !Game.isiDTGV())
	                    navigate(j);
	            	break;
	            }
	            j++;
	        }
	        k = 1;
	        while(k < 7) {
	        	optionBtn[k].interact();
	            if(optionBtn[k].onPress() && (!Game.isiDTGV() || k + 2 >= 6))
	            {
	                navigate(k + 2);
	            }
	            k++;
	        }
        }
        i = 1;
        while(i < 7)
        {
        	optionBtn[i].animate();
            i++;
        }
        if(appOfTheDay != null && !Game.isiDTGV())
        {
            appOfTheDay.onAction();
        }
        App.trophy.animate();
        return;
        
        
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            UIManager.quitIsFrom = 1;
            navigate(9);
        }
    }

    public void onEnter()
    {
        super.onEnter();
        if(!Game.isiDTouch())
        {
            appOfTheDay = new AppOfDayButton(0, 0, BitmapManager.optionsUp[0].getWidth(), BitmapManager.optionsUp[0].getHeight());
            appOfTheDay.setY(Game.scalei(256));
        }
        resetStage();
    }

    public void onInitialize()
    {
        int i;
        center = Game.scalei(176);
        logoX = Game.scalei(12);
        logoY = Game.scalei(16);
        locale = Locale.getDefault();
        menuBtn = new MenuButton[3];
        menuBtn[0] = new MenuButton();
        menuBtn[0].setLabel(Game.getResString(R.string.res_playgame).toUpperCase(locale));
        menuBtn[1] = new MenuButton();
        if(!Game.isiDTouch())
        {
            menuBtn[1].setLabel(Game.getResString(R.string.res_moregame).toUpperCase(locale));
        }
        menuBtn[2] = new MenuButton();
        menuBtn[2].setLabel(Game.getResString(R.string.res_feedback).toUpperCase(locale));
        i = 0;
        while(i < 2) {
            menuBtn[i].setColors(-1, 0xff338eff);
            menuBtn[i].setSize(App.scalefFont(46F), Game.scalef(6F));
            menuBtn[i].center(center, Game.scalei(148 + i * 70));
            menuBtn[i].centerRect(2 * center, Game.scalei(80));
            menuBtn[i].setMaxWidth(Game.scalei(330));
            i++;
        }
        int j;
        if(!validLang())
        {
            menuBtn[1].setSize(App.scalefFont(40F), Game.scalef(5.5F));
        }
        optionBtn = new OptionButton[7];
        j = 0;
        while(j < 7)
        {
            optionBtn[j] = new OptionButton(0, Game.scalei(256), BitmapManager.optionsUp[j], BitmapManager.optionsDown[j]);
            j++;
        }
    }

    public void onLateResume()
    {
    }

    public void onLeave()
    {
        super.onLeave();
        MenuButton amenubutton[] = menuBtn;
        int i = amenubutton.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                return;
            }
            amenubutton[j].reinitColor();
            j++;
        } while(true);
    }

    public void onRender()
    {
        int i;
        App.fader.apply();
        App.scrollBG.draw();
        Game.drawRect(0.0F, 0.0F, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawRect(0.0F, UIManager.barY, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawBitmap(BitmapManager.logo, logoX, logoY);
        Game.drawBitmap(BitmapManager.persoIntro, Game.scalei(320), Game.scalei(10));
        i = 0;
        while(i < 2) {
            if(!Game.isiDTGV() || i != 1)
            {
                menuBtn[i].draw();
            }
            i++;
        }
        int j = 0;
        while(j < 7)
        {
            if(!Game.isiDTGV() || j + 2 >= 6)
            {
                optionBtn[j].draw();
            }
            j++;
        }
        if(appOfTheDay != null && !Game.isiDTGV())
        {
            appOfTheDay.onRender();
        }
        App.fader.restore();
        App.showTrophy();
        App.showDebug();
    }

    public void onResetAfterPause()
    {
        if(selected == 4 || selected == 3)
        {
            shortResume = true;
            resetStage();
        }
    }

    public void paramNavigate(int i)
    {
        switch(i)
        {
        case 4: // '\004'
        default:
            return;

        case 5: // '\005'
            Common.analytics("home/menu/quit");
            App.Quit();
            return;

        case 3: // '\003'
            navigate(8);
            Common.analytics("home/menu/settings");
            return;
        }
    }

    public void resetStage()
    {
        int i = 0;
        MenuButton amenubutton[];
        int j;
        if(!shortResume)
        {
            UIManager.backButtonActive = false;
            UIManager.configIsFrom = 3;
            UIManager.barP.setColor(0xff84c3f7);
            App.scrollBG.setColor(1);
            if(!isQuickOption(selected))
            {
                activated = false;
                App.fader.fadeIn();
            }
            selected = -1;
            refreshOptions();
        } else
        {
            selected = -1;
            activated = true;
            UIManager.backButtonActive = true;
        }
        shortResume = false;
        amenubutton = menuBtn;
        j = amenubutton.length;
        while(i < j)
        {
		    amenubutton[i].reinitColor();
			i++;
        }
		reinitOptions();
        optionBtn[5].animated = GalleryManager.isFirst();
        SoundManager.playBGMusic();
    }
}

package com.magmamobile.game.Burger.stages;

import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.*;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.ui.MenuButton;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;

public final class PauseGame extends GameStage
{

    private final int MENU_CONFIG = 2;
    private final int MENU_EXIT = 3;
    private final int MENU_RESTART = 1;
    private final int MENU_RETURN = 0;
    private final int TEXT_COLOR = 0xffd232ff;
    private boolean activated;
    private MenuButton buttons[];
    private int cw;
    private int nextStage;
    private Paint pTitle1;
    private Paint pTitle2;
    private String title;
    private int titleX;
    private int titleY;
    private int w;

    public PauseGame()
    {
    }

    private void backToGame()
    {
        activated = false;
        UIManager.backButtonActive = false;
        nextStage = 8;
        App.pause.fadeOut();
    }

    private void changeStage()
    {
        Game.setStage(nextStage);
    }

    private void menuHandler(int i)
    {
        buttons[i].invertColor();
        SoundManager.playSound(29);
        switch(i)
        {
        default:
            return;

        case 0: // '\0'
            Common.analytics("pause/button/continue");
            backToGame();
            return;

        case 1: // '\001'
            UIManager.fromPause = false;
            Common.analytics("pause/button/restart");
            navigate(8);
            return;

        case 2: // '\002'
            UIManager.configIsFrom = 10;
            Common.analytics("pause/button/settings");
            navigate(3);
            return;

        case 3: // '\003'
            UIManager.quitIsFrom = 10;
            break;
        }
        Common.analytics("pause/button/exit");
        UIManager.fromPause = false;
        navigate(1);
    }

    private void navigate(int i)
    {
        activated = false;
        nextStage = i;
        UIManager.backButtonActive = false;
        if(i == 3)
        {
            App.pause.fadeOut();
            return;
        } else
        {
            App.fader.fadeOut();
            return;
        }
    }

    private void refreshButtons()
    {
        int i = 0;
        byte byte0;
        byte byte1;
        int j;
        if(GameManager.isFreeplay())
        {
            byte0 = 64;
        } else
        {
            byte0 = 52;
        }
        if(GameManager.isFreeplay())
        {
            byte1 = 112;
        } else
        {
            byte1 = 102;
        }
        j = 0;
        do
        {
            if(j >= buttons.length)
            {
                return;
            }
            buttons[j].center(cw, Game.scalei(byte1 + byte0 * i));
            buttons[j].centerRect(w, Game.scalei(byte0));
            if(j == 1 && GameManager.isFreeplay())
            {
                buttons[j].visible = false;
            } else
            {
                i++;
            }
            j++;
        } while(true);
    }

    private void resetStage()
    {
        int i = 0;
        activated = false;
        UIManager.backButtonActive = false;
        UIManager.barP.setColor(0xffe487ff);
        App.scrollBG.setColor(5);
        App.pause.fadeIn();
        UIManager.configIsFrom = 3;
        MenuButton amenubutton[] = buttons;
        int j = amenubutton.length;
        do
        {
            if(i >= j)
            {
                refreshButtons();
                return;
            }
            amenubutton[i].init();
            i++;
        } while(true);
    }

    private boolean validLang()
    {
        String as[];
        int i;
        int j;
        as = (new String[] {
            "nl", "tr", "sr"
        });
        i = as.length;
        j = 0;
        while(j < i) {
            boolean flag;
            flag = as[j].equals(Game.getResString(R.string.lang));
            if(flag)
                return false;
            j++;
        }
        return true;
    }

    private boolean validTitle()
    {
        String as[];
        int i;
        int j;
        as = (new String[] {
            "cs", "sk", "no"
        });
        i = as.length;
        j = 0;
        while(j < i) {
            boolean flag;
            flag = as[j].equals(Game.getResString(R.string.lang));
            if(flag)
                return false;
            j++;
        }
        return true;
    }

    public boolean enterOnResume()
    {
        return false;
    }

    public void onAction()
    {
        int i;
        if(App.pause.isPlaying())
        {
            if(App.pause.isFinished())
            {
                App.pause.stop();
                if(App.pause.bgAlpha == 0)
                {
                    activated = true;
                    UIManager.backButtonActive = true;
                } else
                {
                    changeStage();
                }
            } else
            {
                App.pause.animate();
            }
        }
        if(App.fader.isFinished())
        {
            App.fader.stop();
            if(!App.fader.opaque)
            {
                activated = true;
                UIManager.backButtonActive = true;
            } else
            {
                changeStage();
            }
        } else
        if(App.fader.isPlaying())
        {
            App.fader.animate();
        }
        App.scrollBG.scroll();
        if(TouchScreen.eventDown && activated) {
            i = 0;
            while (i < buttons.length) {
                if (!(!buttons[i].isReactive() || !TouchScreen.isInRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height))) {
                    menuHandler(i);
                    break; /* Loop/switch isn't completed */
                }
                i++;
            }
        }
        App.trophy.animate();
    }

    public void onBackButton()
    {
        if(UIManager.backButtonActive)
        {
            activated = false;
            UIManager.backButtonActive = false;
            Common.analytics("pause/back");
            backToGame();
        }
    }

    public void onEnter()
    {
        super.onEnter();
        resetStage();
    }

    public void onInitialize()
    {
        w = Game.screenTobufferX((float)Game.getDisplayWidth() - Game.dpi(300F));
        cw = w / 2;
        title = Game.getResString(R.string.res_paused).toUpperCase();
        titleX = cw;
        titleY = Game.scalei(48);
        pTitle1 = new Paint();
        pTitle1.setAntiAlias(Game.getAntiAliasEnabled());
        pTitle1.setColor(0xffd232ff);
        pTitle1.setTypeface(App.defaultFont);
        Paint paint = pTitle1;
        float f;
        MenuButton amenubutton[];
        int i;
        if(validTitle())
        {
            f = 46F;
        } else
        {
            f = 36F;
        }
        paint.setTextSize(App.scalefFont(f));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), -1);
        amenubutton = new MenuButton[4];
        amenubutton[0] = new MenuButton();
        amenubutton[1] = new MenuButton();
        amenubutton[2] = new MenuButton();
        amenubutton[3] = new MenuButton();
        buttons = amenubutton;
        i = 0;
        do
        {
            if(i >= buttons.length)
            {
                buttons[0].setLabel(Game.getResString(R.string.res_continue).toUpperCase());
                buttons[1].setLabel(Game.getResString(R.string.res_restart).toUpperCase());
                buttons[2].setLabel(Game.getResString(R.string.res_settings).toUpperCase());
                buttons[3].setLabel(Game.getResString(R.string.res_exit).toUpperCase());
                return;
            }
            buttons[i].setColors(-1, 0xffd232ff);
            MenuButton menubutton = buttons[i];
            float f1;
            if(validLang())
            {
                f1 = 36F;
            } else
            {
                f1 = 28F;
            }
            menubutton.setSize(App.scalefFont(f1), Game.scalef(5F));
            i++;
        } while(true);
    }

    public void onLateResume()
    {
        resetStage();
    }

    public void onLeave()
    {
        super.onLeave();
    }

    public void onRender()
    {
        App.fader.apply();
        App.scrollBG.draw();
        Game.drawRect(0.0F, 0.0F, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawRect(0.0F, UIManager.barY, UIManager.barW, UIManager.barH, UIManager.barP);
        Game.drawText(title, titleX, titleY, pTitle2);
        Game.drawText(title, titleX, titleY, pTitle1);
        MenuButton amenubutton[] = buttons;
        int i = amenubutton.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                App.fader.restore();
                if(App.pause.isPlaying())
                {
                    App.pause.draw();
                }
                App.showTrophy();
                App.showDebug();
                return;
            }
            amenubutton[j].draw();
            j++;
        } while(true);
    }

    public void paramNavigate(int i)
    {
        switch(i)
        {
        default:
            return;

        case 7: // '\007'
            Common.analytics("pause/menu/back");
            break;
        }
        SoundManager.playSound(29);
        backToGame();
    }
}

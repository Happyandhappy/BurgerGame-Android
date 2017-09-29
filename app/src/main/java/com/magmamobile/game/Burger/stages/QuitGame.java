package com.magmamobile.game.Burger.stages;

import android.content.Intent;
import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.Common;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.display.CartoonFader;
import com.magmamobile.game.Burger.display.ScrollBG;
import com.magmamobile.game.Burger.managers.SoundManager;
import com.magmamobile.game.Burger.managers.UIManager;
import com.magmamobile.game.Burger.ui.MenuButton;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;
import com.magmamobile.mmusia.activities.MMUSIABeforeExitActivity;

public final class QuitGame extends GameStage
{

    private final int MENU_NO = 1;
    private final int MENU_OTHER = 2;
    private final int MENU_QUIT = -1;
    private final int MENU_YES = 0;
    private final int TEXT_COLOR = 0xffd232ff;
    private final int TOTAL_MENU = 3;
    private boolean activated;
    private boolean alt;
    private MenuButton buttons[];
    private int nextStage;
    private Paint pTitle1;
    private Paint pTitle2;
    private String title;
    private int titleX;
    private int titleY;

    public QuitGame()
    {
    }

    private void changeStage()
    {
        switch(UIManager.quitIsFrom)
        {
        default:
            if(nextStage == -1)
            {
                App.Quit();
                return;
            } else
            {
                Game.setStage(nextStage);
                return;
            }

        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            break;
        }
        if(nextStage == -1)
        {
            UIManager.fromPause = false;
            Game.setStage(1);
            return;
        } else
        {
            Game.setStage(nextStage);
            return;
        }
    }

    private void menuHandler(int i)
    {
        buttons[i].invertColor();
        SoundManager.playSound(29);
        switch(i) {
            default:
                break;
            case 0:
                Common.analytics("quit/button/yes");
                navigate(-1);
                break;
            case 1:
                Common.analytics("quit/button/no");
                navigate(UIManager.quitIsFrom);
                break;
            case 2:
                if(!Game.isiDTGV())
                {
                    Common.analytics("quit/button/othergames");
                    Game.getContext().startActivityForResult(new Intent(Game.getContext(), com.magmamobile.mmusia.activities.MMUSIABeforeExitActivity.class), 0);
                }
                break;
        }
    }

    private void navigate(int i)
    {
        activated = false;
        nextStage = i;
        UIManager.backButtonActive = false;
        App.fader.fadeOut();
    }

    private void resetStage()
    {
        activated = false;
        MenuButton amenubutton[] = buttons;
        int i = amenubutton.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                UIManager.backButtonActive = false;
                UIManager.configIsFrom = 3;
                UIManager.barP.setColor(0xffe487ff);
                App.scrollBG.setColor(5);
                App.fader.fadeIn();
                return;
            }
            amenubutton[j].reinitColor();
            j++;
        } while(true);
    }

    private boolean validLang()
    {
        String as[];
        int i;
        int j;
        as = (new String[] {
            "nl", "lt", "es", "pt-rBR", "tr", "hu", "el", "iw"
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

    private boolean validLangTitle()
    {
        String as[];
        int i;
        int j;
        as = (new String[] {
            "ko", "tr"
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
        if(App.fader.isPlaying())
        {
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
            {
                App.fader.animate();
            }
        }
        App.scrollBG.scroll();
        if(TouchScreen.eventDown && activated) {
            i = 0;
            while (i < buttons.length) {
                if (TouchScreen.isInRect(buttons[i].x, buttons[i].y, buttons[i].width, buttons[i].height)) {
                    menuHandler(i);
                    break;
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
            Common.analytics("quit/back");
            navigate(UIManager.quitIsFrom);
        }
    }

    public void onEnter()
    {
        super.onEnter();
        resetStage();
    }

    public void onInitialize()
    {
        boolean flag;
        int i;
        int j;
        byte byte0;
        int k;
        Paint paint;
        float f;
        MenuButton amenubutton[];
        int l;
        if(Game.getDisplayWidth() < 800)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        alt = flag;
        i = Game.screenTobufferX((float)Game.getDisplayWidth() - Game.dpi(300F));
        if(alt)
        {
            j = Game.mBufferCW;
        } else
        {
            j = i / 2;
        }
        if(alt)
        {
            byte0 = 64;
        } else
        {
            byte0 = 80;
        }
        k = Game.scalei(byte0);
        title = Game.getResString(R.string.res_quittitle).toUpperCase();
        titleX = j;
        titleY = k;
        pTitle1 = new Paint();
        pTitle1.setAntiAlias(Game.getAntiAliasEnabled());
        pTitle1.setColor(0xffd232ff);
        pTitle1.setTypeface(App.defaultFont);
        paint = pTitle1;
        if(validLangTitle())
        {
            f = 46F;
        } else
        {
            f = 28F;
        }
        paint.setTextSize(App.scalefFont(f));
        pTitle1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pTitle2 = App.getStroked(pTitle1, Game.scalef(6F), -1);
        amenubutton = new MenuButton[3];
        amenubutton[0] = new MenuButton();
        amenubutton[1] = new MenuButton();
        amenubutton[2] = new MenuButton();
        buttons = amenubutton;
        l = 0;
        do
        {
            if(l >= buttons.length)
            {
                buttons[0].setLabel(Game.getResString(R.string.res_yes).toUpperCase());
                buttons[1].setLabel(Game.getResString(R.string.res_no).toUpperCase());
                if(!Game.isiDTouch())
                {
                    buttons[2].setLabel(Game.getResString(R.string.res_othergames).toUpperCase());
                }
                MenuButton menubutton = buttons[2];
                float f1;
                float f2;
                float f3;
                if(validLang())
                {
                    f1 = 42F;
                } else
                {
                    f1 = 32F;
                }
                f2 = App.scalefFont(f1);
                if(validLang())
                {
                    f3 = 6F;
                } else
                {
                    f3 = 4.5F;
                }
                menubutton.setSize(f2, Game.scalef(f3));
                return;
            }
            buttons[l].setColors(-1, 0xffd232ff);
            buttons[l].setSize(App.scalefFont(42F), Game.scalef(6F));
            buttons[l].center(j, k + Game.scalei(60 + l * 52));
            buttons[l].centerRect(i, Game.scalei(48));
            l++;
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
        int i = 0;
        do
        {
            if(i >= 3)
            {
                App.fader.restore();
                App.showTrophy();
                App.showDebug();
                return;
            }
            if(!Game.isiDTGV() || i != 2)
            {
                buttons[i].draw();
            }
            i++;
        } while(true);
    }

    public void paramNavigate(int i)
    {
        switch(i)
        {
        default:
            return;

        case 7: // '\007'
            SoundManager.playSound(29);
            break;
        }
        navigate(UIManager.quitIsFrom);
    }
}
